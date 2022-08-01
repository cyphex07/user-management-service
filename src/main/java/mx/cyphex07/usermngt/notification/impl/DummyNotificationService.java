package mx.cyphex07.usermngt.notification.impl;

import lombok.extern.slf4j.Slf4j;
import mx.cyphex07.usermngt.notification.NotificationService;
import mx.cyphex07.usermngt.notification.dto.Notification;

@Slf4j
public class DummyNotificationService implements NotificationService {

  @Override
  public void sendNotification(final Notification notification) {
    log.info("Sending dummy notification ....");
    log.info(notification.toString());
  }
}
