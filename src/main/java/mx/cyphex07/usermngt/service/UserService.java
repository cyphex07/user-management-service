package mx.cyphex07.usermngt.service;

import mx.cyphex07.usermngt.model.User;

public interface UserService {

  void saveSignUpToken(User user, String token);

  void resetPassword(String email);

}
