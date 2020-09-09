package com.creative.automc.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefManager {
    private static final String PREF_NAME = "automc";
    private static final String PREF_SETTINGS_LANGUAGE_CODE = "settings_lang";
    private static final String PREF_USER_LOGGED_IN = "logged_in";
    private static final String PREF_USER_LOGGED_ID = "user_id";

    private static final String PREF_VIN_NUMBER = "vin_number";
    private static final String PREF_BRAND = "brand";
    private static final String PREF_MODEL = "model";
    private static final String PREF_YEAR = "year";
    private static final String PREF_DISTANCE = "distance";
    private static final String PREF_LAST_M_DISTANCE = "last_m_distance";
    private static final String PREF_LAST_M_DATE = "last_m_date";

    private static final String PREF_OIL_LIFE = "oil_life";
    private static final String PREF_TIRES_LIFE = "tires_life";
    private static final String PREF_BREAKS_LIFE = "breaks_life";
    private static final String PREF_AIRCONDITIONER_LIFE = "airconditioner_life";

    private static final String PREF_USER_LOGGED_FullNAME = "user_full_name";
    private static final String PREF_USER_LOCATION = "user_location";
    private static final String PREF_USER_EMAIL = "user_email";
    private static final String PREF_USER_PIC = "user_pic";
    private static final String PREF_USER_LOGGED_PASSWORD = "user_password";
    private static final String PREF_USER_GROUP = "user_group_id";
    private static final String PREF_USER_GROUP_ID = "user_group";
    private static final String PREF_USER_SEMESTER = "user_semester";
    private static final String PREF_USER_EMAIL_ID = "user_emailId";
    private static final String PREF_USER_TOKEN = "user_token";
    private static final String PREF_USER_FIREBASE_TOKEN = "user_firebase_token";
    private static final String PREF_USER_FIREBASE_TOKEN_status = "user_firebase_token_status";
    private static final String PREF_USER_NAME = "user_name";
    private static final String PREF_USER_NAME_AR = "user_name_ar";
    private static final String PREF_USER_FAC = "user_fac";

    private static String PREF_USER_FAV_SERVICES = "services";


    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public static void saveLanguage(Context context, String lang) {
        getPref(context).edit().putString(PREF_SETTINGS_LANGUAGE_CODE, lang).apply();
    }

    public static void saveFirebaseToken(Context context, String token) {
        getPref(context).edit().putString(PREF_USER_FIREBASE_TOKEN, token).apply();
    }

    public static void saveFirebaseTokenUpdateStatus(Context context, String token) {
        getPref(context).edit().putString(PREF_USER_FIREBASE_TOKEN_status, token).apply();
    }

    public static String getFirebaseToken(Context context) {
        return getPref(context).getString(PREF_USER_FIREBASE_TOKEN, "---");
    }

    public static String getFirebaseTokenUpdateStatus(Context context) {
        return getPref(context).getString(PREF_USER_FIREBASE_TOKEN_status, "0");
    }

    public static String readLanguage(Context context) {
        return getPref(context).getString(PREF_SETTINGS_LANGUAGE_CODE, "ar");
    }


    public static boolean isUserLoggedIn(Context context) {
        return getPref(context).getBoolean(PREF_USER_LOGGED_IN, false);
    }

    public static void setUserLoggedIn(Context context, boolean isLoggedIn) {
        getPref(context).edit().putBoolean(PREF_USER_LOGGED_IN, isLoggedIn).apply();
    }

    public static void setUserServices(Context context, String services) {
        getPref(context).edit().putString(PREF_USER_FAV_SERVICES, services).apply();
    }


    public static int getUserId(Context context) {
        return getPref(context).getInt(PREF_USER_LOGGED_ID, 0);
    }

    public static void setUserId(Context context, int userId) {
        getPref(context).edit().putInt(PREF_USER_LOGGED_ID, userId).apply();
    }


    public static String getCarVinNumber(Context context) {
        return getPref(context).getString(PREF_VIN_NUMBER, "");
    }

    public static void setCarVinNumber(Context context, String vinNumber) {
        getPref(context).edit().putString(PREF_VIN_NUMBER, vinNumber).apply();
    }


    public static String getCarBrand(Context context) {
        return getPref(context).getString(PREF_BRAND, "");
    }

    public static void setCarBrand(Context context, String brand) {
        getPref(context).edit().putString(PREF_BRAND, brand).apply();
    }


    public static String getCarModel(Context context) {
        return getPref(context).getString(PREF_MODEL, "");
    }

    public static void setCarModel(Context context, String model) {
        getPref(context).edit().putString(PREF_MODEL, model).apply();
    }


    public static String getCarYear(Context context) {
        return getPref(context).getString(PREF_YEAR, "");
    }

    public static void setCarYear(Context context, String year) {
        getPref(context).edit().putString(PREF_YEAR, year).apply();
    }


    public static String getCarDistance(Context context) {
        return getPref(context).getString(PREF_DISTANCE, "");
    }

    public static void setCarDistance(Context context, String Distance) {
        getPref(context).edit().putString(PREF_DISTANCE, Distance).apply();
    }


    public static String getCarLastMDistance(Context context) {
        return getPref(context).getString(PREF_LAST_M_DISTANCE, "");
    }

    public static void setCarLastMDistance(Context context, String LastMDistance) {
        getPref(context).edit().putString(PREF_LAST_M_DISTANCE, LastMDistance).apply();
    }

    public static String getCarLastMDate(Context context) {
        return getPref(context).getString(PREF_LAST_M_DATE, "");
    }

    public static void setCarLastMDate(Context context, String LastMDate) {
        getPref(context).edit().putString(PREF_LAST_M_DATE, LastMDate).apply();
    }

    public static String getCarOilLife(Context context) {
        return getPref(context).getString(PREF_OIL_LIFE, "");
    }

    public static void setCarOilLife(Context context, String OilLife) {
        getPref(context).edit().putString(PREF_OIL_LIFE, OilLife).apply();
    }


    public static String getCarTiresLife(Context context) {
        return getPref(context).getString(PREF_TIRES_LIFE, "");
    }

    public static void setCarTiresLife(Context context, String TiresLife) {
        getPref(context).edit().putString(PREF_TIRES_LIFE, TiresLife).apply();
    }


    public static String getCarBreaksLife(Context context) {
        return getPref(context).getString(PREF_BREAKS_LIFE, "");
    }

    public static void setCarBreaksLife(Context context, String BreaksLife) {
        getPref(context).edit().putString(PREF_BREAKS_LIFE, BreaksLife).apply();
    }


    public static String getCarAirconditionerLife(Context context) {
        return getPref(context).getString(PREF_AIRCONDITIONER_LIFE, "");
    }

    public static void setCarAirconditionerLife(Context context, String AirconditionerLife) {
        getPref(context).edit().putString(PREF_AIRCONDITIONER_LIFE, AirconditionerLife).apply();
    }


    public static void setUserFullName(Context context, String name) {
        getPref(context).edit().putString(PREF_USER_LOGGED_FullNAME, name).apply();
    }

    public static String getUserFullName(Context context) {
        return getPref(context).getString(PREF_USER_LOGGED_FullNAME, "");
    }


    public static void setUserLocation(Context context, String location) {
        getPref(context).edit().putString(PREF_USER_LOCATION, location).apply();
    }

    public static String getUserLocation(Context context) {
        return getPref(context).getString(PREF_USER_LOCATION, "");
    }


    public static void setUserEmail(Context context, String name) {
        getPref(context).edit().putString(PREF_USER_EMAIL, name).apply();
    }

    public static String getUserEmail(Context context) {
        return getPref(context).getString(PREF_USER_EMAIL, "");
    }

    public static void setUserPic(Context context, String name) {
        getPref(context).edit().putString(PREF_USER_PIC, name).apply();
    }

    public static String getUserPic(Context context) {
        return getPref(context).getString(PREF_USER_PIC, "");
    }

    public static void setLoggedPassword(Context context, String name) {
        getPref(context).edit().putString(PREF_USER_LOGGED_PASSWORD, name).apply();
    }

    public static String getLoggedPassword(Context context) {
        return getPref(context).getString(PREF_USER_LOGGED_PASSWORD, "");
    }

    public static void setGroupId(Context context, int groupId) {
        getPref(context).edit().putInt(PREF_USER_GROUP_ID, groupId).apply();
    }

    public static int getUserGroupId(Context context) {
        return getPref(context).getInt(PREF_USER_GROUP_ID, 0);
    }

    public static String getUserGroup(Context context) {
        return getPref(context).getString(PREF_USER_GROUP, "--");
    }

    public static void setUserGroup(Context context, String userGroup) {
        getPref(context).edit().putString(PREF_USER_GROUP, userGroup).apply();
    }

    public static void setUserName(Context context, String name) {
        getPref(context).edit().putString(PREF_USER_NAME, name).apply();
    }

    public static void setUserNameAR(Context context, String name) {
        getPref(context).edit().putString(PREF_USER_NAME_AR, name).apply();
    }


    public static String getUsernameAR(Context context) {
        return getPref(context).getString(PREF_USER_NAME_AR, "");
    }

    public static String getUsername(Context context) {
        return getPref(context).getString(PREF_USER_NAME, "");
    }


    public static void setUserFac(Context context, String fac) {
        getPref(context).edit().putString(PREF_USER_FAC, fac).apply();
    }


    public static String getUserFac(Context context) {
        return getPref(context).getString(PREF_USER_FAC, "");
    }

    public static String getUserToken(Context context) {
        return getPref(context).getString(PREF_USER_TOKEN, "");
    }

    public static String getUserSemester(Context context) {
        return getPref(context).getString(PREF_USER_SEMESTER, "");
    }

    public static String getUserEmailId(Context context) {
        return getPref(context).getString(PREF_USER_EMAIL_ID, "");
    }

    public static void setUserToken(Context context, String userGroup) {
        getPref(context).edit().putString(PREF_USER_TOKEN, userGroup).apply();
    }

    public static void setUserEmailId(Context context, String emailId) {
        getPref(context).edit().putString(PREF_USER_EMAIL_ID, emailId).apply();
    }

    public static void setUserSemester(Context context, String semester) {
        getPref(context).edit().putString(PREF_USER_SEMESTER, semester).apply();
    }

}
