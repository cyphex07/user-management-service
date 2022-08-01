package mx.cyphex07.usermngt.service;

import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.model.VerificationToken;

import java.util.Optional;

public interface UserService {

  User signUp(User user);

  void saveSignUpToken(User user, String token);

  void confirmSignUp(String token);

}
