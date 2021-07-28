package hochenchong.litespring.test.v4;

import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.context.support.ClassPathXmlApplicationContext;
import hochenchong.litespring.service.v4.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ApplicationContextTestV4 {
    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v4.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService.getAccountDao());
        assertNotNull(petStoreService.getItemDao());
    }
}
