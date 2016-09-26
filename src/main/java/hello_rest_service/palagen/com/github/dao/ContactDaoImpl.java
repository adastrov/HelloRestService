package hello_rest_service.palagen.com.github.dao;

import hello_rest_service.palagen.com.github.model.Contact;
import hello_rest_service.palagen.com.github.utils.RestServiceHelper;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Repository
public class ContactDaoImpl implements ContactDao{

    @Autowired
    private SessionFactory sessionFactory;

    public ContactDaoImpl() {

    }

    public ContactDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;

    }

    @Override
    @Transactional
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select c from Contact c").list();
    }

    @Override
    @Transactional
    public List<Contact> getContactsBySqlRestriction(String range) {

        List<Contact> contactList =
                (List<Contact>)sessionFactory.getCurrentSession().createCriteria(Contact.class)
                        .add(Restrictions.sqlRestriction("contact_name REGEXP '^["+range+"]'")).list();

        return contactList;

    }

    @Override
    @Transactional
    public List<Contact> getContactsByRegEx(String range) {

        String sql = "select c from Contact c";
        Query query = sessionFactory.getCurrentSession().createQuery(sql);

        @SuppressWarnings("unchecked")
        List<Contact> contactList = (List<Contact>) query.list();

        Iterator<Contact> iterator = contactList.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();

            if (RestServiceHelper.isStringMatches(contact.getContactName(), range)) {
                iterator.remove();
            }

        }

        return contactList;

    }

}
