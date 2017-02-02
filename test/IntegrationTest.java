import static org.junit.Assert.*;
import static play.test.Helpers.*;

import org.junit.Test;

import play.libs.F.Callback;
import play.test.TestBrowser;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(9001, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:9001/portal/index/home");
                assertTrue(browser.pageSource().contains("Your new application is ready."));
            }
        });
    }

}
