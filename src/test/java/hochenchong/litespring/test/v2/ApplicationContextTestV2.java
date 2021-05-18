package hochenchong.litespring.test.v2;

import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.context.support.ClassPathXmlApplicationContext;
import hochenchong.litespring.dao.v2.AccountDao;
import hochenchong.litespring.dao.v2.ItemDao;
import hochenchong.litespring.service.v2.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationContextTestV2 {
    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v2.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);

        assertNotNull(petStoreService.getAccountDao());

        assertNotNull(petStoreService.getItemDao());

        assertTrue(petStoreService.getAccountDao() instanceof AccountDao);

        assertTrue(petStoreService.getItemDao() instanceof ItemDao);

        assertEquals("hochen", petStoreService.getOwner());

        assertEquals(1, petStoreService.getVersion());
    }
}
