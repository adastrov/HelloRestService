package hello_rest_service.palagen.com.github.application;

import org.junit.runner.*;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = HelloRestService.class)
public abstract class MainControllerTest {

}