package game_client.service;

import java.util.ArrayList;
import java.util.List;

public class GameDisplayer {
    //Когда отображаем полную карту, вверху первая половина краты (4х8),
    //ниже 2 пустых строки,
    //ниже вторая половина карты(4х8).

    public static final int NUMBER_OF_ROWS = 8;

    private List<StringBuilder> lines;

    //в конструкторе создали ArrayList из 8 пустых строк
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
    //добавляем карту в ArrayList lines
    public void appendMap(HalfMapFullService map) {
        String mapAsString = map.toString();// преобразовали карту в строку
        String[] mapLines = mapAsString.split("\r\n");// создали массив из отдельных строк
        for (int i = 0; i < mapLines.length; i++) {
            lines.get(i).append(mapLines[i] + "    ");// добавили к пустым строкам ArrayList-а строки карты + "    "
        }
    }

    //добавляем карту в ArrayList lines
    public void combineTheWholeMap(HalfMapFullService halfMap1, HalfMapFullService halfMap2) {
        String halfMap1AsString = halfMap1.toString();// преобразовали карту в строку
        String[] halfMap1Lines = halfMap1AsString.split("\r\n");// создали массив из отдельных строк
        for (int i = 0; i < halfMap1Lines.length; i++) {
            lines.get(i).append(halfMap1Lines[i]);
            System.out.println("line " + i + " : " + lines.get(i));
        }

        String halfMap2AsString = halfMap2.toString();// преобразовали карту в строку
        String[] halfMap2Lines = halfMap2AsString.split("\r\n");// создали массив из отдельных строк
        for (int i = halfMap1Lines.length; i < halfMap1Lines.length + halfMap2Lines.length; i++) {
            lines.get(i).append(halfMap2Lines[i]);
        }

        for (int i = 0; i < lines.size(); i++) {
            System.out.println("line " + i + " : " + lines.get(i));
        }

    }


    //выводим на экран карту
    public void displayGame() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
    }
}
