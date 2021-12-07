package com.rubinow.challenge02

import static com.rubinow.utils.ChallengeUtils.iterateLines

// correct output is 1962940

new ChallengeA().go("/ch2/2.data")

class ChallengeA {
    def go(data) {
        var position = 0, depth = 0
        iterateLines(data, { line -> {
            String[] parts = line.split()
            switch(parts[0]) {
                case 'forward' :
                    position += Integer.parseInt(parts[1]); break
                case 'up' :
                    depth -= Integer.parseInt(parts[1]); break
                case 'down' :
                    depth += Integer.parseInt(parts[1]); break
            }
        }})
        println position * depth
    }
}
