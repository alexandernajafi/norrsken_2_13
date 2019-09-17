package com.netlight.Norrsken.api;

import com.netlight.Norrsken.AlarmService;
import com.netlight.Norrsken.Configuration;
import com.netlight.Norrsken.domain.AlarmDefinition;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/alarm")
@AllArgsConstructor
public class AlarmController {
  final AlarmService alarmService;

  @RequestMapping(
      value = "/defs",
      method = RequestMethod.GET,
      produces = {"application/json"})
  public List<AlarmDefinition> getDefinitions() {
    return alarmService.getDefinitions();
  }

  @RequestMapping(
      value = "/active",
      method = RequestMethod.GET,
      produces = {"application/json"})
  public List<Alarm> getActiveAlarms(@RequestParam("time") String time) {
    OffsetDateTime offsetDateTime = OffsetDateTime.parse(time);
    return alarmService.getActiveAlarms(offsetDateTime, Configuration.TOKEN);
  }
}
