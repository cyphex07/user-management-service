package mx.cyphex07.usermngt.service.impl;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.service.UserService;
import mx.cyphex07.usermngt.exception.UserAlreadyExistException;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.publish.event.OnRegistrationCompleteEvent;
import mx.cyphex07.usermngt.repository.UserRepository;
import mx.cyphex07.usermngt.repository.VerificationTokenRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final VerificationTokenRepository tokenRepository;
  private final UserRepository userRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public User signUp(User user) {
    userRepository.findByEmail(user.getEmail())
        .ifPresent(this::userAlreadyExist);
    user = userRepository.save(user);
    eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
    return user;
  }

  private void userAlreadyExist(final User user) {
    throw new UserAlreadyExistException(String.format("User %s already exists", user.getEmail()));
  }
}
