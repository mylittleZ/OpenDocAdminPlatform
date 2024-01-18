package com.chenyang.ducumentmanagement;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DucumentManagementApplicationTests {

	@Test
	void testGen() {
		Map<String,Object> claims = new HashMap<>();
		claims.put("id",1);
		claims.put("username","zcy");
		//生成jwt
		String token = JWT.create()
				.withClaim("user",claims) // add payload
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12)) // add expire time
				.sign(Algorithm.HMAC256("zcy")); //指定算法，配置密钥
		System.out.println(token);
	}

	@Test
	void testParse(){
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"+".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6InpjeSJ9LCJleHAiOjE3MDU1ODAzMzh9"+".lIcoeyNTRiufV0_1K6YhkosdxUF4j2dAgd4GP5G-6a0";

		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("zcy")).build();
		DecodedJWT decodedJWT = jwtVerifier.verify(token);
		Map<String, Claim> claims = decodedJWT.getClaims();
		System.out.println(claims.get("user"));
	}

}
