package com.netlight.Norrsken;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.netlight.Norrsken.clients.EricssonWeatherClient;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
  private static final String url =
      "https://demo-apim.westeurope.cloudapp.azure.com/api_secure/PrecipitationAPI/3.0.0";
  private static final String path = "/weather/precipitation/at";

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }

  @Bean
  public EricssonWeatherClient ericssonWeatherClient(ObjectMapper objectMapper) {
    return new EricssonWeatherClient(url, path, objectMapper);
  }
}
