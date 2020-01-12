package logic;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try {
            File tmpDir = new File("C:/Users/Manol/IdeaProjects/Beast/resources/hibernate.cfg.xml");
            boolean exists = tmpDir.exists();
            if(exists){
                return new AnnotationConfiguration().configure(
                        new File("C:/Users/Manol/IdeaProjects/Beast/resources/hibernate.cfg.xml")).buildSessionFactory();

            }else{
                return new AnnotationConfiguration().configure(
                        new File("C:/Users/manolo/IdeaProjects/Boodschappenlijst/resources/hibernate.cfg.xml")).buildSessionFactory();
            }
            // Create the SessionFactory from hibernate.cfg.xml


        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
