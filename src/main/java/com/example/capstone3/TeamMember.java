package com.example.capstone3;

public class TeamMember {
  private String name;
  private String role;
  private String image;
  private String link;

  public TeamMember(String name, String role, String image, String link) {
    this.name = name;
    this.role = role;
    this.image = image;
    this.link = link;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
