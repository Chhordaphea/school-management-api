package com.example.finalprojectapi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeysProperties (
        String issuerUri,
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
){
}
