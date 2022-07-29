package mx.cyphex07.usermngt.config;

import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.NotificationProvider;
import mx.cyphex07.usermngt.notification.impl.DummyNotificationService;
import mx.cyphex07.usermngt.notification.impl.GmailNotificationService;
import mx.cyphex07.usermngt.notification.impl.SESNotificationService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

  @Value("${userapp.notification.provider}")
  private NotificationProvider notificationProvider;

  @Bean
  public NotificationService notificationService() {
    return switch (notificationProvider) {
      case GMAIL ->new GmailNotificationService();
      case AWS_SES -> new SESNotificationService();
      default -> new DummyNotificationService();
    };
  }
}
