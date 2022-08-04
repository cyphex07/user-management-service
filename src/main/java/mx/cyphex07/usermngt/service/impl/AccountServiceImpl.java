package mx.cyphex07.usermngt.service.impl;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.exception.ResourceNotFoundException;
import mx.cyphex07.usermngt.exception.UserAlreadyExistException;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.publish.event.OnResetPasswordEvent;
import mx.cyphex07.usermngt.repository.PasswordTokenRepository;
import mx.cyphex07.usermngt.service.AccountService;
import mx.cyphex07.usermngt.service.UserService;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final UserService userService;
  private final PasswordTokenRepository passwordTokenRepository;
  private final ApplicationEventPublisher eventPublisher;


  @Override
  public void passwordRecovery(final String email) {
    User user = userService.findByEmail(email)
        .orElseThrow(this::userNotFound);

    eventPublisher.publishEvent(new OnResetPasswordEvent(user));
  }

  @Override
  public void tokenValidation(final String token) {

  }

  @Override
  public User updatePassword(final User user) {
    return null;
  }

  private ResourceNotFoundException userNotFound() {
    throw new ResourceNotFoundException("User not found");
  }
}
