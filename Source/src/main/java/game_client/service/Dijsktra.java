/*
package game_client.service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import game_client.messages.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import MessagesBase.Terrain;
import map.Map;
import player.Player;
import utils.CalculateDirection;










*/
/**
 * Dijsktra
 *//*

public class Dijsktra {
    private static final Logger log = LoggerFactory.getLogger(Dijsktra.class);

    private Set<Point> visited;
    private Queue<Node> priorutyQueue;
    private int[][] weights;
    private boolean isPathToTargetFound;
    private ArrayList<Point> pathToTarget;
    private Point target;
    private Map map;
    private Player player;

    public Dijsktra(Player player, Map map, Point target) {
        this.weights = new int[8][8];
        this.visited = new HashSet<Point>();
        this.priorutyQueue = new PriorityQueue<Node>();
        this.player = player;
        this.pathToTarget = new ArrayList<Point>();

        this.isPathToTargetFound = false;

        // add current position of player to priotiryQueue and visited
        this.map = map;
    }

    public void prepareAIForSecondStage() {
        Node position = new Node(player.getPosition(), 0);

        this.priorutyQueue.add(position);
        this.visited.add(position);
        this.fillWeights();
    }

    public Action nextMove() {
        if (!isPathToTargetFound) {
            this.prepareAIForSecondStage();
            this.dijsktra();
            isPathToTargetFound = !isPathToTargetFound;
        }
        Point from = player.getPosition();
        Point to = this.pathToTarget.get;
        Direction direction = CalculateDirection.direction(from, to);

        log.info("Player: " + player.getInfo().getFirstName());
        log.info("moves to cell: (" + to.x + "," + to.y + ")");
        log.info("Cell: " + this.map.getCells()[to.x][to.y].getTerrain());

        this.pathToTarget.remove;
        return new Action(from, to, direction, map.getCellTerrain(from), map.getCellTerrain(to));

    }

    private void fillWeights() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                Terrain value = this.map.getCells()[i][j].getTerrain();

                int weight = 0;
                switch (value) {
                    case Grass:
                        weight = 2;
                        break;
                    case Mountain:
                        weight = 4;
                        break;
                    default:
                        weight = 999;
                }
                weights[j][i] = weight;
            }
        }
        weights[this.player.getPosition().y][this.player.getPosition().x] = 0;
    }

    public boolean isVisited(Point p) {
        return this.visited.contains(p);
    }

    public void dijsktra() {
        ArrayList<Point> allowed;

        while (priorutyQueue.size() != 0) {
            Node current = this.priorutyQueue.poll();
            Node o = current;

            // exit
            if (current.getCurrent().x == target.x && current.getCurrent().y == target.y) {
                while (o.getPrevious() != null) {
                    this.pathToTarget.add(o.getCurrent());
                    o = o.getPrevious();
                }
                Collections.reverse(this.pathToTarget);
                log.debug("Player: " + player.getInfo().getFirstName());
                log.debug("Path to enemy's target: ");
                for (Point p : pathToTarget) {
                    log.debug("--> [" + p.x + "," + p.y + "]");
                }
                return;
            }
            allowed = this.lookAround(current.getCurrent());

            int previousWeight = 0;
            Point previousPoint = current.getPrevious();
            if (previousPoint != null) {
                previousWeight = this.weights[(int) previousPoint.getY()][(int) previousPoint.getX()];
            }

            for (Point p : allowed) {
                int nextWeight = this.weights[(int) p.getY()][(int) p.getX()] + previousWeight;
                Point point = new Point((int) p.getX(), (int) p.getY());
                Node next = new Node(point, current, nextWeight);

                priorutyQueue.add(next);
                this.visited.add(next.getCurrent());
            }
        }
    }

    public ArrayList<Point> getPointsAround(Point src) {
        Point current;
        if (src == null) {
            current = this.player.getPosition();
        } else {
            current = src;
        }

        Point right = new Point((int) current.getX() + 1, (int) current.getY());
        Point down = new Point((int) current.getX(), (int) current.getY() + 1);
        Point left = new Point((int) current.getX() - 1, (int) current.getY());
        Point up = new Point((int) current.getX(), (int) current.getY() - 1);

        ArrayList<Point> around = new ArrayList<Point>();

        around.add(0, down);
        around.add(1, left);
        around.add(2, right);
        around.add(3, up);
        // } else {
        // around.add(1,up);
        // around.add(1,right);
        // around.add(2,left);
        // around.add(3,down);
        // }
        return around;
    }

    private ArrayList<Point> lookAround(Point src) {

        Point current;

        if (src == null) {
            current = this.player.getPosition();
        } else {
            current = src;
        }

        ArrayList<Point> around = this.getPointsAround(current);
        ArrayList<Point> allowedMovements = new ArrayList<>();

        for (Point p : around) {
            if (this.map.boundsCheck(p)) {
                if (!this.map.getCells()[p.x][p.y].getTerrain().equals(Terrain.Water) && !isVisited(p)) {
                    allowedMovements.add(p);
                }
            }
        }
        return allowedMovements;
    }

    public boolean isEmpty() {
        return this.pathToTarget.size() == 0;
    }

}*/
