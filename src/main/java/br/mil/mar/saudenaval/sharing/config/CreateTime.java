package br.mil.mar.saudenaval.sharing.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CreateTime {

    public static LocalDateTime now(){
        Instant instant = Instant.now();
        return LocalDateTime.ofInstant(instant, ZoneId.of(("America/Sao_Paulo")));

    }
}
