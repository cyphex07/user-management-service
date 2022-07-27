package mx.cyphex07.usermngt.controller;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.service.UserService;
import mx.cyphex07.usermngt.api.RestPreconditions;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("signUp")
  @ResponseStatus(HttpStatus.CREATED)
  public void signUp(@Valid @RequestBody User user) {
    RestPreconditions.checkNotNull(user, "User cannot be empty");
    userService.signUp(user);
  }
}
