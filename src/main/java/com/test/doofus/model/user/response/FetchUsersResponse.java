package com.test.doofus.model.user.response;

import com.test.doofus.entity.UserCredentials;
import com.test.doofus.entity.UserDetails;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FetchUsersResponse {
    List<UserCredentialsElement> users;

    @Data
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class UserCredentialsElement {
        Long id;
        String email;
        UserDetailsElement userDetails;

        @Data
        @Builder
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class UserDetailsElement {
            Long id;
            String firstName;
            String lastName;
            String phoneNumber;
            String address;
            String profilePicture;

            public static UserDetailsElement from(UserDetails userDetails) {
                return UserDetailsElement.builder()
                        .id(userDetails.getId())
                        .firstName(userDetails.getFirstName())
                        .lastName(userDetails.getLastName())
                        .phoneNumber(userDetails.getPhoneNumber())
                        .address(userDetails.getAddress())
                        .profilePicture(userDetails.getProfilePicture())
                        .build();
            }
        }

        public static UserCredentialsElement from(UserCredentials userCredentials) {
            return UserCredentialsElement.builder()
                    .id(userCredentials.getId())
                    .email(userCredentials.getEmail())
                    .userDetails(UserDetailsElement.from(userCredentials.getUserDetails()))
                    .build();
        }
    }

    public static FetchUsersResponse from(List<UserCredentials> userCredentials) {
        return FetchUsersResponse.builder()
                .users(userCredentials.stream().map(UserCredentialsElement::from).toList())
                .build();
    }
}
