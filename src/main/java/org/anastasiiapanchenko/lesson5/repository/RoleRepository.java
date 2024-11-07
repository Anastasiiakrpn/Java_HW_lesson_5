package org.anastasiiapanchenko.lesson5.repository;

import org.anastasiiapanchenko.lesson5.model.Role;
import java.util.Optional;

public interface RoleRepository {
    /**
     * Creates a new role in the system.
     *
     * @param role the role to be created
     * @return the created role
     */
    Role create(Role role);

    /**
     * Finds a role by its ID.
     *
     * @param id the ID of the role to find
     * @return an Optional containing the found role,
     * or an empty Optional if not found
     */
    Optional<Role> findById(Long id);
}
