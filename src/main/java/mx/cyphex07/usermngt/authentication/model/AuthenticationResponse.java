package mx.cyphex07.usermngt.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {

  private String accessToken;
  private String tokenType;
  private Integer expiresIn;
  private String scope;
}
