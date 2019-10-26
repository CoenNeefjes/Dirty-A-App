package com.example.dirty_a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.dirty_a.callbacks.JsonObjectCallback;
import com.example.dirty_a.helpers.AuthTokenHelper;
import com.example.dirty_a.settings.ApiSettings;
import com.example.dirty_a.settings.SharedPreferencesSettings;
import com.example.dirty_a.volley.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    // UI elements
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;

    // Shared Preferences
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the shared preferences
        sharedPreferences = getSharedPreferences(SharedPreferencesSettings.PREFERENCES_KEY, MODE_PRIVATE);

        // If we already have the credentials do a login
        if (sharedPreferences.contains(SharedPreferencesSettings.USERNAME_KEY) && sharedPreferences.contains(SharedPreferencesSettings.PASSWORD_KEY)) {
            setTitle("");
            setContentView(R.layout.loading_screen);
            login(sharedPreferences.getString(SharedPreferencesSettings.USERNAME_KEY, null), sharedPreferences.getString(SharedPreferencesSettings.PASSWORD_KEY, null));
            return;
        }

        setContentView(R.layout.activity_login);

        // Set UI elements
        signInButton = findViewById(R.id.login_sign_in_btn);
        usernameEditText = findViewById(R.id.login_username_edit_text);
        passwordEditText = findViewById(R.id.login_password_edit_text);

        signInButton.setOnClickListener(view -> login(usernameEditText.getText().toString(), passwordEditText.getText().toString()));
    }

    private void login(String username, String password) {
        JSONObject params = new JSONObject();
        try {
            params.put("username", username);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestHandler.standardJsonObjectRequest(this, Request.Method.POST, ApiSettings.BASE_URL+"token/", params, new JsonObjectCallback() {
            @Override
            public void processFinished(JSONObject response) {
                try {
                    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                    sharedPreferencesEditor.putString(SharedPreferencesSettings.USERNAME_KEY, username);
                    sharedPreferencesEditor.putString(SharedPreferencesSettings.PASSWORD_KEY, password);
                    AuthTokenHelper.setAuthToken(response.getString("access"));
                    sharedPreferencesEditor.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                    showToast("Something went wrong parsing login response");
                }

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
            @Override
            public void processFailed(VolleyError error) {
                showToast("Wrong credentials provided");
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
