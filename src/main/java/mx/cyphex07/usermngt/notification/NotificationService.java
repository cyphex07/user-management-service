package mx.cyphex07.usermngt.notification;

import mx.cyphex07.usermngt.notification.dto.Notification;

public interface NotificationService {

  void sendNotification(Notification notification);

}
