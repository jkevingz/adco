package com.example.vales2;

import android.content.Intent;
import android.os.Bundle;
import com.example.vales2.auth.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.vales2.auth.utils.HelperClass.*;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

  /**
   * App bar configuration.
   */
  private AppBarConfiguration mAppBarConfiguration;

  /**
   * Firebase authentication service.
   */
  private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initNavigation();
  }

  /**
   * Init the navigation components of this activity:
   * - Toolbar.
   * - Drawer navigation.
   * - Bottom navigation.
   * - Log out menu item listener.
   */
  protected void initNavigation() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);

    mAppBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_home,
        R.id.navigation_clients,
        R.id.navigation_companies
      ).setDrawerLayout(drawer).build();

    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);

    BottomNavigationView navView = findViewById(R.id.bottom_nav);
    NavigationUI.setupWithNavController(navView, navController);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.action_logout) {
      firebaseAuth.signOut();
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onStart() {
    super.onStart();
    firebaseAuth.addAuthStateListener(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    firebaseAuth.removeAuthStateListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
  }

  @Override
  public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    if (firebaseUser == null) {
      Intent intent = new Intent(this, LoginActivity.class);
      startActivity(intent);

      finish();
    }
  }

}
