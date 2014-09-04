package HibernateManager;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
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

    public void addEntity(Object object) {
        try (AutoTransaction at = new AutoTransaction()) {
            at.session.save(object);
            at.tx.commit();
        } catch (MappingException e) { // catch errors when user passes wrong objects
            System.err.println(object.getClass() + " Is not compatible with the database.");
        }
    }

    public List listEntities(Class entity){
        List entities = null;
        try(AutoSession as = new AutoSession()){
            entities = as.session.createQuery("from " + entity.getSimpleName()).list();
        }

        return entities;
    }

    public void deleteEntity(Object object){
        try (AutoTransaction at = new AutoTransaction()) {
            at.session.delete(object);
            at.tx.commit();
        } catch (MappingException e) { // catch errors when user passes wrong objects
            System.err.println(object.getClass() + " Is not compatible with the database.");
        }
    }

    public AutoSession newAutoSession(){
        return new AutoSession();
    }

    public AutoTransaction newAutoTransaction(){
        return new AutoTransaction();
    }
}
