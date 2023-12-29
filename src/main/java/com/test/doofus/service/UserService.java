package com.test.doofus.service;

import com.test.doofus.constants.ExceptionType;
import com.test.doofus.entity.UserCredentials;
import com.test.doofus.exception.InvalidRequestException;
import com.test.doofus.model.user.request.CreateUsersRequest;
import com.test.doofus.model.user.response.FetchUsersResponse;
import com.test.doofus.repository.PagedUserCredentialsRepository;
import com.test.doofus.repository.UserCredentialsRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE ,makeFinal = true)
public class UserService {

    UserCredentialsRepository userCredentialsRepository;
    PagedUserCredentialsRepository pagedUserCredentialsRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createUsers(CreateUsersRequest createUsersRequest) {

//        List<CreateUsersRequest.UserElement> userElements = createUsersRequest.getUsers();
//        List<UserCredentials> allUserCredentials = new ArrayList<>();
//        for(int i = 0; i < userElements.size(); ++i) {
//            CreateUsersRequest.UserElement user = userElements.get(i);
//            UserCredentials userCredentials = UserCredentials.from(user);
//            allUserCredentials.add(userCredentials);
//        }
//        userCredentialsRepository.saveAll(allUserCredentials);
        List<UserCredentials> userCredentials = userCredentialsRepository.saveAll(
                createUsersRequest.getUsers().stream().map(UserCredentials::from).toList());
        log.info("saved: {}", userCredentials);
    }

    @Transactional(readOnly = true)
    public FetchUsersResponse fetchUsers(String email) throws InvalidRequestException {
        try {
            List<UserCredentials> userCredentials = Objects.isNull(email)
                    ? userCredentialsRepository.findAll()
                    : List.of(userCredentialsRepository.findByEmail(email).orElseThrow());
            log.info("read: {}", userCredentials);
            return FetchUsersResponse.from(userCredentials);
        } catch (NoSuchElementException throwable) {
            throw new InvalidRequestException(ExceptionType.INVALID_EMAIL, throwable);
        }
    }

    @Transactional(readOnly = true)
    public Page<FetchUsersResponse.UserCredentialsElement> fetchAllUsers(Pageable pageable) {
        Page<FetchUsersResponse.UserCredentialsElement> userCredentials = pagedUserCredentialsRepository
                .findAll(pageable).map(FetchUsersResponse.UserCredentialsElement::from);
        log.info("read: {}", userCredentials.get().toList());
        return userCredentials;
    }

}
