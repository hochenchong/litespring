package hochenchong.litespring.test.v1;

import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.context.support.ClassPathXmlApplicationContext;
import hochenchong.litespring.context.support.FileSystemXmlApplicationContext;
import hochenchong.litespring.service.v1.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v1.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);
    }
    
    @Test
    public void testFileSystemXmlAppcationContextGetBean() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src/test/resources/petstore-v1.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);
    }
}
