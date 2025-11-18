package unit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

    @Test
    public void testAddition() {
        int a = 5;
        int b = 10;
        Assert.assertEquals(a + b, 15, "Addition failed");
    }

    @Test
    public void testSubtraction() {
        int a = 10;
        int b = 3;
        Assert.assertEquals(a - b, 7, "Subtraction failed");
    }
}
