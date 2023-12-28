package com.test.doofus.model.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUsersRequest implements Serializable {
    List<UserElement> users;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UserElement implements Serializable {
        @Email
        String email;

        @NotNull
        String password;

        @NotNull
        String firstName;
        String lastName;

        @NotNull
        String phoneNumber;

        @NotNull
        String address;
        String profilePicture;
    }
}
