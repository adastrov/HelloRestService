package hello_rest_service.palagen.com.github.utils;

import junit.framework.TestCase;
import org.junit.Assert;

public class RestServiceHelperTest extends TestCase {
    public void testIsStringMatches() throws Exception {
        Boolean result = RestServiceHelper.isStringMatches("^A.*$", "Alexandra");
        Assert.assertFalse(result);
    }

}