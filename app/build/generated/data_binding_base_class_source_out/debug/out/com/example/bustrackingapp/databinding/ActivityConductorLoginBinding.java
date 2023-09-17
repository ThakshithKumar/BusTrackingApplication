// Generated by view binder compiler. Do not edit!
package com.example.bustrackingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.bustrackingapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityConductorLoginBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText cPswd;

  @NonNull
  public final EditText cUsrName;

  @NonNull
  public final Button loginBtn;

  private ActivityConductorLoginBinding(@NonNull LinearLayout rootView, @NonNull EditText cPswd,
      @NonNull EditText cUsrName, @NonNull Button loginBtn) {
    this.rootView = rootView;
    this.cPswd = cPswd;
    this.cUsrName = cUsrName;
    this.loginBtn = loginBtn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityConductorLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityConductorLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_conductor_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityConductorLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cPswd;
      EditText cPswd = ViewBindings.findChildViewById(rootView, id);
      if (cPswd == null) {
        break missingId;
      }

      id = R.id.cUsrName;
      EditText cUsrName = ViewBindings.findChildViewById(rootView, id);
      if (cUsrName == null) {
        break missingId;
      }

      id = R.id.loginBtn;
      Button loginBtn = ViewBindings.findChildViewById(rootView, id);
      if (loginBtn == null) {
        break missingId;
      }

      return new ActivityConductorLoginBinding((LinearLayout) rootView, cPswd, cUsrName, loginBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
