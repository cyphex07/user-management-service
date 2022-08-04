package mx.cyphex07.usermngt.service.impl;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.api.RestPreconditions;
import mx.cyphex07.usermngt.exception.InvalidTokenConfirmationException;
import mx.cyphex07.usermngt.exception.ResourceNotFoundException;
import mx.cyphex07.usermngt.model.VerificationToken;
import mx.cyphex07.usermngt.repository.PasswordTokenRepository;
import mx.cyphex07.usermngt.service.UserService;
import mx.cyphex07.usermngt.exception.UserAlreadyExistException;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.publish.event.OnRegistrationCompleteEvent;
import mx.cyphex07.usermngt.repository.UserRepository;
import mx.cyphex07.usermngt.repository.VerificationTokenRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final VerificationTokenRepository tokenRepository;
  private final UserRepository userRepository;
  private final PasswordTokenRepository passwordTokenRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Value("${userapp.server.url.app}")
  private String serverUrl;

  @Value("${expirationInMinutes}")
  private int expirationInMinutes;

  @Override
  public void saveSignUpToken(final User user, final String token) {
    final VerificationToken myToken = new VerificationToken(token, user, expirationInMinutes);
    tokenRepository.save(myToken);
  }

  @Override
  public void resetPassword(final String email) {
    RestPreconditions.checkFound(userRepository.findByEmail(email)
        .isEmpty(), "Account not found.");

  }

}
