package hochenchong.litespring.test.v1;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.factory.BeanCreationException;
import hochenchong.litespring.beans.factory.BeanDefinitionStoreException;
import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.core.io.ClassPathResource;
import hochenchong.litespring.core.io.Resource;
import hochenchong.litespring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {
    DefaultBeanFactory beanFactory;
    XmlBeanDefinitionReader reader;
    Resource resource;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(beanFactory);
    }

    @Test
    public void testGetBean() {
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        assertEquals("hochenchong.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);
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
            resource = new ClassPathResource("xxx.xml");
            reader.loadBeanDefinitions(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
