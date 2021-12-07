package com.rubinow.challenge04

// Correct output is 14877

import java.util.stream.Collectors

new ChallengeB().go("/ch4/4.data")

class ChallengeB {
    def go(String data) {
        List<BingoBoard> boards = new ArrayList<>()
        List<String> callouts
        BingoBoard currentBoard

        getClass().getResource(data).eachLine(
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

        boolean lastLoop = false
        for( String cString : callouts) {

            Integer c = Integer.parseInt(cString)
            println( "Calling out $c")
            for( BingoBoard board : boards ) {
                board.mark(c)
                if( lastLoop && board.isWinner()) {
                    println board.getTotalUnmarked() * c
                    return
                }
            }
            // Remove winning boards
            boards = boards.stream().filter( board -> !board.isWinner()).collect(Collectors.toList())
            if( boards.size() == 1 ) {
                lastLoop = true
            }
        }
    }
}

