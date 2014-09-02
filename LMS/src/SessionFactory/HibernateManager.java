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

    public class AutoSession implements AutoCloseable{
        public Session session = factory.openSession();
        public Transaction tx;
        public AutoSession() {
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

    public void addEntity(Object object) {
        try (AutoSession as = new AutoSession()) {
            as.session.save(object);
            as.tx.commit();
        } catch (MappingException e) {
            System.err.println(object.getClass() + " Is not compatible with the database.");
        }
    }
}
