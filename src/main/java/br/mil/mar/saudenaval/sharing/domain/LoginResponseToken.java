package br.mil.mar.saudenaval.sharing.domain;

import java.util.HashMap;
import java.util.Map;

public class LoginResponseToken {

    public static Map<String,String> showToken(String token){
        Map<String,String> json = new HashMap<>();
        json.put("token",token);
        return json;
    }

}
