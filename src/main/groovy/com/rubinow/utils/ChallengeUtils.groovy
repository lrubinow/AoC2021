package com.rubinow.utils

class ChallengeUtils {
    static List<Integer> getDataAsIntList(def data) {
        Scanner scanner = new Scanner(new File(ChallengeUtils.class.getResource(data).toURI()))
        List<Integer> all = new ArrayList<>()
        while(scanner.hasNextInt()) {
            all.add(scanner.nextInt())
        }
        return all
    }

    static List<Integer> getDataAsIntList(def data, String delimiter) {
        Scanner scanner = new Scanner(new File(ChallengeUtils.class.getResource(data).toURI())).useDelimiter(delimiter)
        List<Integer> all = new ArrayList<>()
        while(scanner.hasNextInt()) {
            all.add(scanner.nextInt())
        }
        return all
    }

    static def iterateDataAsIntList(data, closure) {
        Scanner scanner = new Scanner(new File(ChallengeUtils.class.getResource(data).toURI()))
        scanner.forEachRemaining(closure)
    }

    static def iterateDataAsIntList(data, closure, String delimiter) {
        Scanner scanner = new Scanner(new File(ChallengeUtils.class.getResource(data).toURI())).useDelimiter(delimiter)
        scanner.forEachRemaining(closure)
    }


    static def iterateLines(data, closure) {
        ChallengeUtils.class.getResource(data).eachLine {closure(it) }
    }
}
