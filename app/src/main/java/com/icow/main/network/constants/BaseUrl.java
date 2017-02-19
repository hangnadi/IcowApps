package com.icow.main.network.constants;

/**
 * Created by hangnadi on 2/8/17.
 */

public class BaseUrl {

    public static final String BASE_DOMAIN = "https://ws.icow.com/";

    public static class Profile {
        public static final String PATH_GET_PROFILE_INFO = "get_profile_info.pl";
        public static final String PATH_SUBMIT_PROFILE = "submit_profile.pl";
        public static final String URL_BASE_PROFILE = BASE_DOMAIN + "v1/profile/";
        public static final String URL_BASE_ACTION_PROFILE = BASE_DOMAIN + "v1/action/profile/";
    }

    public static class User {
        public static final String URL_BASE_USER = BASE_DOMAIN + "v1/user/";
        public static final String PATH_LOGIN = "login";
        public static final String PATH_REGISTER = "register";
        public static final String PATH_RESET_PASSWORD = "reset";
        public static final String PATH_GET_USER_INFO = "{id}";
    }
}
