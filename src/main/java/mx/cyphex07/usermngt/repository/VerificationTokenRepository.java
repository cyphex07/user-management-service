package mx.cyphex07.usermngt.repository;

import mx.cyphex07.usermngt.model.VerificationToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  Optional<VerificationToken> findByToken(String token);

}
