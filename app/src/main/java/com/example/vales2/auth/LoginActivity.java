package com.example.vales2.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.vales2.MainActivity;
import com.example.vales2.R;
import static com.example.vales2.auth.utils.HelperClass.*;

public class LoginActivity extends AppCompatActivity {

  /**
   * Email input edit text.
   */
  private EditText emailInput;

  /**
   * Password input edit text.
   */
  private EditText passwordInput;

  /**
   * View model of this activity.
   */
  private LoginViewModel loginViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    initComponents();
  }

  /**
   * Sign user in to firebase using email and password. This will redirect the
   * user to the main activity on successful response.
   */
  protected void signIn() {
    loginViewModel.signIn(emailInput.getText().toString(), passwordInput.getText().toString());
    loginViewModel.authenticatedUserLiveData.observe(this, user -> {
      if (user.isAuthenticated()) {
        Intent intent = new Intent(this, MainActivity.class).addFlags(
          Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP
        );
        startActivity(intent);

        finish();
      }
      else {
        // @todo: Better managing errors.
        showToast(this, user.getReason());
      }
    });
  }

  /**
   * Init components of this activity:
   * - Login button.
   * - Email edit text.
   * - Password edit text.
   * - Login view model.
   */
  protected void initComponents() {
    Button loginButton = findViewById(R.id.button_login);
    loginButton.setOnClickListener(v -> signIn());

    emailInput = findViewById(R.id.input_email);

    passwordInput = findViewById(R.id.input_password);

    loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
  }
}
