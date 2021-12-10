package com.rubinow.challenge10
import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected result is 2182912364

new ChallengeB().go('/ch10/10.data');

class ChallengeB {
    def go(data) {
        List<String> incomplete = new ArrayList<>()
        iterateLines(data, { String line ->
            if( !isLineIllegal(line)) {
                incomplete.add(line)
            }
        })
        def lines = incomplete.stream().mapToLong( line -> getCompletionScore(line)).sorted().collect()
        println( lines.getAt((lines.size() - 1) / 2))
    }

    def isLineIllegal(String line) {
        Stack stack = new Stack()
        def result = line.toList().findResult( chr -> {
            Bracket bracket = Bracket.getBracket(chr)
            if(bracket.isClose()) {
                if( stack.peek() == bracket.getMatch()) {
                    stack.pop()
                } else {
                    return true
                }
            } else {
                stack.push(bracket)
            }
            return null
        })
        result
    }

    def getCompletionScore(String line) {
        Stack<Bracket> stack = new Stack<>()
        // process the stack, leaving remainder
        line.toList().forEach( chr -> {
            Bracket bracket = Bracket.getBracket(chr)
            if(bracket.isClose()) {
                stack.pop()
            } else {
                stack.push(bracket)
            }
        })
        // score the remainder
        long score = 0
        stack.reverse().forEach(bracket -> {
            Bracket close = bracket.getMatch()
            score *= 5
            score += close.getCompletionPoints()
        })
        score
    }
}

