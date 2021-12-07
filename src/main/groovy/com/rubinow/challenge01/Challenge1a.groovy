package com.rubinow.challenge01
import static com.rubinow.utils.ChallengeUtils.iterateLines

// correct output is 1228

new ChallengeA().go("/ch1/1.data")

class ChallengeA {
    def go(data) {
        var prev, count = 0
        iterateLines(data, { line -> {
            if( prev != null && Integer.parseInt(line) > Integer.parseInt(prev) )
                count++
            prev = line
        }})
        println(count)
    }
}
