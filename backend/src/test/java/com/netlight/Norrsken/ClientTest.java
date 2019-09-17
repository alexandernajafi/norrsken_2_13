package com.netlight.Norrsken;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.netlight.Norrsken.clients.EricssonWeatherClient;
import com.netlight.Norrsken.clients.domain.EricssonWeatherData;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Optional;

public class ClientTest {
  String token =
      "eyJ4NXQiOiJOVEF4Wm1NeE5ETXlaRGczTVRVMVpHTTBNekV6T0RKaFpXSTRORE5sWkRVMU9HRmtOakZpTVEiLCJraWQiOiJOVEF4Wm1NeE5ETXlaRGczTVRVMVpHTTBNekV6T0RKaFpXSTRORE5sWkRVMU9HRmtOakZpTVEiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhbGV4YW5kZXIubmFqYWZpQG5ldGxpZ2h0LmNvbUBjYXJib24uc3VwZXIiLCJhdWQiOiJQcnFmNTk5NHYyd1Rva3VoelNnR0JrVDdYdm9hIiwiYXpwIjoiUHJxZjU5OTR2MndUb2t1aHpTZ0dCa1Q3WHZvYSIsInNjb3BlIjoiYXBpbTpzdWJzY3JpYmUiLCJpc3MiOiJodHRwczpcL1wvZGVtby1pcy53ZXN0ZXVyb3BlLmNsb3VkYXBwLmF6dXJlLmNvbVwvb2F1dGgyXC90b2tlbiIsImV4cCI6MTU2OTMyNTI4MCwiaWF0IjoxNTY4NzIwNDgwLCJqdGkiOiI5NjQ4ZGUxOC03MGZkLTQ1NWQtYWNiZC1kMzk5MGZjMzdmYzYifQ.eoRbF17mcQwIH7gE5llKVidr3rDzQ1oDVq27zgghj5gj4cwz0KNWbbIVQ-p_iLzfXfvqwbbuwdCQGBLuNWvREyUfKOL3rNYWtklPwA358tFvvX_QRRmb1C0_IM2hHG8p67fRXFyNhX7wzBoLbf4ZbYtmwi-e7yjIjQWw4-Yiifq89ag9WXUTBxYmTsv4vOni0lC6Tswaml07UERKGNjxXjVB_0Gx2wQZ4zqDjF--jewL2eVHZrLioTV0JpQWRA092UGDY2SLDZk9KoJJJFIO4nk6QK5ONt9wlbSf8hB6ezK91gWq6R2qPGNUMQVZB7l0HdMz8xka_EKj9uUWJLeAvA";

  @Test
  public void testClient() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    EricssonWeatherClient ericssonWeatherClient =
        new EricssonWeatherClient(
            "https://demo-apim.westeurope.cloudapp.azure.com/api_secure/PrecipitationAPI/3.0.0",
            "/weather/precipitation/at",
            objectMapper,
            CacheBuilder.newBuilder().build());

    Optional<EricssonWeatherData> stockholm =
        ericssonWeatherClient.getWeatherData(
            OffsetDateTime.parse("2019-09-16T20:00:00+02:00"), token);
    System.out.println(stockholm.get());
  }
}
