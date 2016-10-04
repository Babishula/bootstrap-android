package com.ssg.android.demo.api;

import com.ssg.android.demo.dto.Token;

import retrofit2.Call;

/**
 * Created by mvincent on 29/06/2016.
 */
public interface SecurityService {

    Call<Token> login(String login, String password);
}
