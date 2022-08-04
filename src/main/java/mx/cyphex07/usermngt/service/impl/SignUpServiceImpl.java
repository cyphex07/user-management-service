package mx.cyphex07.usermngt.service.impl;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.exception.InvalidTokenConfirmationException;
import mx.cyphex07.usermngt.exception.UserAlreadyExistException;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.model.VerificationToken;
import mx.cyphex07.usermngt.publish.event.OnRegistrationCompleteEvent;
import mx.cyphex07.usermngt.repository.UserRepository;
import mx.cyphex07.usermngt.repository.VerificationTokenRepository;
import mx.cyphex07.usermngt.service.SignUpService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

  private final UserRepository userRepository;
  private final ApplicationEventPublisher eventPublisher;
  private final VerificationTokenRepository tokenRepository;

  @Value("${expirationInMinutes}")
  private int expirationInMinutes;

  @Override
  public User signUp(User user) {
    userRepository.findByEmail(user.getEmail())
        .ifPresent(this::userAlreadyExist);

    user = userRepository.save(user);
    eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));

    return user;
  }

  @Override
  public void confirmSignUp(final String token) {
    VerificationToken verificationToken = tokenRepository.findByToken(token)
        .orElseThrow(this::invalidTokenConfirmationException);

    if (verificationToken.isExpired(expirationInMinutes))
      throw new InvalidTokenConfirmationException("Invalid confirmation token");

    User user = verificationToken.getUser();
    user.setEnabled(true);
    userRepository.save(user);
  }

  private void userAlreadyExist(final User user) {
    throw new UserAlreadyExistException(String.format("User %s already exists", user.getEmail()));
  }

  private InvalidTokenConfirmationException invalidTokenConfirmationException() {
    throw new InvalidTokenConfirmationException("Invalid confirmation token");
  }

}
