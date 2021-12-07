package com.rubinow.challenge01
import static com.rubinow.utils.ChallengeUtils.getDataAsIntList

// correct output is 1257

new ChallengeB().go("/ch1/1.data")

class ChallengeB {
    public go( String data) {
        var prev, count = 0
        def all = getDataAsIntList(data)
        for( var i = 2; i < all.size(); i++ ) {
            var current = all.get(i-2) + all.get(i-1) + all.get(i)
            if( prev && (current > prev)) {
                count++
            }
            prev = current
        }
        println(count)
    }
}
