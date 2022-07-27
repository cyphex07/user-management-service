package mx.cyphex07.usermngt.authentication;

import mx.cyphex07.usermngt.authentication.model.AuthenticationResponse;
import mx.cyphex07.usermngt.authentication.model.LoginDto;
import mx.cyphex07.usermngt.authentication.model.UserDto;
import mx.cyphex07.usermngt.model.User;

public interface AuthenticationService {

  AuthenticationResponse signIn(LoginDto login);

  UserDto signUp(User user);

  void logOut(String username);
}
