package com.rubinow.challenge05

import java.awt.*
import java.util.List

class Vector {
    Point start
    Point end
    Boolean useDiagonals
    public Vector(Integer startx, Integer starty, Integer endx, Integer endy, Boolean useDiagonals) {
        this.start = new Point(startx, starty)
        this.end = new Point(endx, endy)
        this.useDiagonals = useDiagonals
    }
    public Vector(String string, Boolean useDiagonals) {
        def group = (string =~ /(\d+),(\d+)/)
        this.start = new Point(Integer.parseInt(group[0][1]), Integer.parseInt(group[0][2]))
        this.end = new Point(Integer.parseInt(group[1][1]), Integer.parseInt(group[1][2]))
        this.useDiagonals = useDiagonals
    }

    public List<Point> getAllPoints() {
        try {
            List<Point> list = new ArrayList<>()
            if (start.x == end.x) {
                for (Integer y : start.y..end.y) {
                    list.add(new Point(start.x as Integer, y))
                }
            } else if (start.y == end.y) {
                for (Integer x : start.x..end.x) {
                    list.add(new Point(x, start.y as Integer))
                }
            } else if (useDiagonals) {
                Integer y = start.y as Integer
                for (Integer x : start.x..end.x) {
                    list.add(new Point(x,y))
                    y += getIncrement(start.y, end.y)
                }
            }
            return list
        } catch( MissingPropertyException e ) {
            println(e.getMessage())
        }
    }
    private Integer getIncrement( double a, double b) {
        return a > b ? -1 : ( a < b ? 1 : 0 )
    }
}
