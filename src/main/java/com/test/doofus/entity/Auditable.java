package com.test.doofus.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@MappedSuperclass
public class Auditable<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    T id;
}
