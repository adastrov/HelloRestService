package hello_rest_service.palagen.com.github.application;

import hello_rest_service.palagen.com.github.dao.ContactDao;
import hello_rest_service.palagen.com.github.exceptions.BadRequestException;
import hello_rest_service.palagen.com.github.model.Contact;
import hello_rest_service.palagen.com.github.model.IncomeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Configuration
@ComponentScan("hello_rest_service.palagen.com.github")
public class MainController {

    @Autowired
    private ContactDao contactDao;

    @RequestMapping(value = "/hello/contacts", method = RequestMethod.GET)
    public Map<String, List<Contact>> get(IncomeData incomeData){

        Map<String, List<Contact>> responseMap = new HashMap<>(1);
        List<Contact> contactList;

        if (incomeData.getNameFilter() != null && !incomeData.getNameFilter().isEmpty()) {
            contactList = contactDao.getContactsBySqlRestriction(incomeData.getNameFilter());
            // We also can use the next construction:
            //List<Contact> contactList = contactDao.getContactsByRegEx(incomeData.getNameFilter());
            responseMap.put("contacts:", contactList);
        } else {
            throw new BadRequestException();
        }

        responseMap.put("contacts:", contactList);
        return responseMap;

    }

}
