package com.rubinow.challenge01

// correct output is 1228

new ChallengeA().go("/ch1/1.data")

class ChallengeA {
    def go(data) {
        var prev, count = 0
        getClass().getResource(data).eachLine {
            if( prev != null && Integer.parseInt(it) > Integer.parseInt(prev) )
                count++
            prev = it
        }
        println(count)
    }
}
