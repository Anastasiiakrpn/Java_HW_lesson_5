package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.User;
import java.util.Optional;

public interface UserRepository {
    /**
     * Creates a new user in the database.
     *
     * @param user the user to be created
     * @return the created user
     */
    User create(User user);


    /**
     * Updates an existing user in the database.
     *
     * @param user the user to be updated
     * @return the updated user
     */
    User update(User user);

    /**
     * Finds a user by its ID.
     *
     * @param id the ID of the user to be found
     * @return an Optional containing the found user,
     * or an empty Optional if not found
     */
    Optional<User> findById(Long id);
}
