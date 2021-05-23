package hochenchong.litespring.test.v3;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.factory.support.ConstructorResolver;
import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.core.io.ClassPathResource;
import hochenchong.litespring.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

public class ConstructorResolverTest {

    @Test
    public void testAutowireConstructor() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

        ConstructorResolver resolver = new ConstructorResolver(factory);

        PetStoreService petStoreService = (PetStoreService) resolver.autowireConstructor(beanDefinition);

        Assert.assertEquals(1, petStoreService.getVersion());
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
    }
}
