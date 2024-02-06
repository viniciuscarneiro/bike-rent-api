package com.trio.java.bikerentapi.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.data.geo.Point;

@Converter(autoApply = true)
public class PointConverter implements AttributeConverter<Point, String> {

  @Override
  public String convertToDatabaseColumn(Point point) {
    if (point == null) {
      return null;
    }
    return "POINT(" + point.getX() + " " + point.getY() + ")";
  }

  @Override
  public Point convertToEntityAttribute(String value) {
    if (value == null) {
      return null;
    }
    String[] coordinates = value.replace("POINT(", "").replace(")", "").split(" ");
    return new Point(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
  }
}
