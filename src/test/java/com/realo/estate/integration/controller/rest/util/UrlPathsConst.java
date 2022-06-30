package com.realo.estate.integration.controller.rest.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlPathsConst {

    public static final String PROVIDERS_PATH = "/api/v1/providers";
    public static final String GET_BY_NAME_ENDPOINT = PROVIDERS_PATH + "/name";
    public static final String USERS_PATH = "/api/v1/users";
    public static final String GET_BY_LOGIN_ENDPOINT = USERS_PATH + "/login";
    public static final String ANNOUNCEMENTS_PATH = "/api/v1/announcements";
    public static final String GET_ALL_BY_TYPE_ENDPOINT = ANNOUNCEMENTS_PATH + "/type";
    public static final String GET_ALL_BY_TITLE_ENDPOINT = ANNOUNCEMENTS_PATH + "/title";
    public static final String ESTATES_PATH = "/api/v1/estates";
    public static final String GET_ALL_ESTATES_BY_TYPE_ENDPOINT = ESTATES_PATH + "/type";
}