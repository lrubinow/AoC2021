package com.rubinow.challenge08

// expected output = 554

import static com.rubinow.utils.ChallengeUtils.iterateLines

new ChallengeA().go('/ch8/8.data');

class ChallengeA {
    Set<Integer> uniques = [2,3,4,7]
    def go(data) {
        int total = 0
        iterateLines( data, { String line -> {
            total += line.substring(line.indexOf('|') + 1).split().toList().stream().mapToInt( String::length ).filter(x -> uniques.contains(x)).count()
        }})
        println total
    }
}