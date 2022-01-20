package com.meeshoapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meeshoapi.view.UserLoginDetailsView;

@Repository
public interface IUserLoginDetailsRepository extends CrudRepository<UserLoginDetailsView, Long> {
	List<UserLoginDetailsView> findByUsername(String username);

}
