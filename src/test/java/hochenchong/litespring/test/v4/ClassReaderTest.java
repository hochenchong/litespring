package hochenchong.litespring.test.v4;

import hochenchong.litespring.core.io.ClassPathResource;
import hochenchong.litespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("hochenchong/litespring/service/v4/PetStoreService.class");

        ClassReader classReader = new ClassReader(classPathResource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();

        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("hochenchong.litespring.service.v4.PetStoreService", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaces().length);
    }
}
