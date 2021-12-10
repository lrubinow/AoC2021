package com.rubinow.challenge10

import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected result is 316851

new ChallengeA().go('/ch10/10.data');

class ChallengeA {
    def go(data) {
        def sum = 0
        iterateLines(data, { String line ->
            sum += getScoreForLine(line)
        })
        println sum
    }

    def int getScoreForLine(String line) {
        Stack stack = new Stack()
        def result = line.toList().findResult( chr -> {
            Bracket bracket = Bracket.getBracket(chr)
            if(bracket.isClose()) {
                if( stack.peek() == bracket.getMatch()) {
                    stack.pop()
                } else {
                    return bracket.getPoints()
                }
            } else {
                stack.push(bracket)
            }
            return null
        })
        result == null ? 0 : result
    }
}

