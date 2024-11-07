package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.Role;
import org.anastasiiapanchenko.lesson5.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Repository for managing roles in the database.
 */
public final class RoleRepositoryImpl implements RoleRepository {
    /**
     * SessionFactory instance for creating database sessions.
     */
    private final SessionFactory sessionFactory =
            HibernateUtil.getSessionFactory();

    /**
     * Logger for logging events and errors.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(RoleRepositoryImpl.class);

    /**
     * Creates a new role and saves it to the database.
     *
     * This method is intended to be overridden by subclasses if custom behavior
     * is needed when creating roles. Ensure that the transaction handling logic
     * is maintained when overriding this method.
     *
     * @param role the role to be created
     * @return the created role
     * @throws RuntimeException if the operation fails
     */
    @Override
    public Role create(final Role role) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            handleException(transaction, e);
            LOGGER.error("Error occurred while creating role: {}", role, e);
        }
        return role;
    }

    /**
     * Finds a role by its ID.
     *
     * @param id the ID of the role to find
     * @return an Optional containing the found role
     *         or an empty Optional if not found
     */
    @Override
    public Optional<Role> findById(final Long id) {
        try (Session session = sessionFactory.openSession()) {
            Role role = session.get(Role.class, id);
            return Optional.ofNullable(role);
        } catch (Exception e) {
            LOGGER.error("Error occurred while finding Role with ID: {}",
                    id, e);
            return Optional.empty();
        }
    }

    private void handleException(final Transaction transaction,
                                 final Exception e) {
        if (transaction != null) {
            transaction.rollback();
            LOGGER.error("Transaction rolled back due to an error", e);
        }
    }
}
