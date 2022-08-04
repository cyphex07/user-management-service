package mx.cyphex07.usermngt.service;

import mx.cyphex07.usermngt.model.User;

public interface AccountService {

  void passwordRecovery(String email);

  void tokenValidation(String token);

  User updatePassword(User user);
}
