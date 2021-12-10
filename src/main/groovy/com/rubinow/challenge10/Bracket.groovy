package com.rubinow.challenge10

enum Bracket {
    OPEN_SQUARE('[', ']', 0, 0),
    OPEN_PAREN('(', ')', 0, 0),
    OPEN_CURLY('{', '}', 0, 0),
    OPEN_ANGLE('<', '>', 0, 0),
    CLOSE_SQUARE(']', '[', 57, 2),
    CLOSE_PAREN(')', '(', 3, 1),
    CLOSE_CURLY('}', '{', 1197, 3),
    CLOSE_ANGLE('>', '<', 25137, 4)

    static final Map map
    final String id
    final String matchId
    final Integer points
    final Integer completionPoints

    static {
        map = [:] as TreeMap
        values().each( type ->
            map.put(type.id, type)
        )
    }
    private Bracket(String id, String matchId, Integer points, Integer completionPoints) {
        this.id = id
        this.matchId = matchId
        this.points = points
        this.completionPoints = completionPoints
    }
    static getBracket(id) {
        map[id]
    }
    def isClose() {
        points > 0
    }
    def getMatch() {
        return Bracket.getBracket(this.matchId)
    }
}