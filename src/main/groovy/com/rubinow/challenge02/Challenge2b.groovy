package com.rubinow.challenge02

// correct output is 1813664422

new ChallengeB().go("/ch2/2.data")

class ChallengeB {
    def go(data) {
        var position = 0, depth = 0, aim = 0
        getClass().getResource(data).eachLine {
            String[] parts = it.split()
            switch(parts[0]) {
                case 'forward' :
                    var amount = Integer.parseInt(parts[1])
                    position += amount
                    depth += aim * amount
                    break
                case 'up' :
                    aim -= Integer.parseInt(parts[1]); break
                case 'down' :
                    aim += Integer.parseInt(parts[1]); break
            }
        }
        println position * depth
    }
}
