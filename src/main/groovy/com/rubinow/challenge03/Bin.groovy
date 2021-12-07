package com.rubinow.challenge03

class Bin {
    private String raw
    private List<Character> charList
    private Integer numeric
    private Integer size

    public Bin(String input) {
        this.raw = input
        this.size = input.length()
        this.charList = input.toCharArray().toList()
        this.numeric = Integer.parseInt(input, 2)
    }

    public Bin(int digits) {
        this.size = digits
        this.charList = new ArrayList<Character>()
    }

    public getBinaryDigitList() { return this.charList }

    public getBinaryDigitAt(int pos) { return this.charList.get(pos) }

    public getNumeric() {
        if( this.numeric == null ) {
            return Integer.parseInt(this.toString(),2)
        }
        return this.numeric
    }

    public getBinaryDigitCount() { return this.charList.size() }

    public addToCharList(char c) {
        if( raw ) {
            throw new IllegalArgumentException("Cannot add to fully formed Bin")
        }
        this.charList.add(c)
    }

    public String toString() {
        return this.charList.join()
    }
}