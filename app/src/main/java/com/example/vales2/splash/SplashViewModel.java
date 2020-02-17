package com.example.vales2.splash;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.vales2.auth.User;

public class SplashViewModel extends AndroidViewModel {

  /**
   * The repository to communicate with firebase.
   */
  private SplashRepository splashRepository;

  /**
   * Data to determine if the user is authenticated.
   */
  LiveData<User> isUserAuthenticatedLiveData;

  /**
   * Class constructor.
   *
   * @param application The owner of this view model.
   */
  public SplashViewModel(Application application) {
    super(application);
    splashRepository = new SplashRepository();
  }

  /**
   * Whether the user is authenticated.
   */
  void checkIfUserIsAuthenticated() {
    isUserAuthenticatedLiveData = splashRepository.checkIfUserIsAuthenticatedInFirebase();
  }
}
