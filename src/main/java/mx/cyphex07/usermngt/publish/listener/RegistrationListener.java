package mx.cyphex07.usermngt.publish.listener;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.Notification;
import mx.cyphex07.usermngt.publish.event.OnRegistrationCompleteEvent;
import mx.cyphex07.usermngt.service.UserService;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final UserService userService;
  private final NotificationService notificationService;

  @Value("${userapp.notification.email.subject}")
  private String subject;

  @Value("${userapp.notification.email.message}")
  private String message;

  @Value("${userapp.notification.email.sender}")
  private String sender;

  @Override
  public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
    log.info("Executing application event: OnRegistrationCompleteEvent");

    User user = event.getUser();

    // token
    final String token = UUID.randomUUID().toString();
    userService.saveSignUpToken(user, token);
    StringBuilder confirmationURL = new StringBuilder(event.getUrl())
        .append("/")
        .append("api/v1/signUp/confirmation")
        .append("?")
        .append("token=")
        .append(token);

    Notification notification = Notification.builder()
        .sender(sender)
        .subject(subject)
        .message(String.format(message, user.getEmail(), confirmationURL.toString()))
        .recipients(List.of(user.getEmail()))
        .build();

    notificationService.sendNotification(notification);
  }
}
