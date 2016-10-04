package com.ssg.android.demo.api.mock;

import com.ssg.android.demo.api.SecurityService;
import com.ssg.android.demo.dto.Token;

import retrofit2.Call;

/**
 * Created by mvincent on 29/06/2016.
 */
public class MockSecurity implements SecurityService {
    @Override
    public Call<Token> login(String login, String password) {
        Token token = new Token();
        token.value = "token";
        return new MockCall<Token>(token);
    }
}
