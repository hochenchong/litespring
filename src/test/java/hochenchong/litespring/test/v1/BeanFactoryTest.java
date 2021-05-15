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

import static org.junit.Assert.*;

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

        assertTrue(beanDefinition.isSingleton());

        assertFalse(beanDefinition.isPrototype());

        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());

        assertEquals("hochenchong.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);

        PetStoreService petStoreService1 = (PetStoreService) beanFactory.getBean("petStore");

        assertTrue(petStoreService.equals(petStoreService1));
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

    @Test
    public void testGetProtoTypeBean() {
        resource = new ClassPathResource("petstore-v1-prototype.xml");
        reader.loadBeanDefinitions(resource);

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        assertFalse(beanDefinition.isSingleton());

        assertTrue(beanDefinition.isPrototype());

        assertEquals(BeanDefinition.SCOPE_PROTOTYPE, beanDefinition.getScope());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);

        PetStoreService petStoreService1 = (PetStoreService) beanFactory.getBean("petStore");

        assertFalse(petStoreService.equals(petStoreService1));
    }
}
