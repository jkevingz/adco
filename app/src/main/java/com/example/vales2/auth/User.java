package com.example.vales2.auth;

import com.google.firebase.database.Exclude;
import java.io.Serializable;

public class User implements Serializable {

  /**
   * The unique identifier of the user across the application.
   */
  private String uid;

  /**
   * The human label of the user.
   */
  private String name;

  /**
   * The email used to log into the application.
   */
  private String email;

  /**
   * Runtime variable used to get the reason a user was not able to access.
   */
  private String reason;

  /**
   * Runtime variable used to know if a user is authenticated to the app.
   */
  private boolean isAuthenticated;

  /**
   * Generic class constructor.
   */
  public User() {}

  /**
   * Class constructor to have a user with failed authentication.
   *
   * @param reason The reason the user could not access the application.
   */
  public User(String reason) {
    setAuthenticated(false);
    setReason(reason);
  }

  /**
   * General class constructor that allows to set all properties of this class.
   *
   * @param uid The unique identifier of the user across the application.
   * @param name The human label of the user.
   * @param email The email used to log into the application.
   * @param isAuthenticated Runtime variable used to know if a user is
   *                        authenticated to the app.
   */
  public User(String uid, String name, String email, boolean isAuthenticated) {
    setUid(uid);
    setName(name);
    setEmail(email);
    setAuthenticated(isAuthenticated);
  }

  /**
   * Quickly instantiate an unauthenticated user. Alias for the
   * User(String reason) constructor.
   *
   * @param reason The reason the user could not access the application.
   * @return A new instance of this class with failed authentication.
   */
    public static User unauthenticatedUser(String reason) {
      return new User(reason);
    }

  /**
   * Get user's uid.
   *
   * @return The unique identifier of the user across the application.
   */
  public String getUid() {
    return uid;
  }

  /**
   * Set user's uid.
   *
   * @param uid The unique identifier of the user across the application.
   * @return Current instance of the class.
   */
  public User setUid(String uid) {
    this.uid = uid;
    return this;
  }

  /**
   * Get user's name.
   *
   * @return The human label of the user.
   */
  public String getName() {
    return name;
  }

  /**
   * Set user's name.
   *
   * @param name The human label of the user.
   * @return Current instance of the class.
   */
  public User setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get user's email.
   *
   * @return The email used to log into the application.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set user's email.
   *
   * @param email The email used to log into the application.
   * @return Current instance of the class.
   */
  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get user's isAuthenticated.
   *
   * @return Runtime variable used to know if a user is authenticated to the
   * app.
   */
  @Exclude
  public boolean isAuthenticated() {
    return isAuthenticated;
  }

  /**
   * Set user's isAuthenticated.
   *
   * @param authenticated Runtime variable used to know if a user is
   *                      authenticated to the app.
   * @return Current instance of the class.
   */
  public User setAuthenticated(boolean authenticated) {
    isAuthenticated = authenticated;
    return this;
  }

  /**
   * Get user's reason.
   *
   * @return Runtime variable used to get the reason a user was not able to
   * access.
   */
  @Exclude
  public String getReason() {
      return reason;
  }

  /**
   * Set user's reason.
   *
   * @param reason Runtime variable used to get the reason a user was not able
   *               to access.
   * @return Current instance of the class.
   */
  public User setReason(String reason) {
    this.reason = reason;
    return this;
  }
}
