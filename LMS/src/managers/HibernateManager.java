package managers;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by denislavrov on 9/2/14.
 */
public class HibernateManager {
    private static SessionFactory factory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            if (ex.getClass() == JDBCConnectionException.class){
                System.err.println("Could not connect to the database.");
            } else {
                System.err.println("Initial SessionFactory creation failed." + ex);
            }
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession(){
        return factory.openSession();
    }

    public class AutoTransaction implements AutoCloseable{
        public Session session = factory.openSession();
        public Transaction tx;
        public AutoTransaction() {
            try{
                tx = session.beginTransaction();
            }
            catch (HibernateException e){
                if (tx!=null) tx.rollback();
                e.printStackTrace();
                session.close();
            }
        }

        @Override
        public void close(){
              session.close();
        }
    }

    public class AutoSession implements AutoCloseable{
        public Session session = factory.openSession();
        @Override
        public void close(){
            session.close();
        }
    }

    public void TaskRunner(Runnable task){
        try (AutoTransaction at = new AutoTransaction()) {
            task.run();
        }
    }

    public Serializable addEntity(Object object) {
        Serializable ret = null;
        try (AutoTransaction at = new AutoTransaction()) {
            ret = at.session.save(object);
            at.tx.commit();
        } catch (MappingException e) { // catch errors when user passes wrong objects
            System.err.println(object.getClass() + " Is not compatible with the database.");
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public <E> E getEntity(Class<E> eClass, Serializable key){
        try(AutoSession as = new AutoSession()){
            return (E) as.session.get(eClass, key);
        }
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> listEntities(Class<E> entity){
        List<E> entities = null;
        try(AutoSession as = new AutoSession()){
            entities = (List<E>)as.session.createCriteria(entity).list();
        }

        return entities;
    }

    public void deleteEntities(Object... objects){
        try (AutoTransaction at = new AutoTransaction()) {
            for(Object object : objects) at.session.delete(object);
            at.tx.commit();
        } catch (MappingException e) { // catch errors when user passes wrong objects
            System.err.println(objects[0].getClass() + " Is not compatible with the database.");
        }
    }

    public Object executeQuery(String query){
        try(AutoSession as = new AutoSession()){
            return as.session.createQuery(query).list();
        }
    }

    public AutoSession newAutoSession(){
        return new AutoSession();
    }

    public AutoTransaction newAutoTransaction(){
        return new AutoTransaction();
    }
}
