package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.AddressData;
import ru.stqa.pft.addressbook.model.Addresses;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DBHelber {

    private final SessionFactory sessionFactory;

    public DBHelber() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Addresses address() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<AddressData> result = session.createQuery("from AddressData where deprecated= '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Addresses(result);
    }

    public AddressData GetAddressById(int id) {
        Session session = sessionFactory.openSession();
        AddressData addressData = session.get(AddressData.class, id);
        session.close();
        return addressData;
    }

}
