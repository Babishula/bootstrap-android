package com.ssg.android.demo.config;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.karumi.dexter.Dexter;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.squareup.otto.Bus;
import com.ssg.android.demo.api.SecurityService;
import com.ssg.android.demo.api.mock.MockSecurity;

/**
 * Centralized application configuration elements.
 * Created by mvincent on 28/06/2016.
 */
public class AppConfig extends MultiDexApplication {

    /** Singleton. */
    private static AppConfig INSTANCE;

    /** Event bus. */
    private AndroidBus bus;

    /** Job manager. */
    private JobManager jobMgr;

    // API services
    /** Security service. */
    private SecurityService security;

    /**
     * Public constructor for Android behaviour.
     * @deprecated DO NOT USE for singleton management consideration
     */
    @Deprecated
    public AppConfig() {
        INSTANCE = this;
    }

    /**
     * @return Configuration singleton
     */
    public static AppConfig get() { return INSTANCE; }

    /**
     * @return Unique events' bus
     */
    public static Bus bus() { return INSTANCE.bus; }

    /**
     * @return Current application context
     */
    public static Context context() { return INSTANCE.getApplicationContext(); }

    /**
     * @return Unique job manager
     */
    public static JobManager jobManager() { return INSTANCE.jobMgr; }

    /**
     * @return Security service proxy
     */
    public static SecurityService security() { return INSTANCE.security; }

    /**
     * On bean creation.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        bus = new AndroidBus();
        configureJobManager();
        initSecurityService();
        MultiDex.install(this);
        Dexter.initialize(this);
    }

    /**
     * Configure job manager
     */
    private void configureJobManager() {
        Configuration cfg = new Configuration.Builder(this)
                .minConsumerCount(1)
                .maxConsumerCount(2)
                .loadFactor(2)
                .consumerKeepAlive(120)
                .build();
        jobMgr = new JobManager(this, cfg);
    }

    private void initSecurityService() {
        // TODO : replace with Rest call
        security = new MockSecurity();
    }

    /**
     * Attach default context
     * @param base Default context
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
