package mx.cyphex07.usermngt.config;

import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.NotificationProvider;
import mx.cyphex07.usermngt.notification.impl.DummyNotificationService;
import mx.cyphex07.usermngt.notification.impl.GmailNotificationService;
import mx.cyphex07.usermngt.notification.impl.SESNotificationService;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.sesv2.SesV2Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class NotificationConfig {

  @Value("${NOTIFICATION_PROVIDER:EMPTY}")
  private String notificationProvider;

  @Autowired
  private JavaMailSender javaMailSender;

  @Value("${AWS_PROFILE}")
  private String awsProfile;

  @Bean
  public NotificationService notificationService() {
    return switch (NotificationProvider.valueOf(notificationProvider)) {
      case GMAIL ->new GmailNotificationService(javaMailSender);
      case AWS_SES -> new SESNotificationService(sesV2Client());
      case EMPTY -> new DummyNotificationService();
    };
  }

  @Bean
  public SesV2Client sesV2Client() {
    return SesV2Client.builder()
        .credentialsProvider(ProfileCredentialsProvider.create(awsProfile))
        .build();
  }

}
