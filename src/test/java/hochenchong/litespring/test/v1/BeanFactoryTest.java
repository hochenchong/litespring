package hochenchong.litespring.test.v1;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.factory.BeanCreationException;
import hochenchong.litespring.beans.factory.BeanDefinitionStoreException;
import hochenchong.litespring.beans.factory.BeanFactory;
import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {
    @Test
    public void testGetBean() {
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        assertEquals("hochenchong.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
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
            new DefaultBeanFactory("xxx.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
