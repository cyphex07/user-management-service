package mx.cyphex07.usermngt.controller;

import lombok.RequiredArgsConstructor;
import mx.cyphex07.usermngt.model.User;
import mx.cyphex07.usermngt.service.AccountService;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping("recovery")
  @ResponseStatus(HttpStatus.OK)
  public void passwordRecovery(@Valid @RequestParam("email") String email) {
    accountService.passwordRecovery(email);
  }

  @GetMapping("tokenValidation")
  @ResponseStatus(HttpStatus.OK)
  public void confirmation(@Valid @RequestParam("token") String token) {
    accountService.tokenValidation(token);
  }

  @PutMapping("password")
  @ResponseStatus(HttpStatus.OK)
  public User update(@Valid @RequestBody User user) {
    return accountService.updatePassword(user);
  }
}
