package com.netlight.Norrsken;

import com.netlight.Norrsken.api.Alarm;
import com.netlight.Norrsken.clients.EricssonWeatherClient;
import com.netlight.Norrsken.clients.domain.EricssonWeatherData;
import com.netlight.Norrsken.domain.AlarmDefinition;
import com.netlight.Norrsken.domain.Area;
import com.netlight.Norrsken.domain.Point;
import com.netlight.Norrsken.domain.ThreasholdAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlarmService {

  @Autowired EricssonWeatherClient ericssonWeatherClient;

  public List<AlarmDefinition> getDefinitions() {
    return Arrays.asList(threasholdAlarm());
  }

  private AlarmDefinition threasholdAlarm() {
    Point upperRight = new Point(55, 17);
    Point upperLeft = new Point(55, 17);
    Point downRight = new Point(55, 17);
    Point downLeft = new Point(55, 17);
    return new AlarmDefinition(
        "https://aCallbackUrl.com",
        new ThreasholdAlarm(2.1, new Area(upperRight, upperLeft, downRight, downLeft)));
  }

  public List<Alarm> getActiveAlarms(OffsetDateTime offsetDateTime, String token) {
    Optional<EricssonWeatherData> weatherData =
        ericssonWeatherClient.getWeatherData(offsetDateTime, token);

    return getDefinitions().stream()
        .map(d -> d.getTrigger().trigger(weatherData))
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }
}
