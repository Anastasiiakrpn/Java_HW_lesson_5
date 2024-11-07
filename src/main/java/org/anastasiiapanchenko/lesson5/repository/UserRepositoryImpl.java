package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.User;
import org.anastasiiapanchenko.lesson5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public final class UserRepositoryImpl implements UserRepository {
    /**
     * SessionFactory instance for creating database sessions.
     */
    private final SessionFactory sessionFactory =
            HibernateUtil.getSessionFactory();

    /**
     * Logger for logging events and errors.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public User create(final User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            handleException(transaction, e);
            LOGGER.error("Error occurred while creating User: {}", user, e);
        }
        return user;
    }

    @Override
    public User update(final User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            handleException(transaction, e);
            LOGGER.error("Error occurred while updating User: {}",
                    user, e);
        }
        return user;
    }

    @Override
    public Optional<User> findById(final Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            LOGGER.error("Error occurred while finding User with ID: {}",
                    id, e);
            return Optional.empty();
        }
    }

    /**
     * Handles exceptions by rolling back the transaction if it's not null.
     *
     * @param transaction the current transaction
     * @param e the exception that was thrown
     */
    private void handleException(final Transaction transaction,
                                 final Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        LOGGER.error("Transaction rolled back due to an error", e);
    }

}


