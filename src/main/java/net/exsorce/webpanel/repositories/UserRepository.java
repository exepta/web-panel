package net.exsorce.webpanel.repositories;

import java.util.Optional;

import net.exsorce.webpanel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
public interface UserRepository extends JpaRepository< User, Integer >
{

	Optional<User> findByEmail(String username);

}
