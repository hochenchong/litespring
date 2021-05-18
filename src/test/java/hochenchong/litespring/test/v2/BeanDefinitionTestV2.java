package hochenchong.litespring.test.v2;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.PropertyValue;
import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.support.RuntimeBeanReference;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinition() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();

        Assert.assertTrue(propertyValues.size() == 2);

        {
            PropertyValue propertyValue = this.getPropertyValue("accountDao", propertyValues);

            Assert.assertNotNull(propertyValue);

            Assert.assertTrue(propertyValue.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue propertyValue = this.getPropertyValue("itemDao", propertyValues);

            Assert.assertNotNull(propertyValue);

            Assert.assertTrue(propertyValue.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> propertyValues) {
        if (propertyValues == null) {
            return null;
        }

        return propertyValues.stream().filter(pv -> pv.getName().equals(name)).findFirst().orElse(null);
    }
}
