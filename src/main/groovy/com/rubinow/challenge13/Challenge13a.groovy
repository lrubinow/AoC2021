package com.rubinow.challenge13


import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected result is 664

new ChallengeA().go('/ch13/13.data');

class ChallengeA {
    def go(data) {
        Set<Point> points = new HashSet<>()
        List<FoldLine> foldLines = new ArrayList<FoldLine>()
        parseMatrix(data, points, foldLines)
        Point extents = getExtents(points)
        points = fold(extents, points, foldLines.get(0))
        println(points.size())
    }

    def parseMatrix(data, points, foldLines) {
        iterateLines(data, { String line ->
            if (line.contains(',')) {
                String[] components = line.split(',')
                Point newPoint = new Point(components[0], components[1])
                points.add(newPoint)
            } else if (line.contains('=')) {
                foldLines.add(new FoldLine(line))
            }
        })
    }

    def Point getExtents(Set<Point> points) {
        Point extents = new Point(0, 0)
        points.each { point ->
            {
                if(point.row > extents.row) {
                    extents.row = point.row
                }
                if(point.col > extents.col) {
                    extents.col = point.col
                }
            }
        }
        return extents
    }

    def fold(Point extents, points, foldLine) {
        Set<Point> myPoints = new HashSet<Point>()
        if (foldLine.isXValue) {
            points.each { point ->
                {
                    if (point.col < foldLine.value) {
                        myPoints.add(point)
                    } else {
                        Point newPoint = new Point(extents.col - point.col, point.row)
                        myPoints.add(newPoint)
                    }
                }
            }
        } else {
            points.each { point ->
                {
                    if (point.row < foldLine.value) {
                        myPoints.add(point)
                    } else {
                        Point newPoint = new Point(point.col, extents.row - point.row)
                        myPoints.add(newPoint)
                    }
                }
            }
        }
        return myPoints
    }

}

class Point {
    private int row
    private int col

    Point(int col, int row) {
        this.col = col
        this.row = row
    }

    Point(String col, String row) {
        this.col = Integer.parseInt(col)
        this.row = Integer.parseInt(row)
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

    @Override
    String toString() {
        sprintf("[%d,%d]", col, row)
    }

    @Override
    boolean equals(Object obj) {
        Point other = (Point) obj
        return (this.row == other.row && this.col == other.col)
    }

    @Override
    int hashCode() {
        return (col.toString() + row.toString()).hashCode()
    }
}

class FoldLine {
    int value;
    Boolean isXValue = false

    public FoldLine(String line) {
        line = line.substring(10)
        String[] components = line.split("=")
        value = Integer.parseInt(components[1])
        isXValue = (components[0].contains('x'))
    }

    @Override
    String toString() {
        sprintf("%s=%d", (isXValue ? 'x' : 'y'), value)
    }
}