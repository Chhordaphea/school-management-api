package com.example.finalprojectapi.common.api;

public enum StatusCode {

    SUCCESS(200, "Success"),
    USER_NOT_FOUND(453, "User is not found"),
    STUDENT_NOT_FOUND(453, "Student is not found"),
    TEACHER_NOT_FOUND(453, "Teacher is not found"),
    BAD_CREDENTIALS(452, "Password is incorrect"),
    SECRET_INVALID(452, "Secret is incorrect"),

    USER_DISABLED(453, "User account is disabled"),

    USER_LOCKED(400, "User account is locked"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    BAD_REQUEST(400, "Bad Request"),
    USERNAME_EXIST(409, "Username already exist"),
    FORBIDDEN(403, "You are not authorized to perform this action"),
    PASSWORD_MUST_BE_ENCRYPTED(400, "Password must be encrypted"),
    SECURITY_KEY_NOT_FOUND(404, "Security key was not found"),
    ROLE_NOT_FOUND(400, "Role not found"),
    IMAGE_CANNOT_BE_EMPTY(400, "Image cannot be empty"),

    SECURITY_CODE_INCORRECT(400, "Security code incorrect");

    private final String message;
    private final int code;

    StatusCode(final int code, final String message) {

        this.message = message;
        this.code = code;
    }

    public String getMessage() {

        return this.message;

    }

    public int getCode() {

        return code;

    }
}
