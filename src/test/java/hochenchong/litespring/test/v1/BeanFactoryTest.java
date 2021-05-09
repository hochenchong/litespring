package hochenchong.litespring.test.v1;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.factory.BeanCreationException;
import hochenchong.litespring.beans.factory.BeanDefinitionStoreException;
import hochenchong.litespring.beans.factory.BeanFactory;
import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {
    DefaultBeanFactory beanFactory;
    XmlBeanDefinitionReader reader;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(beanFactory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        assertEquals("hochenchong.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinitions("petstore-v1.xml");
        try {
            beanFactory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
        try {
            reader.loadBeanDefinitions("xxx.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
