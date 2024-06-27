package hochenchong.litespring.test.v2;

import hochenchong.litespring.beans.propertyeditors.CustomBooleanEditor;
import hochenchong.litespring.beans.propertyeditors.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

public class CustomBooleanEditorTest {

    @Test
    public void testConvertString() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);
        editor.setAsText("true");
        Assert.assertTrue((Boolean) editor.getValue());
        editor.setAsText("false");
        Assert.assertFalse((Boolean) editor.getValue());

        editor.setAsText("on");
        Assert.assertTrue((Boolean) editor.getValue());
        editor.setAsText("off");
        Assert.assertFalse((Boolean) editor.getValue());

        editor.setAsText("yes");
        Assert.assertTrue((Boolean) editor.getValue());
        editor.setAsText("no");
        Assert.assertFalse((Boolean) editor.getValue());

        try {
            editor.setAsText("aabbcc");
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }
}
