package com.realo.estate.dto.validation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationMessageConst {

  public static final String FIRSTNAME_BOUND = "Firstname must not be more than 64 characters!";
  public static final String FIRSTNAME_NOT_NULL = "Firstname is mandatory!";
  public static final String LASTNAME_BOUND = "Lastname must not be more than 64 characters!";
  public static final String LASTNAME_NOT_NULL = "Lastname is mandatory!";
  public static final String USERNAME_BOUND = "Username must be in bound of 3 and 64 characters!";
  public static final String USERNAME_NOT_NULL = "Username is mandatory!";
  public static final String EMAIL_BOUND = "Email must not be more than 128 characters!";
  public static final String EMAIL_NOT_NULL = "Email is mandatory!";
  public static final String PASSWORD_BOUND = "Password must be in bound of 8 and 128 characters!";
  public static final String PASSWORD_NOT_NULL = "Password is mandatory!";
  public static final String GENDER_NOT_NULL = "Gender is mandatory!";
  public static final String COUNTRY_BOUND = "Country must not be more than 128 characters!";
  public static final String COUNTRY_NOT_NULL = "Country is mandatory!";
  public static final String PHONE_NUMBER_BOUND = "Phone number must be in bound of 10 and 16 characters!";
  public static final String PHONE_NUMBER_NOT_NULL = "Phone number is mandatory!";
  public static final String ROLE_NOT_NULL = "Account type is mandatory!";
  public static final String CITY_BOUND = "City must not be more than 48 characters!";
  public static final String PROVIDER_NAME_NOT_NULL = "Provider name is mandatory!";
  public static final String PROVIDER_NAME_BOUND = "Provider name must be in bound of 3 and 64 characters!";
  public static final String PROVIDER_LINK_NOT_NULL = "Provider link is mandatory!";
  public static final String PROVIDER_LINK_BOUND = "Provider link must not be more than 256 characters!";
  public static final String ESTATE_TYPE_NOT_NULL = "Estate type is mandatory!";
  public static final String ESTATE_SQUARE_NOT_NULL = "Square is mandatory!";
  public static final String ESTATE_YEAR_NOT_NULL = "Year is mandatory!";
  public static final String DESCRIPTION_NOT_NULL = "Description is mandatory!";
  public static final String DESCRIPTION_BOUND = "Description must not be more than 256 characters!";
  public static final String ESTATE_COUNTRY_NOT_NULL = "Country is mandatory!";
  public static final String ESTATE_CITY_NOT_NULL = "City is mandatory!";
  public static final String ESTATE_CITY_BOUND = "City name can not be more than 32 characters!";
  public static final String ESTATE_STREET_NOT_NULL = "Street is mandatory!";
  public static final String ESTATE_STATION_NOT_NULL = "Metro station is mandatory (You can paste 'No')";
  public static final String ESTATE_COUNTRY_BOUND = "Country must not be more than 32 characters!";
  public static final String ESTATE_STREET_BOUND = "Street must not be more than 64 characters!";
  public static final String ESTATE_DISTRICT_BOUND = "District must not be more than 64 characters!";
  public static final String ESTATE_STATION_BOUND = "Metro station must not be more than 128 characters!";
  public static final String ANNOUNCEMENT_TYPE_NOT_NULL = "Type is mandatory!";
  public static final String TITLE_BOUND = "Title must not be more than 64 characters!";
  public static final String TITLE_NOT_NULL = "Title is mandatory!";
  public static final String DETAILS_BOUND = "Details must not be more than 512 characters!";
}