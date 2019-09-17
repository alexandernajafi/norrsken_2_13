package com.netlight.Norrsken.domain;

import lombok.Data;

@Data
public class Area {
  final Point upperRight;
  final Point upperLeft;
  final Point downRight;
  final Point downLeft;

  public Area(Point upperRight, Point upperLeft, Point downRight, Point downLeft) {
    this.upperRight = upperRight;
    this.upperLeft = upperLeft;
    this.downRight = downRight;
    this.downLeft = downLeft;
  }

  final boolean surrounds(Point b) {
    double lat = b.getLatitude();
    double lon = b.getLongitude();
    boolean latWithin = lat > downRight.getLatitude() && lat < upperRight.getLatitude();
    boolean lonWithin = lon > downLeft.getLongitude() && lon < downRight.getLongitude();
    return latWithin && lonWithin;
  }
}
