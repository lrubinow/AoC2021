package com.rubinow.challenge08
import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected output is 990964

new ChallengeB().go('/ch8/8.data');

class ChallengeB {
    def go(data) {
        def total = 0
        iterateLines( data, line -> total += decodeLineToSum(line) )
        println total
    }

    def decodeLineToSum(String line) {
        StringBuffer valAsString = new StringBuffer()
        decodeLineToIntListOfOutputSection(line).stream().peek(x -> valAsString.append(x.toString())).collect()
        return Integer.parseInt(valAsString.toString())
    }

    def decodeLineToIntListOfOutputSection(String line) {
        def decoder = createDecoderFromLine(line)
        return decodeOutputSection(getOutputSection(line), decoder)
    }

    def getOutputSection(line) {
        return line.substring(line.indexOf('|') + 1)
    }

    def decodeOutputSection(outputSection, decoder) {
        return outputSection.split().toList().stream().map( x -> x.toSet()).mapToInt( x -> decoder.get(x) ).collect()
    }

    String[] filterByCount(String line, Integer count) {
        return line.split().toList().stream().filter(x->x.length() == count).toArray()
    }

    def Object createDecoderFromLine(String line) {
        Set<String> one = filterByCount(line, 2)[0].toSet()
        Set<String> seven = filterByCount(line, 3)[0].toSet()
        Set<String> four = filterByCount(line, 4)[0].toSet()
        Set<String> eight = filterByCount(line, 7)[0].toSet()
        // zero has six elements, contains all elements of one, but doesn't contain all elements of four
        Set<String> zero = filterByCount(line, 6).toList().stream().filter(x -> (x.toSet().containsAll(one) && !(x.toSet().containsAll(four)))).collect().getAt(0).toString().toSet()
        // nine has six elements, doesn't contain all elements of zero, but does contain all elements of one
        Set<String> nine = filterByCount(line, 6).toList().stream().filter( x -> { !(x.toSet().containsAll(zero)) && (x.toSet().containsAll(one))}).collect().getAt(0).toString().toSet()
        // six has six elements, but is not zero or nine
        Set<String> six = filterByCount(line, 6).toList().stream().filter( x -> { !(x.toSet().containsAll(zero)) && !(x.toSet().containsAll(nine))}).collect().getAt(0).toString().toSet()
        // three has five elements, contains all elements of one
        Set<String> three = filterByCount(line, 5).toList().stream().filter(x -> (x.toSet().containsAll(one))).collect().getAt(0).toString().toSet()
        // five has five elements, all of which are contained in six
        Set<String> five = filterByCount(line, 5).toList().stream().filter(x -> six.containsAll(x.toSet())).collect().getAt(0).toString().toSet()
        // two has five elements, but is not three or five
        Set<String> two = filterByCount(line, 5).toList().stream().filter( x -> { !(x.toSet().containsAll(three)) && !(x.toSet().containsAll(five))}).collect().getAt(0).toString().toSet()

        Map<Set<String>, Integer> decoder = new HashMap<Set<String>, Integer>()
        decoder.put(zero, 0)
        decoder.put(one, 1)
        decoder.put(two, 2)
        decoder.put(three, 3)
        decoder.put(four, 4)
        decoder.put(five, 5)
        decoder.put(six, 6)
        decoder.put(seven, 7)
        decoder.put(eight, 8)
        decoder.put(nine, 9)
        return decoder
    }
}