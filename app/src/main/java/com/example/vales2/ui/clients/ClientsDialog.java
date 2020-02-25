package com.example.vales2.ui.clients;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.vales2.R;
import com.google.android.material.textfield.TextInputEditText;

public class ClientsDialog extends AppCompatDialogFragment {

  /**
   * The dialog listener that is triggered on successful submission.
   */
  private ClientsDialogListener listener;

  /**
   * The input to set the client's name.
   */
  private TextInputEditText editTextName;

  /**
   * The input to set the client's address.
   */
  private TextInputEditText editTextAddress;

  /**
   * The input to set the client's phone number.
   */
  private TextInputEditText editTextPhoneNumber;

  /**
   * Initialize the name, address, and phone number inputs.
   *
   * @param view The view to get the view element inputs.
   */
  private void initInputs(View view) {
    editTextName = view.findViewById(R.id.dialog_add_client_name_input);
    editTextAddress = view.findViewById(R.id.dialog_add_client_address_input);
    editTextPhoneNumber = view.findViewById(R.id.dialog_add_client_phone_input);
  }

  /**
   * Perform basic validation on the inputs.
   *
   * @param name The client's name.
   * @param address The client's address.
   * @param phoneNumber The client's phone number.
   * @return True if none of the fields are empty, false otherwise.
   */
  private boolean validateNewClient(String name, String address, String phoneNumber) {
    boolean hasErrors = false;
    if (name.equals("")) {
      hasErrors = true;
      editTextName.setError(getString(R.string.client_add_name_error));
    }
    if (address.equals("")) {
      hasErrors = true;
      editTextAddress.setError(getString(R.string.client_add_address_error));
    }
    if (phoneNumber.equals("")) {
      hasErrors = true;
      editTextPhoneNumber.setError(getString(R.string.client_add_phone_number_error));
    }

    return hasErrors;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);

    if (context instanceof ClientsDialogListener) {
      listener = (ClientsDialogListener) context;
    }
    else if(getTargetFragment() != null) {
      listener = (ClientsDialogListener) getTargetFragment();
    }
  }

  /**
   * {@inheritDoc}
   */
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_add_client, null);

    initInputs(view);

    builder.setView(view).setTitle(getString(R.string.new_client))
      .setNegativeButton(getString(R.string.cancel), null)
      .setPositiveButton(getString(R.string.save), null);

    // Annoyingly, the default behavior of any button (including the ok button)
    // is to dismiss the modal on click. This default listener needs to be
    // overridden so that it shows the errors on the input when the validation
    // fails.
    AlertDialog dialog = builder.create();
    dialog.show();
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(l -> {
      String name = editTextName.getText().toString();
      String address = editTextAddress.getText().toString();
      String phoneNumber = editTextPhoneNumber.getText().toString();

      if (validateNewClient(name, address, phoneNumber)) {
        return;
      }

      dismiss();
      listener.onSuccess(name, address, phoneNumber);
    });

    return dialog;
  }

  /**
   * Clients Dialog Listener. This interface must be implemented by every class
   * that needs to open up this dialog. This will allow it to grab the data
   * that was set in the dialog.
   */
  interface ClientsDialogListener {

    /**
     * Callback for closing the dialog.
     *
     * @param name The client's name.
     * @param address The client's address.
     * @param phoneNumber The client's phone number.
     */
    void onSuccess(String name, String address, String phoneNumber);

  }
}
