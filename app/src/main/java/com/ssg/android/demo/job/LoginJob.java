package com.ssg.android.demo.job;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;
import com.path.android.jobqueue.RetryConstraint;
import com.ssg.android.demo.config.AppConfig;
import com.ssg.android.demo.dto.LoginInfo;
import com.ssg.android.demo.dto.Token;

import retrofit2.Call;

/**
 * Job to perform login.
 * Created by mvincent on 28/06/2016.
 */
public class LoginJob extends Job {

    /** Login input. */
    private String login;
    /** Password input. */
    private String password;

    public LoginJob(String login, String password) {
        super(new Params(1).requireNetwork().addTags("login"));
        this.login = login;
        this.password = password;
    }

    @Override
    public void onRun() throws Throwable {
        Call<Token> tokenCall = AppConfig.security().login(login, password);
        LoginInfo loginInfo = new LoginInfo();
        // TODO : Add exception management
        loginInfo.token = tokenCall.execute().body().value;
        AppConfig.bus().post(loginInfo);
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        // No retry on failure
        return RetryConstraint.CANCEL;
    }

    @Override
    public void onAdded() {
        // Nothing here
    }

    @Override
    protected void onCancel() {
        // Nothing here
    }

}
