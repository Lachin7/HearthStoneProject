package resLoader.database;

import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import server.Server;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

public class DataBase {

    @Getter
    private SessionFactory sessionFactory = buildSessionFactory();
    private Server server;

    private static SessionFactory buildSessionFactory() {
        PrintStream err = System.err;
        try {
            PrintStream printStream = new PrintStream(new File("./databaseLog.txt"));
            System.setErr(printStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        System.setErr(err);
        return sessionFactory;
    }

    public DataBase(){
    }
    public DataBase(Server server){
       this.server= server;
    }
    public synchronized void save(Object o) {
        try {
            Session session = sessionFactory.openSession();
//        session.clear();
            session.beginTransaction();
            session.saveOrUpdate(o);
            session.getTransaction().commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            server.handleHibernateException();
        }
    }

    public  <T> T fetch(Class<T> tClass, Serializable id) {
        try{
            Session session = sessionFactory.openSession();
            T t = session.get(tClass, id);
            session.close();
            return t;
        } catch (Exception e) {
            server.handleHibernateException();
        }
        return null;
    }

    public  void update(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }

    public  void delete(Object o) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            server.handleHibernateException();
        }
    }

    public  <E> List<E> fetchAll(Class<E> entity) {
        try {
            Session session = sessionFactory.openSession();
            List<E> list = session.createQuery("from " + entity.getName(), entity).getResultList();
            session.close();
            return list;
        } catch (HibernateException e) {
            server.handleHibernateException();
        }
        return null;
    }

    public <E> List<E> fetchWithCondition(Class<E> entity, String fieldName, Object value) {
        String sqlCode = "from " + entity.getName() + " where " + fieldName + "=" + "'" + value + "'";
        Session session = sessionFactory.openSession();
        List<E> list = session.createQuery(sqlCode, entity).getResultList();
        session.close();
        return list;
    }




}
