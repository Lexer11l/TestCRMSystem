package smth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
  private String street;
  private String country;
  private String zip;
  private String city;
  private String state;

  public Address() {
  }

  public String getStreet() {
    return street;
  }

  public String getCountry() {
    return country;
  }

  public String getZip() {
    return zip;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "Address{" +
            "street='" + street + '\'' +
            ", country='" + country + '\'' +
            ", zip='" + zip + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            '}';
  }
}
