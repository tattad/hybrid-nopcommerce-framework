package assertVerify;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_03_Assert_Message {
    @Test
    public void showMessage() {
        boolean statusGender = false;

        Assert.assertTrue(statusGender, "Gender is not male!!!");
    }
}
