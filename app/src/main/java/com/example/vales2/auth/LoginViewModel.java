package com.example.vales2.auth;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class LoginViewModel extends AndroidViewModel {

  /**
   * Repository used to communicate with firebase.
   */
  private LoginRepository loginRepository;

  /**
   * Live data with user information.
   */
  LiveData<User> authenticatedUserLiveData;

  /**
   * Class constructor.
   *
   * @param application The owner of this view model.
   */
  public LoginViewModel(Application application) {
    super(application);
    loginRepository = new LoginRepository();
  }

  /**
   * Sign user in to firebase using email and password.
   *
   * @param email The email string to log in.
   * @param password The password string to log in.
   */
  void signIn(String email, String password) {
    authenticatedUserLiveData = loginRepository.firebaseSignIn(email, password);
  }
}
