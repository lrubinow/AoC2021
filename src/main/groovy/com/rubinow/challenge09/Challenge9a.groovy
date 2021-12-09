package com.rubinow.challenge09

import java.awt.Point

import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected output = 504

new ChallengeA().go('/ch9/9.data');

class ChallengeA {
    def go(data) {
        List<List> map = buildMap(data)
        List<Point> lowPoints = findLowPoints(map)
        List<Integer> risks = findRisks(map, lowPoints)
        println(risks.stream().mapToInt(Integer::valueOf).sum())
    }

    def buildMap(data) {
        List<List> map = []
        iterateLines(data, {String line ->
            map.add(line.toCharArray().toList())
        })
        return map
    }

    def findLowPoints(map) {
        List<Point> retval = []
        map.size().times { y ->
            map[0].size().times { x -> {
                if(isLowPoint(map, x, y)) {
                    retval.add(new Point(x,y))
                }
            }}
        }
        return retval
    }

    def isLowPoint(map, x, y) {
        char thisValue = map[y][x]
        for( def y1 : y-1 ..y+1) {
            for( def x1 : x-1 .. x+1 ) {
                if(isValidIndex(x, y, x1, y1, map)) {
                    if( getValueForPoint(map, x1, y1) <= getValueForPoint(map, x, y)) {
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
        return( map[y][x])
    }

    def List<Integer> findRisks(List<List> map, List<Point> lowPoints) {
        return lowPoints.stream().mapToInt( point -> {
            Integer.parseInt(map[point.y][point.x].toString() ) + 1
        } ).toArray().toList()
    }
}
