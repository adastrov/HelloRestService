package hello_rest_service.palagen.com.github.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hello_rest_service.palagen.com.github.dto.ContactDTO;
import hello_rest_service.palagen.com.github.exceptions.*;
import hello_rest_service.palagen.com.github.model.IncomeData;
import hello_rest_service.palagen.com.github.service.ContactServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@RestController
@Configuration
@ComponentScan("hello_rest_service.palagen.com.github")
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger("MainControllerLogger");

    @Autowired
    private ContactServiceImpl contactService;

    @RequestMapping(value = "/hello/contacts", method = RequestMethod.GET)
    public ResponseEntity<Map<String, List<ContactDTO>>> getByRegEx(IncomeData incomeData) throws Exception{

        List<ContactDTO> contactList;
        Map<String, List<ContactDTO>> responseMap = new HashMap<>(1);

        if (incomeData.getNameFilter() == null || incomeData.getNameFilter().isEmpty()) {
            throw new InvalidParameterException("ISS_E: Invalid parameter! Expected: nameFilter");
        }

        try {
            Pattern.compile(incomeData.getNameFilter());
            contactList = contactService.findContactsByRegExp(incomeData.getNameFilter());
        } catch (PatternSyntaxException e) {
            throw new BadRegExpException(e.getMessage());
        }

        if (contactList.isEmpty()) {
            throw new ContactNotFoundException("Contacts not found");
        }

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

        responseMap.put("contacts", contactList);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);

    }

    @ExceptionHandler(ContactNotFoundException.class)
    protected @ResponseBody ResponseEntity<ErrorMessage> handleNoContentException(Exception e) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.getErrors().add("ISS_E: "+e.toString()+" "+e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.NO_CONTENT);

    }

    @ExceptionHandler(BadRegExpException.class)
    protected @ResponseBody ResponseEntity<ErrorMessage> handleBadRegExpException(HttpServletRequest request, Exception ex) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.getErrors().add("ISS_E: "+ex.toString()+" "+ex.getMessage());

        LOGGER.info("Requested URL="+request.getRequestURL());
        LOGGER.error("Invalid regex: " + request.getPathInfo());
        LOGGER.error("REST Error handler="+ex.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidParameterException.class)
    protected @ResponseBody ResponseEntity<ErrorMessage> handleInvalidParameterException(HttpServletRequest request, Exception ex) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.getErrors().add("ISS_E: " + ex.getMessage());

        LOGGER.info("Requested URL="+request.getRequestURL());
        LOGGER.error("REST Error handler="+ex.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

}
