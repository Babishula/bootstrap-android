package com.ssg.android.demo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ssg.android.demo.config.AppConfig;
import com.ssg.android.demo.config.AppPrefs;
import com.ssg.android.demo.dto.LoginInfo;
import com.ssg.android.demo.job.LoginJob;
import com.ssg.android.demo.misc.Utils;
import com.squareup.otto.Subscribe;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    /** Login form. */
    @BindView(R.id.login_form)
    LinearLayout loginForm;
    /** Waiting layout. */
    @BindView(R.id.login_wait)
    LinearLayout wait;
    /** Login input text. */
    @BindView(R.id.login_input)
    EditText loginInput;
    /** Password input text. */
    @BindView(R.id.pwd_input)
    EditText passwordInput;
    /** Login required label. */
    @BindString(R.string.login_required)
    String loginRequired;
    /** Password required label. */
    @BindString(R.string.password_required)
    String passwordRequired;
    /** Connection error label. */
    @BindString(R.string.connection_error) String connectionError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppConfig.bus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppConfig.bus().unregister(this);
    }

    /**
     * Login button click behaviour.
     * @param view Current view
     */
    @OnClick(R.id.login_btn)
    public void login(View view) {

        Snackbar infoBar;

        // Check inputs
        if (Utils.isNullOrEmpty(loginInput)) {
            infoBar = Snackbar.make(view, loginRequired, Snackbar.LENGTH_LONG);
            infoBar.show();
            return;
        }
        if(Patterns.EMAIL_ADDRESS.matcher((CharSequence)loginInput).matches())
        {

        }
        if (Utils.isNullOrEmpty(passwordInput)) {
            infoBar = Snackbar.make(view, passwordRequired, Snackbar.LENGTH_LONG);
            infoBar.show();
            return;
        }

        // Start login call
        String login = loginInput.getText().toString();
        String password = passwordInput.getText().toString();
        AppConfig.jobManager().addJobInBackground(new LoginJob(login, password));

        // Show waiting cursor
        loginForm.setVisibility(View.GONE);
        wait.setVisibility(View.VISIBLE);
    }

    /**
     * Subscription to login info events
     * @param loginInfo Event received
     */
    @Subscribe
    public void loginResult(LoginInfo loginInfo) {

        if (Utils.isNullOrEmpty(loginInfo.token)) {
            // Hide waiting cursor
            loginForm.setVisibility(View.VISIBLE);
            wait.setVisibility(View.GONE);

            // Show snackbar
            Snackbar.make(loginForm, connectionError, Snackbar.LENGTH_INDEFINITE).show();
            return;
        }

        // Login successfull, enter application
        AppPrefs.get(this).putToken(loginInfo.token);
        Intent home = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(home);
    }
}
