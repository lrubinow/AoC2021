package com.rubinow.challenge01

// correct output is 1257

new ChallengeB().go("/ch1/1.data")

class ChallengeB {
    public go( String data) {
        var prev, count = 0

        Scanner scanner = new Scanner(new File(getClass().getResource(data).toURI()))
        List<Integer> all = new ArrayList<>()
        while(scanner.hasNextInt()) {
            all.add(scanner.nextInt())
        }
        for( var i = 2; i < all.size(); i++ ) {
            var current = all.get(i-2) + all.get(i-1) + all.get(i)
            if( prev && (current > prev)) {
                count++
            }
            prev = current
        }
        println(count)
    }
}
