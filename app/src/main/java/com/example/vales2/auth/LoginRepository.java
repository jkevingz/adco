package com.example.vales2.auth;

import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;
import static com.example.vales2.auth.utils.HelperClass.*;

class LoginRepository {
  /**
   * The firebase authentication service.
   */
  private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

  /**
   * The mutable data with the user information.
   */
  private MutableLiveData<User> authenticatedUserMutableLiveData = new MutableLiveData<>();

  /**
   * Sign user in to firebase using email and password.
   *
   * @param email The email string to log in.
   * @param password The password string to log in.
   * @return The mutable data with the user information.
   */
  MutableLiveData<User> firebaseSignIn(String email, String password) {
    firebaseAuth.signInWithEmailAndPassword(email, password)
      .addOnSuccessListener(this::onSuccess)
      .addOnFailureListener(this::onFailure);

    return authenticatedUserMutableLiveData;
  }

  /**
   * On success callback. Instantiate user using info gotten from result.
   *
   * @param authResult The auth result given by firebase.
   */
  private void onSuccess(@NotNull AuthResult authResult) {
    FirebaseUser firebaseUser = authResult.getUser();
    User user = new User(
      firebaseUser.getUid(),
      firebaseUser.getDisplayName(),
      firebaseUser.getEmail(),
      true
    );
    authenticatedUserMutableLiveData.setValue(user);
  }

  /**
   * On failure callback. Instantiate user with reason log in failed.
   * @param e Exception caught with failure reason.
   */
  private void onFailure(@NotNull Exception e) {
    User user = User.unauthenticatedUser(e.getMessage());
    authenticatedUserMutableLiveData.setValue(user);
    logErrorMessage(e.getMessage());
  }
}
