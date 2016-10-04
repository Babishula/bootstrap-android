package com.ssg.android.demo.api.mock;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mvincent on 29/06/2016.
 */
public class MockCall<T> implements Call<T> {

    private T element;

    public MockCall(T element) {
        this.element = element;
    }


    @Override
    public Response<T> execute() throws IOException {
        return Response.success(element);
    }

    @Override
    public void enqueue(Callback<T> callback) {

    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
