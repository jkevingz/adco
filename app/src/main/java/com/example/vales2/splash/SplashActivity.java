package com.example.vales2.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.vales2.MainActivity;
import com.example.vales2.auth.LoginActivity;

public class SplashActivity extends AppCompatActivity {

  /**
   * The view model owned by this activity.
   */
  SplashViewModel splashViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initSplashViewModel();
    checkIfUserIsAuthenticated();
  }

  /**
   * Call the view model's method to determine authentication. If user is
   * authenticated, then it'll be redirected to the main activity. Otherwise,
   * the login activity will be launched.
   */
  protected void checkIfUserIsAuthenticated() {
    splashViewModel.checkIfUserIsAuthenticated();
    splashViewModel.isUserAuthenticatedLiveData.observe(this, user -> {
      Class classToInitiate = user.isAuthenticated() ? MainActivity.class : LoginActivity.class;
      Intent intent = new Intent(this, classToInitiate).addFlags(
        Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP
      );
      startActivity(intent);

      finish();
    });
  }

  /**
   * Initiate the view model that is owned by this activity.
   */
  protected void initSplashViewModel() {
    splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
  }
}
