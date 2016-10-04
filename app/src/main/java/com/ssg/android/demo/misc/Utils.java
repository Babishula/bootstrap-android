package com.ssg.android.demo.misc;

import android.widget.EditText;

/**
 * Some useful methods
 * Created by mvincent on 29/06/2016.
 */
public abstract class Utils {

    /**
     * Check null or empty value
     * @param obj Object to check
     * @return true if objet null or empty, otherwise false
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && ((String) obj).trim().matches("")) {
            return true;
        } else if (obj instanceof EditText && ((EditText) obj).getText().toString().trim().matches("")) {
            return true;
        }
        return false;
    }
}
