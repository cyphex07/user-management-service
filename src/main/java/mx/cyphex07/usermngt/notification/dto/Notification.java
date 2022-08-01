package mx.cyphex07.usermngt.notification.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class Notification {

  private String subject;
  private String message;
  private List<String> recipients;
  private String sender;
}
