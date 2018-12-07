package game_client.service;

import java.util.ArrayList;
import java.util.List;

public class GameDisplayer {
    //dispayer shows 4*8 nodes
    //then there are two empty raws
    //then the other part of a map

    public static final int NUMBER_OF_ROWS = 8;

    private List<StringBuilder> lines;

    //created empty arraylist in a costructor
    public GameDisplayer() {
        lines = new ArrayList<StringBuilder>();
        for (int i = 0; i < 9; i++) {
            lines.add(new StringBuilder());
        }

        System.out.println("GameDisplayer() constructor:lines=" + lines);
    }

    /**
     * @param map getting a map and representing it as String
     */
    //addin g a map into в ArrayList lines
    public void appendMap(HalfMapFullService map) {
        String mapAsString = map.toString();// map into toString
        String[] mapLines = mapAsString.split("\r\n");// array of independent raws
        for (int i = 0; i < mapLines.length; i++) {
            lines.get(i).append(mapLines[i] + "    ");//added to empty raws of ArrayList lines of a map + "    "
        }
    }

    //добавляем карту в ArrayList lines
    public void combineTheWholeMap(HalfMapFullService halfMap1, HalfMapFullService halfMap2) {
        String halfMap1AsString = halfMap1.toString();// card into a sttring
        String[] halfMap1Lines = halfMap1AsString.split("\r\n");//array of individual lines
        for (int i = 0; i < halfMap1Lines.length; i++) {
            lines.get(i).append(halfMap1Lines[i]);
            System.out.println("line " + i + " : " + lines.get(i));
        }

        String halfMap2AsString = halfMap2.toString();// to string
        String[] halfMap2Lines = halfMap2AsString.split("\r\n");// lines
        for (int i = halfMap1Lines.length; i < halfMap1Lines.length + halfMap2Lines.length; i++) {
            lines.get(i).append(halfMap2Lines[i]);
        }

        for (int i = 0; i < lines.size(); i++) {
            System.out.println("line " + i + " : " + lines.get(i));
        }

    }


    //printl the whole thing
    public void displayGame() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
    }
}
