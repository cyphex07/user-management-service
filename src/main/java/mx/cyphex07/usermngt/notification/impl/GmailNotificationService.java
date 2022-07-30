package mx.cyphex07.usermngt.notification.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.Notification;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
@RequiredArgsConstructor
public class GmailNotificationService implements NotificationService {


  private final JavaMailSender mailSender;

  @Override
  public void sendNotification(final Notification notification) {
    log.info("Sending Gmail notification ....");
    final SimpleMailMessage message = buildEmailMessage(notification.getRecipients(), notification);
    mailSender.send(message);
  }

  private SimpleMailMessage buildEmailMessage(List<String> emails, Notification notification) {
    SimpleMailMessage emailMessage = new SimpleMailMessage();
    emailMessage.setTo(emails.toArray(new String[emails.size()]));
    emailMessage.setSubject(notification.getSubject());
    emailMessage.setText(notification.getMessage());
    emailMessage.setFrom(notification.getSender());
    return emailMessage;
  }
}
