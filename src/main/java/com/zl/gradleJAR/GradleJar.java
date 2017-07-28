package com.zl.gradleJAR;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;

import net.minidev.json.JSONObject;

public class GradleJar {

  private static final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT,
      null, null, null, null, null, null, null, null, null, null, null);


  private static final byte[] secret = "HHIUHDSHIYUEWI*(#@*UDHSDSJKDU(EWJJDKSJU(EWJ".getBytes();

  public static void main(String[] args) {
    System.out.println("Bearer " + createToken(args[0]));
  }

  public static String createToken(String userId) {
    String token = "";
    Map<String, Object> payload = new HashMap<String, Object>();
    payload.put("userId", userId);
    payload.put("exp", new Date().getTime() / 1000 + 28800);
    JWSObject jwsObject = new JWSObject(header, new Payload(new JSONObject(payload)));
    try {
      jwsObject.sign(new MACSigner(secret));
      token = jwsObject.serialize();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return token;
  }
}
