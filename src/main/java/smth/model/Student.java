package smth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student   {
  private int id;
  private String firstName;
  private String lastName;
  private String company;
  private boolean active;
  private Address address;
  private String[] languages;

  public Student() {
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean isActive() {
    return active;
  }

  public Address getAddress() {
    return address;
  }

  public String[] getLanguages() {
    return languages;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setLanguages(String[] languages) {
    this.languages = languages;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", company='" + company + '\'' +
            ", active=" + active +
            ", address=" + address +
            ", languages=" + Arrays.toString(languages) +
            '}';
  }
}
