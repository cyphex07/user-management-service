package mx.cyphex07.usermngt.publish.event;

import lombok.Getter;
import mx.cyphex07.usermngt.model.User;

import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

  @Getter
  private final User user;

  public OnRegistrationCompleteEvent(final User user) {
    super(user);
    this.user = user;
  }
}
