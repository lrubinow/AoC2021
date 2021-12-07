package com.rubinow.challenge05

import java.awt.Point

// correct output is 18864

new ChallengeB().go("/ch5/5.data")

class ChallengeB {
    def go(String data) {
        Map<Point,Integer> seen = new HashMap<>()
        getClass().getResource(data).eachLine (
                line -> {
                    Vector v = new Vector(line, true)
                    v.getAllPoints().forEach( point -> {
                        if (!seen.containsKey(point))
                            seen.put(point, 0)
                        seen.put(point, seen.get(point) + 1)
                    })
                }
        )
        Integer counter = 0
        seen.forEach( (point, hits) -> { if( hits > 1 ) counter++})
        println(counter)

    }
}


