package net.exsorce.webpanel.repositories;

import java.util.List;
import java.util.Optional;

import net.exsorce.webpanel.repositories.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @since 0.0.1-SNAPSHOT
 * @author Daniel Ramke
 */
public interface TokenRepository extends JpaRepository< Token, Integer>
{

	@Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
	List<Token> findAllValidTokenByUser(Integer id);

	Optional<Token> findByContent(String content);
}
