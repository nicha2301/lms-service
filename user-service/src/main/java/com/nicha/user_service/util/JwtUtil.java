package com.nicha.user_service.util;

import com.nicha.user_service.enums.ErrorCode;
import com.nicha.user_service.exception.AppException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUtil {

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public String generateToken(String username) throws JOSEException {
        JWSSigner signer = new MACSigner(SIGNER_KEY);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("nicha.com")
                .expirationTime(new Date(new Date().getTime() + 60 * 1000 * 60))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public String validateToken(String token) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY);

        if (signedJWT.verify(verifier)) {
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationTime != null && new Date().before(expirationTime)) {
                return signedJWT.getJWTClaimsSet().getSubject();
            } else {
                throw new AppException(ErrorCode.TOKEN_EXPIRED);
            }
        } else {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

}