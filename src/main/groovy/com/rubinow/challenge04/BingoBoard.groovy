package com.rubinow.challenge04

class BingoBoard {
    final int COLS = 5
    final int ROWS = 5
    boolean isWinner = false

    List<List<Integer>> board = new ArrayList<>()

    public void addRow(String row) {
        def newRow = new ArrayList<Integer>()
        String[] vals = row.split()
        for( String val : vals ) {
            newRow.add(Integer.parseInt(val));
        }
        board.add(newRow)
    }

    public String toString() {
        def retval = new StringBuffer()
        for( int r : 0 .. board.size() - 1) {
                for (int c : 0..COLS - 1) {
                    retval.append( board.get(r).get(c))
                    retval.append(' ')
                }
                retval.append("\n")
        }
        return retval.toString()
    }

    public void mark( Integer val) {
        for( int r : 0 .. board.size() - 1) {
            for( int c : 0 .. COLS -1 ) {
                if( board.get(r).get(c) == val ) {
                    board.get(r).set(c, val + 1000)
                    this.isWinner = checkForWinner()
                    return
                }
            }
        }
    }

    public Boolean isWinner() {
        return this.isWinner
    }
    private Boolean checkForWinner() {
        for( int r : 0 .. board.size() - 1 ) {
            if( board.get(r).stream().mapToInt(Integer::valueOf).sum() >= 1000 * COLS ) {
                return true
            }
        }
        for( int c : 0 .. COLS - 1 ) {
            if( board.stream().mapToInt( row -> row.get(c)).sum() >= 1000 * ROWS ) {
                return true
            }
        }
        return false
    }

    public int getTotalUnmarked() {
        Integer retval = board.stream().mapToInt( row -> row.stream().mapToInt( x -> x >= 1000 ? 0 : x ).sum()).sum()
        return retval
    }
}
