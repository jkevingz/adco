package com.example.vales2.auth.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import static com.example.vales2.auth.utils.Constants.*;

public class HelperClass {

  /**
   * Add message to the log.
   *
   * @param errorMessage The error message to log.
   */
  public static void logErrorMessage(String errorMessage) {
    Log.d(TAG, errorMessage);
  }

  /**
   * Show a toast to the user.
   *
   * @param context Context where the toast is shown.
   * @param message The message to display.
   * @param duration The duration the toast will have.
   */
  public static void showToast(Context context, String message, int duration) {
    Toast.makeText(context, message, duration).show();
  }

  /**
   * Show a toast to the user. Default duration to short.
   *
   * @param context Context where the toast is shown.
   * @param message The message to display.
   */
  public static void showToast(Context context, String message) {
    showToast(context, message, Toast.LENGTH_SHORT);
  }
}
