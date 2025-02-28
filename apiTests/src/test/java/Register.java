public class Register {

  private String email;
  private String password;

  public Register(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public Register(String email) {
    this.email = email;
  }

  public Register() {
    super();
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }
}
