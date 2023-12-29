package com.test.doofus.repository;

import com.test.doofus.entity.UserCredentials;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagedUserCredentialsRepository extends PagingAndSortingRepository<UserCredentials, Long> {
}
