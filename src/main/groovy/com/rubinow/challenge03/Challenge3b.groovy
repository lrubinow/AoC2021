package com.rubinow.challenge03

import java.util.stream.Collectors

// Correct output is 4105235

new ChallengeB().go("/ch3/3.data")

class ChallengeB {
    def go(String data) {
        def  list = new ArrayList<char[]>()
        getClass().getResource(data).eachLine {
            list.add(new Bin(it))
        }

        def oxyList = recurse( list, 0, Common.MOST_COMMON )
        def co2List = recurse( list, 0, Common.LEAST_COMMON )

        def oxyListInt = oxyList.get(0).getNumeric()
        def co2ListInt = co2List.get(0).getNumeric()


        println(co2ListInt * oxyListInt )
    }

    def List<Bin> recurse(List<Bin> list, int position, Common common ) {
        def ones = 0
        def zeroes = 0
        list.forEach( it -> {
            if( it.getBinaryDigitAt(position) == '1' )
                ones++
            else
                zeroes++
        })
        char target
        if( common == Common.MOST_COMMON ) {
            if( ones >= zeroes )
                target = '1'
            else
                target = '0'
        } else {
            if( ones < zeroes )
                target = '1'
            else
                target = '0'
        }
        List<Bin> newList = list.stream().filter( item -> item.getBinaryDigitAt(position).equals(target)).collect(Collectors.toList())
        if( newList.size() > 1 && position < list.get(0).getBinaryDigitCount() - 1 )
            return recurse( newList, position + 1, common)
        else
            return newList
    }

}
