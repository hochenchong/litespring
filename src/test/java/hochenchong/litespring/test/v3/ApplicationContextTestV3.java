package hochenchong.litespring.test.v3;

import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.context.support.ClassPathXmlApplicationContext;
import hochenchong.litespring.dao.v3.AccountDao;
import hochenchong.litespring.dao.v3.ItemDao;
import hochenchong.litespring.service.v3.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationContextTestV3 {
    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v3.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);

        assertNotNull(petStoreService.getAccountDao());

        assertNotNull(petStoreService.getItemDao());

        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);

        assertTrue(petStoreService.getItemDao() instanceof ItemDao);

        assertEquals(1, petStoreService.getVersion());
    }
}
