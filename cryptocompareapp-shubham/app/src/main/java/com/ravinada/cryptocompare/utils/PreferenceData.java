package com.ravinada.cryptocompare.utils;

public class PreferenceData {

    public static String getDeviceToken() {
        return (SharedPreferenceUtil.contains(AppConstant.device_token) &&
                (SharedPreferenceUtil.getString(AppConstant.device_token, null) != null) ?
                SharedPreferenceUtil.getString(AppConstant.device_token, null) : "");
    }

    public static void setDeviceToken(String deviceToken){
        SharedPreferenceUtil.putValue(AppConstant.device_token,deviceToken);
    }

    public static void setPassword(String password) {
        SharedPreferenceUtil.putValue(AppConstant.password, password);
    }

    public static String getPassword() {
        return SharedPreferenceUtil.getString(AppConstant.password, "");
    }

    public static void setUserId(String userId) {
        SharedPreferenceUtil.putValue(AppConstant.userid, userId);
    }

    public static String getUserId() {
        return SharedPreferenceUtil.getString(AppConstant.userid, "");
    }

    public static void setLogin(boolean isLogin){
        SharedPreferenceUtil.putValue(AppConstant.ISLOGIN, isLogin);
    }

    public static boolean isLogin(){
        return SharedPreferenceUtil.getBoolean(AppConstant.ISLOGIN, false);
    }

    public static void setOnBoarding(boolean isLogin){
        SharedPreferenceUtil.putValue(AppConstant.IS_ON_BOARDING, isLogin);
    }

    public static boolean isOnBoarding(){
        return SharedPreferenceUtil.getBoolean(AppConstant.IS_ON_BOARDING, false);
    }

    public static boolean getNotificationStatus(){
        return SharedPreferenceUtil.getBoolean(AppConstant.NOTIFICATION_STATUS, false);
    }

    public static void setNotificationStatus(boolean notification){
        SharedPreferenceUtil.putValue(AppConstant.NOTIFICATION_STATUS, notification);
    }

    public static void saveLoginUserData(String userData) {
        SharedPreferenceUtil.putValue(AppConstant.loginRequestResponse, userData);
    }

    public static void clear(){
        SharedPreferenceUtil.clear();
    }

}
