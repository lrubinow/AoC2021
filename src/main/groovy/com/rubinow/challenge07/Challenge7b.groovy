package com.rubinow.challenge07
import static com.rubinow.utils.ChallengeUtils.getDataAsIntList

// correct output is 91257582

new ChallengeB().go('/ch7/7.data');

class ChallengeB {
    def go(String data) {
        def inputs = getDataAsIntList(data, ",")
        def best = Integer.MAX_VALUE
        for( def destination : 0 .. inputs.size() - 1 ) {
            def consumed = fuelConsumedForDestination(inputs, destination)
            if( consumed < best )
                best = consumed
        }
        println( best )
    }

    Integer fuelConsumedForDestination(List<Integer> inputs, Integer destination) {
        Integer totalFuel = 0
        Integer cost = 1
        inputs.forEach( x -> {
            def distance = Math.abs(x - destination)
            totalFuel += distance * (distance +1) / 2
        })
        return totalFuel
    }
}


