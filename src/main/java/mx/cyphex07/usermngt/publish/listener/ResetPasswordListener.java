package mx.cyphex07.usermngt.publish.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.Notification;
import mx.cyphex07.usermngt.publish.event.OnResetPasswordEvent;
import mx.cyphex07.usermngt.service.UserService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResetPasswordListener implements ApplicationListener<OnResetPasswordEvent> {

  private final UserService userService;
  private final NotificationService notificationService;

  @Value("${userapp.notification.email.recovery.subject}")
  private String subject;

  @Value("${userapp.notification.email.recovery.message}")
  private String message;

  @Value("${userapp.notification.email.sender}")
  private String sender;

  @Value("${userapp.server.url.app}")
  private String serverUrl;

  @Override public void onApplicationEvent(final OnResetPasswordEvent event) {
    log.info("Executing application event: OnResetPasswordEvent");

    User user = event.getUser();

    final String token = UUID.randomUUID().toString();
    userService.savePasswordRecoveryToken(user, token);
    StringBuilder confirmationURL = new StringBuilder(serverUrl)
        .append("/")
        .append("api/v1/account/tokenValidation")
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
