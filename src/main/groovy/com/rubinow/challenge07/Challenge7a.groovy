package com.rubinow.challenge07
import static com.rubinow.utils.ChallengeUtils.getDataAsIntList

// correct output is 328187

new ChallengeB().go('/ch7/7.data');

class ChallengeA {
    def go(String data) {
        def inputs = getDataAsIntList(data, ",")
        def best = Integer.MAX_VALUE
        for( def destination : 0 .. inputs.size() - 1 ) {
            def consumed = fuelConsumedForDestination(inputs, destination)
            if( consumed < best ) {
                best = consumed
            }
        }
        println( best )
    }

    Integer fuelConsumedForDestination(List<Integer> inputs, Integer destination) {
        Integer totalFuel = 0
        inputs.forEach( x -> totalFuel += Math.abs(x - destination))
        return totalFuel
    }
}


