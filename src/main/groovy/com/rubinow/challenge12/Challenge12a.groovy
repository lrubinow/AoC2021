package com.rubinow.challenge12

import java.util.stream.Collectors;

import static com.rubinow.utils.ChallengeUtils.iterateLines

// expected result is 4691

new ChallengeA().go('/ch12/12.data');

// a cave map is a Map<String,Set<String>>  -- each key is a room, and each val is a connected room
// a cave path is a List<String> starting at start and ending at end

class ChallengeA {
    def go(data) {
        final Map<String,Set<String>> cave = buildCave(data)
//        println(cave)
        Set<List<String>> paths = findPaths(cave)
        println(paths.size())
    }

    def Map<String, Set<String>> buildCave(data) {
        Map<String,Set<String>> cave = new HashMap<String,Set<String>>()
        iterateLines(data, { String line ->
            addPassageToCave(cave, line.split('-'))
        })
        return cave
    }
    def addPassageToCave(Map<String,Set<String>> cave, String[] endpoints) {
        if(!cave.containsKey(endpoints[0])) {
            cave.put(endpoints[0], new HashSet<String>())
        }
        cave.get(endpoints[0]).add(endpoints[1])
        if(!cave.containsKey(endpoints[1])) {
            cave.put(endpoints[1], new HashSet<String>())
        }
        cave.get(endpoints[1]).add(endpoints[0])
    }

    def Set<List<String>> findPaths(Map<String, Set<String>> cave) {
        Set<List<String>> paths = new HashSet<List<String>>()
        List<String> initialPath = new ArrayList<String>()
        initialPath.add('start')
        followPath(cave, initialPath, paths)
        return paths
    }

    def followPath( Map<String, Set<String>> cave, List<String> pathSoFar, Set<List<String>> paths) {
        if( pathSoFar.last() == 'end') {
            paths.add(pathSoFar)
            return
        }
        Set<String> possibleMoves = getPossibleMoves(cave, pathSoFar)
        if( possibleMoves.isEmpty()) {
            return
        }

        possibleMoves.each {move ->
            List<String> pathCopy = new ArrayList<String>(pathSoFar)
            pathCopy.add(move)
            followPath(cave, pathCopy, paths)
        }
    }

    def Set<String> getPossibleMoves(Map<String, Set<String>> cave, List<String> pathSoFar) {
        String current = pathSoFar.last()
        Set<String> cantRevisit = pathSoFar.stream().filter( location -> {
            location == location.toLowerCase()
        }).collect(Collectors.toSet())
        Set<String> allMoves = new HashSet<String>(cave.get(current))
        allMoves.removeAll(cantRevisit)
        return allMoves
    }
}