package mx.cyphex07.usermngt.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiError {

  public int code;
  public String message;
  private String developerMessage;

}
