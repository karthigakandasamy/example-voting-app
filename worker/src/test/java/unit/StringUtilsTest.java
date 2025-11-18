package unit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StringUtilsTest {

    @Test
    public void testIsEmpty() {
        String str = "";
        Assert.assertTrue(str.isEmpty(), "String should be empty");
    }

    @Test
    public void testContains() {
        String text = "Hello World";
        Assert.assertTrue(text.contains("World"));
    }
}
