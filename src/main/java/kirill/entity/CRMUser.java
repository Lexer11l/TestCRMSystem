package kirill.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CRMUser {

  @NotNull(message = "is required")
  @Size(min=1, message = "is required")
  private String username;


  @NotNull(message = "is required")
  @Size(min=1, message = "is required")
  private String password;


  public CRMUser() {
  }

  public CRMUser(@NotNull(message = "is required") @Size(min = 1, message = "is required") String username, @NotNull(message = "is required") @Size(min = 1, message = "is required") String password) {
    this.username = username;
    this.password = password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

}
