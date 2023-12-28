package com.test.doofus.entity;

import com.test.doofus.model.user.request.CreateUsersRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "user_credentials")
@Data
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCredentials extends Auditable<Long> {
    @Column(name = "email", unique = true, nullable = false, length = 128)
    String email;

    @Column(name = "password", nullable = false, length = 512)
    String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_details_id", unique = true)
    UserDetails userDetails;

    public static UserCredentials from(CreateUsersRequest.UserElement userElement) {
        return UserCredentials.builder()
                .email(userElement.getEmail())
                .password(userElement.getPassword())
                .userDetails(UserDetails.from(userElement))
                .build();
    }
}
