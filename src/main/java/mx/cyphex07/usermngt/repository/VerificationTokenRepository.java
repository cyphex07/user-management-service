package mx.cyphex07.usermngt.repository;

import mx.cyphex07.usermngt.model.VerificationToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);

  VerificationToken findByUserId(Long userId);

}
