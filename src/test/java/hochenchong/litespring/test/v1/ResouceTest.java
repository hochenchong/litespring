package hochenchong.litespring.test.v1;

import hochenchong.litespring.core.io.ClassPathResource;
import hochenchong.litespring.core.io.FileSystemResource;
import hochenchong.litespring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResouceTest {
    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClassPathResource("petstore-v1.xml");

        InputStream is = null;

        try {
            is = resource.getInputStream();
            // 简单判断
            Assert.assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource = new FileSystemResource("src/test/resources/petstore-v1.xml");

        InputStream is = null;

        try {
            is = resource.getInputStream();
            // 简单判断
            Assert.assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
