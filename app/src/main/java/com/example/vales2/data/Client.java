package com.example.vales2.data;

import com.google.firebase.database.Exclude;

public class Client {

  /**
   * The unique identifier.
   */
  private String id;

  /**
   * The label of the client.
   */
  private String name;

  /**
   * Whether is active or blocked.
   */
  private boolean active;

  /**
   * The phone number to communicate with client.
   */
  private String phoneNumber;

  /**
   * The address of client.
   */
  private String address;

  /**
   * The owner of this client.
   */
  private String userId;

  /**
   * Clients's empty constructor.
   */
  public Client() {}

  /**
   * Client's constructor.
   *
   * @param name The label of the client.
   * @param active Whether is active of blocked.
   */
  public Client(String name, boolean active, String address, String phoneNumber, String userId) {
    setName(name);
    setActive(active);
    setAddress(address);
    setPhoneNumber(phoneNumber);
    setUserId(userId);
  }

  /**
   * Get the client's id.
   *
   * @return The unique identifier.
   */
  @Exclude
  public String getId() {
    return id;
  }

  /**
   * Set the client's id.
   *
   * @param id The unique identifier.
   * @return The current instance of this client.
   */
  public Client setId(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get the client's name.
   *
   * @return The label of the client.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the client's name.
   *
   * @param name The label of the client.
   * @return The current instance of this client.
   */
  public Client setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get the client's active.
   *
   * @return Whether is active or blocked.
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Alias for negating isActive method.
   *
   * @return Whether is blocked or active.
   */
  @Exclude
  public boolean isBlocked() {
    return !isActive();
  }

  /**
   * Set the client's active.
   *
   * @param active Whether is active or blocked.
   * @return The current instance of this client.
   */
  public Client setActive(boolean active) {
    this.active = active;
    return this;
  }

  /**
   * Get the client's phone number.
   *
   * @return The phone number to communicate with client.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Set the client's phone number.
   *
   * @param phoneNumber The phone number to communicate with client.
   * @return The current instance of this client.
   */
  public Client setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get the client's address.
   *
   * @return The address of client.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Set the client's address.
   *
   * @param address The address of client.
   * @return The current instance of this client.
   */
  public Client setAddress(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get the client's user id.
   *
   * @return The owner of this client.
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Set the client's user id.
   *
   * @param userId The owner of this client.
   * @return The current instance of this client.
   */
  public Client setUserId(String userId) {
    this.userId = userId;
    return this;
  }

}
