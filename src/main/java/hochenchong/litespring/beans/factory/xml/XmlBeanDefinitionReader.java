package hochenchong.litespring.beans.factory.xml;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.PropertyValue;
import hochenchong.litespring.beans.factory.BeanDefinitionStoreException;
import hochenchong.litespring.beans.factory.support.BeanDefinitionRegistry;
import hochenchong.litespring.beans.factory.support.GenericBeanDefinition;
import hochenchong.litespring.beans.factory.support.RuntimeBeanReference;
import hochenchong.litespring.beans.factory.support.TypedStringValue;
import hochenchong.litespring.core.io.Resource;
import hochenchong.litespring.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReader {
    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    BeanDefinitionRegistry beanDefinitionRegistry;

    private static final Logger logger = LoggerFactory.getLogger(XmlBeanDefinitionReader.class);

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(Resource resource) {
        // 解析这个文件
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            // <beans>
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id, beanClassName);
                if (element.attributeValue(SCOPE_ATTRIBUTE) != null) {
                    beanDefinition.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                parsePropertyElements(element, beanDefinition);
                this.beanDefinitionRegistry.registerBeanDefinition(id, beanDefinition);
            }
        } catch (DocumentException | IOException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document " + resource.getDescription() + " failed", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 参照：org.springframework.beans.factory.xml.BeanDefinitionParserDelegate#parsePropertyElements
    public void parsePropertyElements(Element beanEle, BeanDefinition bd) {
        Iterator<Element> elementIterator = beanEle.elementIterator(PROPERTY_ELEMENT);
        while (elementIterator.hasNext()) {
            Element propElem = elementIterator.next();
            parsePropertyElement(propElem, bd);
        }
    }

    public void parsePropertyElement(Element ele, BeanDefinition bd) {
        String propertyName = ele.attributeValue(NAME_ATTRIBUTE);
        if (!StringUtils.hasLength(propertyName)) {
            logger.error("Tag 'property' must have a 'name' attribute", ele);
            return;
        }

        Object val = parsePropertyValue(ele, bd, propertyName);
        PropertyValue pv = new PropertyValue(propertyName, val);
        bd.getPropertyValues().add(pv);
    }

    public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";



        boolean hasRefAttribute = (ele.attributeValue(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (ele.attributeValue(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute) {
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute", ele);
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        }
        else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        }
        else {
            // Neither child element nor "ref" or "value" attribute found.
            logger.error(elementName + " must specify a ref or value", ele);
            return null;
        }
    }
}
