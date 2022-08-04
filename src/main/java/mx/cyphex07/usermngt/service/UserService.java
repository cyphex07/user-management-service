package mx.cyphex07.usermngt.service;

import mx.cyphex07.usermngt.model.User;

import java.util.Optional;

public interface UserService {

  void saveSignUpToken(User user, String token);

  void savePasswordRecoveryToken(User user, String token);

  Optional<User> findByEmail(String email);

}
