package com.test.doofus.entity;

import com.test.doofus.model.user.request.CreateUsersRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "user_details")
@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetails extends Auditable<Long> {

    @Column(name = "first_name",  nullable = false, length = 128)
    String firstName;

    @Column(name = "last_name", length = 128)
    String lastName;

    @Column(name = "phone_number",  nullable = false, length = 16)
    String phoneNumber;

    @Column(name = "address",  nullable = false)
    String address;

    @Column(name = "profile_picture")
    String profilePicture;

    public static UserDetails from(CreateUsersRequest.UserElement userElement) {
        return UserDetails.builder()
                .firstName(userElement.getFirstName())
                .lastName(userElement.getLastName())
                .phoneNumber(userElement.getPhoneNumber())
                .address(userElement.getAddress())
                .profilePicture(userElement.getProfilePicture())
                .build();
    }
}
