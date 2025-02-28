public class User {

  private Integer id;
  private String email;
  private String first_name;
  private String last_name;
  private String avatar;

  public User(Integer id, String email, String first_name, String last_name, String avatar) {
    this.id = id;
    this.email = email;
    this.first_name = first_name;
    this.last_name = last_name;
    this.avatar = avatar;
  }

  public User() {
    super();
  }


  public String getEmail() {
    return email;
  }

  public Integer getId() {
    return id;
  }

  public String getAvatar() {
    return avatar;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getLast_name() {
    return last_name;
  }
}
