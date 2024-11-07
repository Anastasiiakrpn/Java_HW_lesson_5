package org.anastasiiapanchenko.lesson5.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    /**
     * The SessionFactory used to create sessions for database interactions.
     */
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {

    }

    /**
     * Initializes the SessionFactory using Hibernate's Configuration.
     *
     * @return a configured SessionFactory instance
     */
    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets the Hibernate SessionFactory.
     *
     * @return the SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
