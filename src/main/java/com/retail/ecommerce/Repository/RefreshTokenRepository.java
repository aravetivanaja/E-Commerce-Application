package com.retail.ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecommerce.Model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>{


	boolean existsByTokenAndIsBlocked(String rt, boolean b);

	Optional<RefreshToken> findByToken(String refreshToken);

}
