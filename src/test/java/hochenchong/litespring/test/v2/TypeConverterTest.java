package hochenchong.litespring.test.v2;

import hochenchong.litespring.beans.SimpleTypeConverter;
import hochenchong.litespring.beans.TypeConverter;
import hochenchong.litespring.beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

public class TypeConverterTest {
    @Test
    public void testConvertStringToInt() throws TypeMismatchException {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3, i.intValue());

        try {
            converter.convertIfNecessary("3.1", Integer.class);
        } catch (TypeMismatchException exception) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testConvertStringToBoolean() throws TypeMismatchException {
        TypeConverter converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        Assert.assertEquals(true, b.booleanValue());

        try {
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        } catch (TypeMismatchException exception) {
            return;
        }
        Assert.fail();
    }
}
