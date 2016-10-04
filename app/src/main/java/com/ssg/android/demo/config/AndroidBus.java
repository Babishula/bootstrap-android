package com.ssg.android.demo.config;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Internal events bus.
 * Created by mvincent on 23/06/2016.
 */
public class AndroidBus extends Bus {

    /** Handler for main thread. */
    private final Handler mainThread = new Handler( Looper.myLooper());

    @Override
    public void post(final Object event) {
        // Check if current thread is main thread
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            // otherwise send to previously stored main thread reference
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }
}
