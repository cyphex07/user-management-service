package mx.cyphex07.usermngt.notification.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.Notification;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.Body;
import software.amazon.awssdk.services.sesv2.model.Content;
import software.amazon.awssdk.services.sesv2.model.Destination;
import software.amazon.awssdk.services.sesv2.model.EmailContent;
import software.amazon.awssdk.services.sesv2.model.Message;
import software.amazon.awssdk.services.sesv2.model.SendEmailRequest;
import software.amazon.awssdk.services.sesv2.model.SesV2Exception;

@Slf4j
@RequiredArgsConstructor
public class SESNotificationService implements NotificationService {

  private final SesV2Client sesV2Client;

  @Override
  public void sendNotification(final Notification notification) {
    log.info("Sending AWS SES notification ....");

    SendEmailRequest sendEmailRequest = buildEmailRequest(notification);

    try {
      sesV2Client.sendEmail(sendEmailRequest);
    } catch (SesV2Exception ex) {
      log.error("There was an error on trying to send email.", ex);
    }

  }

  public SendEmailRequest buildEmailRequest(Notification notification) {
    Destination destination = Destination.builder()
        .toAddresses(notification.getRecipients())
        .build();

    Content content = Content.builder()
        .data(notification.getMessage())
        .build();

    Content sub = Content.builder()
        .data(notification.getSubject())
        .build();

    Body body = Body.builder()
        .html(content)
        .build();

    Message msg = Message.builder()
        .subject(sub)
        .body(body)
        .build();

    EmailContent emailContent = EmailContent.builder()
        .simple(msg)
        .build();

    return SendEmailRequest.builder()
        .destination(destination)
        .content(emailContent)
        .fromEmailAddress(notification.getSender())
        .build();
  }
}
