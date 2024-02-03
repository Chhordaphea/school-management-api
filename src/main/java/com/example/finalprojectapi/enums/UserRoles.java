package com.example.finalprojectapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum UserRoles implements GenericEnum<UserRoles,String> {

    ADMIN_ROLE("ADMIN"),
    USER_ROLE("USER")
    ;
    private final String value;
    UserRoles(String value){this.value=value;}

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        String label = "(no label)";

        if("ADMIN".equals(value)) label = "ADMIN";
        else if("USER".equals(value)) label = "USER";

        return label;
    }

    @JsonCreator
    public static UserRoles fromValue(String value)
    {
        return Stream.of( UserRoles.values()).filter( targetEnum -> targetEnum.value.equals(value)).findFirst().orElse(null);
    }
}
