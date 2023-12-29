package com.test.doofus.controller;

import com.test.doofus.constants.GeneralConstants;
import com.test.doofus.constants.ResponseType;
import com.test.doofus.exception.InvalidRequestException;
import com.test.doofus.model.http.RestApiResponse;
import com.test.doofus.model.user.request.CreateUsersRequest;
import com.test.doofus.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Log4j2
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<RestApiResponse<?>> createUsers(@Valid @RequestBody CreateUsersRequest createUsersRequest) {
        userService.createUsers(createUsersRequest);
        return ResponseEntity.ok(RestApiResponse.from(ResponseType.GENERIC_SUCCESS));
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<?>> fetchUsers(
            @RequestParam(required = false) String email) throws InvalidRequestException {
        return ResponseEntity.ok(RestApiResponse.from(ResponseType.GENERIC_SUCCESS, userService.fetchUsers(email)));
    }

    @GetMapping("/paged")
    public ResponseEntity<RestApiResponse<?>> fetchAllUsers(
            @Valid @Min(0) @RequestParam Integer page,
            @Valid @Min(1) @RequestParam Integer size,
            @Valid @Pattern(regexp = GeneralConstants.SORTABLE_FIELDS_DEFAULT) @NotNull @RequestParam String sortBy,
            @Valid @Pattern(regexp = GeneralConstants.SORT_ORDER_REGEX) @NotNull @RequestParam String order
    ) throws InvalidRequestException {
        return ResponseEntity.ok(RestApiResponse.from(
                ResponseType.GENERIC_SUCCESS, userService.fetchAllUsers(PageRequest.of(page, size,
                        order.equalsIgnoreCase(GeneralConstants.ASCENDING)
                                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()))));
    }
}
