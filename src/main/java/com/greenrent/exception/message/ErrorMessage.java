package com.greenrent.exception.message;

public class ErrorMessage {

    public final static String RESOURCE_NOT_FOUND_MESSAGE="Resource with id %d not found";
    public static final String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    public static final String EMAIL_ALREADY_EXIST="Email already exist:%s";
    public final static String ROLE_NOT_FOUND_MESSAGE="Role with id %s not found";
    public final static String NOT_PERMITTED_METHOD_MESSAGE="You dont have any permission to change thi value";
    public final static String PASSWORD_NOT_MATCHED="You password are not matched";
    public static final String IMAGE_NOT_FOUND_MESSAGE = "ImageFile with id %s not found";
    public static final String RESERVATION_TIME_INCORRECT_MESSAGE = "Reservation Time incorrect";
    public static final String CAR_NOT_AVAILABLE_MESSAGE = "Car is not available for selected time";
    public static final String EXCEL_REPORT_CREATION_ERROR_MESSAGE = "Error occurred while generation excel report";
}