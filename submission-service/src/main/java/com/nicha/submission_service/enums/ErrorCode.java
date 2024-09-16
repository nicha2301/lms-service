package com.nicha.user_service.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION("Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED("User already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED("User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED("Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("You do not have permission", HttpStatus.FORBIDDEN),
    USER_LOCKED("User is locked", HttpStatus.FORBIDDEN),
    EMAIL_ALREADY_REGISTERED("Email already registered", HttpStatus.CONFLICT),
    SESSION_EXPIRED("Session expired", HttpStatus.UNAUTHORIZED),
    DATABASE_ERROR("Database error", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE("Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE),
    MESSAGE_NOT_EXISTED("Message does not exist", HttpStatus.NOT_FOUND),
    FRIEND_NOT_EXISTED("Friend does not exist", HttpStatus.NOT_FOUND),
    NOT_FRIENDS("Both users are not friends", HttpStatus.BAD_REQUEST),
    GROUP_NOT_FOUND("Group not found", HttpStatus.NOT_FOUND),
    GROUP_OR_MEMBER_NOT_FOUND("Group or member not found", HttpStatus.NOT_FOUND),
    GROUP_CREATOR("You do not have permission, only group creator can delete!", HttpStatus.FORBIDDEN),
    MEMBER_NOT_FOUND("Member does not exist in group", HttpStatus.BAD_REQUEST),
    CANNOT_REMOVE_CREATOR("Group creators cannot be removed from the admin list", HttpStatus.FORBIDDEN),
    TOKEN_EXPIRED("Token has expired", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("Invalid JWT token", HttpStatus.UNAUTHORIZED),
    INVALID_PASSWORD("Invalid username", HttpStatus.UNAUTHORIZED);

    ErrorCode(String message, HttpStatusCode statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    private String message;
    private HttpStatusCode statusCode;
}