package com.epicenergyservices.u5w4.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(UUID id) {
    super("L'utente con id " + id + " non è stato trovato");
  }

  public UserNotFoundException(String message) {
    super(message);
  }
}
