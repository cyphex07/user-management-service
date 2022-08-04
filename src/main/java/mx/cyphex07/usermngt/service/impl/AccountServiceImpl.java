package mx.cyphex07.usermngt.service.impl;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.repository.PasswordTokenRepository;
import mx.cyphex07.usermngt.service.AccountService;
import mx.cyphex07.usermngt.service.UserService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final UserService userService;
  private final PasswordTokenRepository passwordTokenRepository;


  @Override
  public void passwordRecovery(final String email) {

  }

  @Override
  public void tokenValidation(final String token) {

  }

  @Override
  public User updatePassword(final User user) {
    return null;
  }
}
