package com.rubinow.challenge06
import static com.rubinow.utils.ChallengeUtils.iterateDataAsIntList


// correct output for 80 days is 371379
// correct output for 256 days is 1674303997472

new ChallengeB().go('/ch6/6.data');

class ChallengeB {
    def go(String data) {
        final int MAX_DAYS = 256

        Long[] lifespanCounts = new Long[] {0,0,0,0,0,0,0,0,0}
        iterateDataAsIntList(data, {item -> { lifespanCounts[item as Integer]++} }, ",")

        for( Integer day : 0..MAX_DAYS-1) {
            Long spawning = lifespanCounts[0]
            System.arraycopy(lifespanCounts, 1, lifespanCounts, 0, 8)
            lifespanCounts[8] = spawning
            lifespanCounts[6] += spawning
        }

        println( lifespanCounts)
        Long sum = 0
        Arrays.stream(lifespanCounts).forEach(val -> sum += val)
        println( sum)
    }
}


