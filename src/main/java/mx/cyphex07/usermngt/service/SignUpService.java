package mx.cyphex07.usermngt.service;

import mx.cyphex07.usermngt.model.User;

public interface SignUpService {

  User signUp(User user);

  void confirmSignUp(final String token);

}
