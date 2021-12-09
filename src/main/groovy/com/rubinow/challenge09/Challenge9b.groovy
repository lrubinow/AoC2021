package com.rubinow.challenge09

import java.awt.Point
import java.util.stream.Collectors

import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected output = 1558722

new ChallengeB().go('/ch9/9.data');

class ChallengeB {
    def go(data) {
        List<List> map = buildMap(data)
        List<Point> lowPoints = findLowPoints(map)
        List<Set<Point>> threeLargestBasins = findBasins(map, lowPoints).stream().sorted((a, b) -> b.size() <=> a.size()).collect(Collectors.toList()).subList(0, 3)
        def product = 1
        threeLargestBasins.forEach(basin -> product *= basin.size())
        println product
    }

    def buildMap(data) {
        List<List> map = []
        iterateLines(data, { String line ->
            map.add(line.toCharArray().toList())
        })
        return map
    }

    def findLowPoints(map) {
        List<Point> retval = []
        map.size().times { y ->
            map[0].size().times { x ->
                if (isLowPoint(map, x, y)) {
                    retval.add(new Point(x, y))
                }
            }
        }
        return retval
    }

    def isLowPoint(map, x, y) {
        char thisValue = map[y][x]
        for (def y1 : y - 1..y + 1) {
            for (def x1 : x - 1..x + 1) {
                if (isValidIndex(x, y, x1, y1, map)) {
                    if (getValueForPoint(map, x1, y1) <= getValueForPoint(map, x, y)) {
                        return false
                    }
                }
            }
        }
        return true
    }

    def boolean isValidIndex(x, y, x1, y1, map) {
        return (x1 == x || y1 == y) // not diagonal
                && (x1 != x || y1 != y) // not identity
                && (x1 >= 0 && x1 < map[0].size()) // x in range
                && (y1 >= 0 && y1 < map.size()) // y in range
    }

    def getValueForPoint(map, x, y) {
        return (map[y][x])
    }

    def List<Set<Point>> findBasins(List<List> map, List<Point> lowPoints) {
        lowPoints.stream().map(lowPoint -> findBasin(map, lowPoint)).collect(Collectors.toList())
    }

    Set<Point> findBasin(List<List> map, Point lowPoint) {
        Set<Point> startingBasin = new HashSet<Point>()
        startingBasin.add(lowPoint)
        return extendBasin(map, lowPoint, startingBasin)
    }

    def Set<Point> extendBasin(List<List> map, Point startPoint, HashSet<Point> basin) {
        for (int y1 : startPoint.y - 1..startPoint.y + 1) {
            for (int x1 : startPoint.x - 1..startPoint.x + 1) {
                Point newPoint = new Point(x1, y1)
                if (isValidIndex(startPoint.x, startPoint.y, x1, y1, map)
                        && !basin.contains(newPoint)
                        && getIntValueForPoint(map, x1, y1) < 9) {
                    basin.add(newPoint)
                    extendBasin(map, newPoint, basin)
                }
            }
        }
        return basin
    }

    def int getIntValueForPoint(List<List> map, int x, int y) {
        return Integer.parseInt(getValueForPoint(map, x, y).toString())
    }
}
