package SessionFactory;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by denislavrov on 9/2/14.
 */
public class HibernateManager {
    private static final SessionFactory factory;
    private static final ServiceRegistry serviceRegistry;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }


    private static void defTransactionRef(){
        // a little template of code for every interaction with the database
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            // write your code here
            tx.commit();
        } catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addEntity(Object object){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            try {
                session.save(object);
            }
            catch (MappingException e){
                System.err.println(object.getClass() + " Is not compatible with the database.");
            }
            tx.commit();
        }
        catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }

}
