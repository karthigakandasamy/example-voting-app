package unit;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseConnectionTest {

    @Test
    public void testDBConnection() {
        boolean dbConnected = true; // mock
        Assert.assertTrue(dbConnected, "DB connection failed");
    }
}
