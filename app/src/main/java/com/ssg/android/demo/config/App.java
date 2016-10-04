package com.ssg.android.demo.config;

import org.jraf.android.prefs.Name;
import org.jraf.android.prefs.Prefs;

/**
 * Application shared preferences keys.
 * Created by mvincent on 29/06/2016.
 */
@Prefs
public class App {

    /** Authentication token. */
    @Name("token")
    String token;
}
