package mx.cyphex07.usermngt.config;

import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.NotificationProvider;
import mx.cyphex07.usermngt.notification.impl.DummyNotificationService;
import mx.cyphex07.usermngt.notification.impl.GmailNotificationService;
import mx.cyphex07.usermngt.notification.impl.SESNotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class NotificationConfig {

  @Value("${userapp.notification.provider}")
  private NotificationProvider notificationProvider;

  @Autowired
  private JavaMailSender javaMailSender;

  @Bean
  public NotificationService notificationService() {
    return switch (notificationProvider) {
      case GMAIL ->new GmailNotificationService(javaMailSender);
      case AWS_SES -> new SESNotificationService();
      default -> new DummyNotificationService();
    };
  }

}
