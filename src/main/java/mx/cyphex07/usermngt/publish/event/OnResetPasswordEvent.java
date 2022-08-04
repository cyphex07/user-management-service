package mx.cyphex07.usermngt.publish.event;

import lombok.Getter;
import mx.cyphex07.usermngt.model.User;

import org.springframework.context.ApplicationEvent;

public class OnResetPasswordEvent extends ApplicationEvent {

  @Getter
  private final User user;

  @Getter
  private final String url;

  public OnResetPasswordEvent(final User user, String url) {
    super(user);
    this.user = user;
    this.url = url;
  }


}
