package com.rubinow.challenge06
// correct output for 80 days is 371379
// correct output for 256 days is 1674303997472

new ChallengeA().go('/ch6/6.data');

class ChallengeA {
    def go(String data) {
        final int MAX_DAYS = 80
        Scanner scanner = new Scanner(new File(getClass().getResource(data).toURI())).useDelimiter(',')

        Long[] lifespanCounts = new Long[] {0,0,0,0,0,0,0,0,0}
        while(scanner.hasNextInt()) {
            Integer initialLifespan = scanner.nextInt()
            lifespanCounts[initialLifespan]++
        }

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


