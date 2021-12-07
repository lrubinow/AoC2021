package com.rubinow.challenge03

import static com.rubinow.utils.ChallengeUtils.iterateLines

// correct output is 3847100

new ChallengeA().go("/ch3/3.data")

class ChallengeA {
    def go(String data) {
        def  list = new ArrayList<char[]>()
        iterateLines(data, { line -> {
            list.add(new Bin(line))
        }})

        def gamma = new Bin(list.get(0).getBinaryDigitCount())
        def epsilon = new Bin(list.get(0).getBinaryDigitCount())

        for( x in 0..list.get(0).getBinaryDigitCount() - 1 ) {
            def ones = 0
            def zeroes = 0
            list.forEach( it -> {
                if( it.getBinaryDigitList().get(x) == '1' )
                    ones++
                else
                    zeroes++
            })
            if( ones > zeroes ) {
                gamma.addToCharList('1' as char)
                epsilon.addToCharList('0' as char)
            } else {
                gamma.addToCharList('0' as char)
                epsilon.addToCharList('1' as char)
            }
        }
        println( gamma.getNumeric() * epsilon.getNumeric())
    }
}
