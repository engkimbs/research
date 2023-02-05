package com.engkimbs.springjpamybatis.member.command.vo;

import lombok.*;

import java.io.Serializable;


@Getter
@EqualsAndHashCode(of = "value")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point implements Serializable, Comparable<Point> {

    private Integer value;

    public Point(Integer point) {
        this.value = point;
    }

    public static Point of(Integer point) {
        return new Point(point);
    }

    public static Point of(Point point) {
        return new Point(point.getValue());
    }

    public Point add(Point point) {
        return new Point(value + point.getValue());
    }

    public Point multiply(Point point) {
        return new Point(this.value*point.getValue());
    }

    public Point multiply(Integer value) {
        return new Point(this.value*value);
    }

    public Point add(Integer value) {
        return new Point(this.value + value);
    }

    @Override
    public int compareTo(Point o) {
        Integer otherValue = o.getValue();
        return this.value.compareTo(otherValue);
    }

    public Point minus(Point point) {
        return new Point(value - point.getValue());
    }
}