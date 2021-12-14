package com.rubinow.challenge11

import java.awt.Point
import java.util.concurrent.CopyOnWriteArrayList

import static com.rubinow.utils.ChallengeUtils.iterateLines

// correct output is 1725

new ChallengeA().go("/ch11/11.data")

class ChallengeA {
    def go(data) {
        def totalCount = 0
        def matrix = buildMatrix(data)
        100.times {totalCount += doStep(matrix)}
        prettyPrint(matrix)
        println("----------------------------------")
        println(totalCount)
    }

    def doStep(matrix) {
        // First, increase all by one
        increaseAll(matrix)
        // Then, flash all >9, increasing energy of all around; recursively flash those if appropriate
        def count = doFlashes(matrix)
        // Finally, reset all >9 to 0
        resetToZero(matrix)
        return count
    }

    def int doFlashes(List<List<Integer>> matrix) {
        int flashCount = 0
        matrix.eachWithIndex { row, rownum ->
            {
                row.eachWithIndex { int entry, int i ->
                    {
                        if (entry > 9) {
                            flashCount += flash(matrix, rownum, i)
                        }
                    }
                }
            }
        }
        return flashCount
    }

    // flash method:
    // takes row/column to flash. Returns number of flashes generated.

    def int flash(matrix, row, col) {
        if(matrix.get(row).get(col) <= 9) {
            throw new RuntimeException("Flashing a bad point")
        }
        int flashCount = 1
        matrix.get(row).set(col, Integer.MIN_VALUE)
        List<Point> adjacent = getAdjacent(row, col)
        adjacent.each {pt -> {
            matrix.get(pt.row).set(pt.col, matrix.get(pt.row).get(pt.col) + 1)
            if(matrix.get(pt.row).get(pt.col) > 9) {
                flashCount += flash(matrix, pt.row, pt.col)
            }
        }}
        return flashCount
    }

    def increaseAll(matrix) {
        matrix.each(row -> {
            row.eachWithIndex { int entry, int i ->
                {
                    row.set(i, entry + 1)
                }
            }
        })
    }

    def buildMatrix(data) {
        List<List<Integer>> matrix = new ArrayList<List<Integer>>()
        iterateLines(data, { String line ->
            {
                def row = new /*CopyOnWrite*/ ArrayList<Integer>()
                line.toList().each { it -> row.add(Integer.parseInt(it)) }
                matrix.add(row)
            }
        })
        matrix
    }

    def resetToZero(List<List<Integer>> matrix) {
        matrix.each(row -> {
            row.eachWithIndex { int entry, int i ->
                {
                    if (entry < 0) row.set(i, 0)
                }
            }
        })
    }

    def prettyPrint(matrix) {
        matrix.each(row -> {
            row.each { int i ->
                {
                    print(i)
                }
            }
            println()
        })
    }
    def List<Point> getAdjacent(List<List<? super Integer>> Matrix, Point point) {
        return getAdjacent(matrix, point.getRow(), point.getCol())
    }

    def List<Point> getAdjacent(int row, int col) {
        List<Point> adjacent = new ArrayList<Point>()
        for( int r : row - 1 .. row + 1) {
            for( int c : col -1 .. col + 1) {
                if( r >= 0 && r < 10 && c >= 0 && c < 10 && (r != row || c != col)) {
                    adjacent.add(new Point(r,c))
                }
            }
        }
        return adjacent
    }
}

class Point {
    private int row
    private int col

    Point(int row, int col) {
        this.row = row
        this.col = col
    }

    int getRow() {
        return row
    }

    void setRow(int row) {
        this.row = row
    }

    int getCol() {
        return col
    }

    void setCol(int col) {
        this.col = col
    }
}

