package com.retail.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecommerce.Model.AccessToken;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer>{



	boolean existsByTokenAndIsBlocked(String at, boolean b);

	Optional<AccessToken>findByToken(String accessToken);

}
