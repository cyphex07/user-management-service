package mx.cyphex07.usermngt.publish.listener;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.publish.event.OnRegistrationCompleteEvent;
import mx.cyphex07.usermngt.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final UserService userService;

  @Override
  public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
    User user = event.getUser();
    log.info("Sending email to the user {}", user.getEmail());
  }
}
