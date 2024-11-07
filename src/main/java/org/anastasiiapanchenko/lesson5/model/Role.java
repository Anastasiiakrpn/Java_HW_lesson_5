package org.anastasiiapanchenko.lesson5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    /**
     * Unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     */
    private String name;

    /**
     * The users associated with this role.
     */
    @ManyToMany
    private Set<User> users;

    /**
     * Constructor to create a Role with a given name.
     *
     * @param roleName The name of the role.
     */
    public Role(final String roleName) {
        this.name = roleName;
    }
}
