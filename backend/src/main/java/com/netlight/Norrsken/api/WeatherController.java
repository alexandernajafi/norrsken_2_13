package com.netlight.Norrsken.api;

import com.netlight.Norrsken.clients.EricssonWeatherClient;
import com.netlight.Norrsken.clients.domain.EricssonWeatherData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/weather")
public class WeatherController {

  EricssonWeatherClient ericssonWeatherClient;
  String token;

  @GetMapping
  public Weather getWeatherData(OffsetDateTime time) {
    EricssonWeatherData stockholm = ericssonWeatherClient.getWeatherData("stockholm", time, token);
    return new Weather(
        stockholm.getPoints().stream()
            .map(
                p ->
                    new Point(
                        p.getGeometry().getCoordinates()[0],
                        p.getGeometry().getCoordinates()[1],
                        p.getProperties().getValue()))
            .collect(Collectors.toList()));
  }
}
