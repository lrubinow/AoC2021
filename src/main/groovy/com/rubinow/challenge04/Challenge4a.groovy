package com.rubinow.challenge04

// correct output is 69579

new ChallengeA().go("/ch4/4.data")

class ChallengeA {
    def go(String data) {
        List<BingoBoard> boards = new ArrayList<>()
        List<String> callouts
        BingoBoard currentBoard
        getClass().getResource(data).eachLine (
                line -> {
                    if( line.contains(",")) {
                        callouts = line.split(",")
                    } else if( line.isBlank()) {
                        currentBoard = new BingoBoard()
                        boards.add(currentBoard)
                    } else {
                        currentBoard.addRow(line)
                    }
                }
        )

        for( String cString : callouts) {
            Integer c = Integer.parseInt(cString)
            println( "Calling out $c")
            for( BingoBoard board : boards ) {
                board.mark(c)
                if( board.isWinner()) {
                    println board.getTotalUnmarked() * c
                    return
                }
            }
        }

    }
}
