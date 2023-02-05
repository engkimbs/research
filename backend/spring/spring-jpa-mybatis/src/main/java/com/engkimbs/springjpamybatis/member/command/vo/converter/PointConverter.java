package com.engkimbs.springjpamybatis.member.command.vo.converter;

import com.engkimbs.springjpamybatis.member.command.vo.Point;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PointConverter implements AttributeConverter<Point, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Point point) {
        return point == null ? null : point.getValue();
    }

    @Override
    public Point convertToEntityAttribute(Integer value) {
        return value == null ? null : new Point(value);
    }
}
