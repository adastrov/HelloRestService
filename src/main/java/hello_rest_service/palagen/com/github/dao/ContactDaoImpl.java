package hello_rest_service.palagen.com.github.dao;

import hello_rest_service.palagen.com.github.model.Contact;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Cacheable("contacts")
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select c from Contact c").list();

    }

}
