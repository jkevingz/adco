package com.example.vales2.data;

import androidx.annotation.NonNull;
import com.google.firebase.database.Exclude;

public class Client {

  /**
   * The unique identifier.
   */
  public String id;

  /**
   * The label of the client.
   */
  public String name;

  /**
   * Whether is active or blocked.
   */
  public boolean active;

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
  public Client(String name, boolean active) {
    setName(name);
    setActive(active);
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
}
