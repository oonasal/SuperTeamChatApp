package main;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author Oona
 */
public class HibernateUtil {

    private static final HibernateUtil instance = new HibernateUtil();
    private SessionFactory sessionFactory;

    private HibernateUtil() {

        Configuration config = new Configuration();
        config.addAnnotatedClass(models.User.class);
        config.addAnnotatedClass(models.Alert.class);
        config.addAnnotatedClass(models.Message.class);
        config.addAnnotatedClass(models.Profile.class);
        config.addAnnotatedClass(models.Task.class);
        config.addAnnotatedClass(models.User.class);
        config.configure();

        new SchemaExport(config).create(true, true);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(config.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

        this.sessionFactory = config.buildSessionFactory(serviceRegistry);

    }
    
    public static synchronized SessionFactory getSessionFactory() {
        return instance.sessionFactory;
    }

}
