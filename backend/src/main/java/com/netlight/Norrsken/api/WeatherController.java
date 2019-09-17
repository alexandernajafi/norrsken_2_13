package com.netlight.Norrsken.api;

import com.netlight.Norrsken.clients.EricssonWeatherClient;
import com.netlight.Norrsken.clients.domain.EricssonWeatherData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class WeatherController {
  private static final String TOKEN =
      "eyJ4NXQiOiJOVEF4Wm1NeE5ETXlaRGczTVRVMVpHTTBNekV6T0RKaFpXSTRORE5sWkRVMU9HRmtOakZpTVEiLCJraWQiOiJOVEF4Wm1NeE5ETXlaRGczTVRVMVpHTTBNekV6T0RKaFpXSTRORE5sWkRVMU9HRmtOakZpTVEiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhbGV4YW5kZXIubmFqYWZpQG5ldGxpZ2h0LmNvbUBjYXJib24uc3VwZXIiLCJhdWQiOiJQcnFmNTk5NHYyd1Rva3VoelNnR0JrVDdYdm9hIiwiYXpwIjoiUHJxZjU5OTR2MndUb2t1aHpTZ0dCa1Q3WHZvYSIsInNjb3BlIjoiYXBpbTpzdWJzY3JpYmUiLCJpc3MiOiJodHRwczpcL1wvZGVtby1pcy53ZXN0ZXVyb3BlLmNsb3VkYXBwLmF6dXJlLmNvbVwvb2F1dGgyXC90b2tlbiIsImV4cCI6MTU2OTMyNTI4MCwiaWF0IjoxNTY4NzIwNDgwLCJqdGkiOiI5NjQ4ZGUxOC03MGZkLTQ1NWQtYWNiZC1kMzk5MGZjMzdmYzYifQ.eoRbF17mcQwIH7gE5llKVidr3rDzQ1oDVq27zgghj5gj4cwz0KNWbbIVQ-p_iLzfXfvqwbbuwdCQGBLuNWvREyUfKOL3rNYWtklPwA358tFvvX_QRRmb1C0_IM2hHG8p67fRXFyNhX7wzBoLbf4ZbYtmwi-e7yjIjQWw4-Yiifq89ag9WXUTBxYmTsv4vOni0lC6Tswaml07UERKGNjxXjVB_0Gx2wQZ4zqDjF--jewL2eVHZrLioTV0JpQWRA092UGDY2SLDZk9KoJJJFIO4nk6QK5ONt9wlbSf8hB6ezK91gWq6R2qPGNUMQVZB7l0HdMz8xka_EKj9uUWJLeAvA";

  EricssonWeatherClient ericssonWeatherClient;

  @RequestMapping(
      value = "/weather",
      method = RequestMethod.GET,
      produces = {"application/json"})
  public Weather getWeatherData(@RequestParam("time") String timeString) {
    OffsetDateTime time = OffsetDateTime.parse(timeString);
    Optional<EricssonWeatherData> stockholm = ericssonWeatherClient.getWeatherData(time, TOKEN);
    return new Weather(
        stockholm
            .map(
                s ->
                    s.getPoints().stream()
                        .map(
                            p ->
                                new Point(
                                    p.getGeometry().getCoordinates()[0],
                                    p.getGeometry().getCoordinates()[1],
                                    p.getProperties().getValue()))
                        .collect(Collectors.toList()))
            .orElse(Collections.emptyList()));
  }
}
