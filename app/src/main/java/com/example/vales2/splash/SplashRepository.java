package com.example.vales2.splash;

import androidx.lifecycle.MutableLiveData;
import com.example.vales2.auth.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

class SplashRepository {

  /**
   * The firebase authentication service.
   */
  private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

  /**
   * Determine if user is logged in.
   *
   * @return mutable live data with a user model that has information about
   * whether or not the user is authenticated.
   */
  MutableLiveData<User> checkIfUserIsAuthenticatedInFirebase() {
    MutableLiveData<User> isUserAuthenticateInFirebaseMutableLiveData = new MutableLiveData<>();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    User user = new User();
    if (firebaseUser == null) {
      user.setAuthenticated(false);
    }
    else {
      user.setUid(firebaseUser.getUid()).setAuthenticated(true);
    }
    isUserAuthenticateInFirebaseMutableLiveData.setValue(user);

    return isUserAuthenticateInFirebaseMutableLiveData;
  }
}
