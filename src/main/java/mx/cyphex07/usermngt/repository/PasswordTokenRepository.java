package mx.cyphex07.usermngt.repository;

import mx.cyphex07.usermngt.model.PasswordToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {

}
