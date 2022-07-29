package mx.cyphex07.usermngt.publish.listener;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.Notification;
import mx.cyphex07.usermngt.publish.event.OnRegistrationCompleteEvent;
import mx.cyphex07.usermngt.service.UserService;

import java.util.List;

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

  @Value("userapp.notification.email.subject")
  private String subject;

  @Value("userapp.notification.email.message")
  private String message;

  @Override
  public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
    User user = event.getUser();
    log.info("Sending email to the user {}", user.getEmail());
    Notification notification = Notification.builder()
        .isRawHTML(false)
        .subject(subject)
        .message(String.format(message, user.getEmail(), user.getEmail()))
        .recipients(List.of(user.getEmail()))
        .build();

    notificationService.sendNotification(notification);
  }
}
