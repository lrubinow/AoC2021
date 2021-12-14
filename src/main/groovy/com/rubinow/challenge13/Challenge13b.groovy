package com.rubinow.challenge13


import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected result is EFJKZLBL

new ChallengeB().go('/ch13/13.data');

class ChallengeB {
    def go(data) {
        Set<Point> points = new HashSet<>()
        List<FoldLine> foldLines = new ArrayList<FoldLine>()
        parseMatrix(data, points, foldLines)
        Point extents = getExtents(points)
        for (int lineIndex in 0..foldLines.size() - 1) {
            println("Extents before fold: " + extents.toString())
            points = fold(extents, points, foldLines.get(lineIndex))
            extents = (foldLines.get(lineIndex).isXValue ?
                    new Point( ((extents.col - 1)/2).intValue(), extents.row) :
                    new Point(extents.col, ((extents.row - 1)/2).intValue()))
        }
        prettyPrintMatrix(points)
        println(getExtents(points))
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
                if (point.row > extents.row) {
                    extents.row = point.row
                }
                if (point.col > extents.col) {
                    extents.col = point.col
                }
            }
        }
        return extents
    }

    def prettyPrintMatrix(Point extents,points) {
        for (int row in 0..extents.row) {
            for (int col in 0..extents.col) {
                Point test = new Point(col, row)
                if (points.contains(test)) {
                    print('#')
                } else {
                    print('.')
                }
            }
            println()
        }
        println()
    }

    def fold(Point extents, points, foldLine) {
        println("Folding ...")
        Set<Point> myPoints = new HashSet<Point>()
        if (foldLine.isXValue) {
            points.each { point ->
                {
                    if (point.col < foldLine.value) {
                        myPoints.add(point)
                    } else if (point.col > foldLine.value) {
                        Point newPoint = new Point(extents.col - point.col, point.row)
                        myPoints.add(newPoint)
                    } else {
                        throw new RuntimeException("Something went wrong folding along column: " + foldLine.toString())
                    }
                }
            }
        } else {
            points.each { point ->
                {
                    if (point.row < foldLine.value) {
                        myPoints.add(point)
                    } else if (point.row > foldLine.value) {
                        Point newPoint = new Point(point.col, extents.row - point.row)
                        myPoints.add(newPoint)
                    } else {
                        throw new RuntimeException("Something went wrong folding along row:" + foldLine.toString())
                    }
                }
            }
        }
        return myPoints
    }
}
