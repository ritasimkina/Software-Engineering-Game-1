/*
package main;

import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.web.client.RestTemplate;

import Controller.Controller;
import Controller.GameController;
import DataObjects.PlayerInformation;
import MessagesBase.HalfMap;

import MessagesBase.PlayerRegistration;
import MessagesBase.RequestState;
import MessagesBase.ResponseEnvelope;
import MessagesBase.UniqueGameIdentifier;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.FortState;
import MessagesGameState.FullMap;
import MessagesGameState.FullMapNode;
import MessagesGameState.GameState;
import MessagesGameState.PlayerGameState;
import MessagesGameState.PlayerPositionState;
import MessagesGameState.PlayerState;
import MessagesGameState.TreasureState;
import ai.Converter;
import ai.Graph;
import ai.Move;
import ai.Moves;
import ai.Node;
import cli.Visualize;
import map.GenerateHalfMap;
import message.GameIdentifier;
import message.PlayerIdentifier;

public class Main {

    public static void main(String[] args) throws JAXBException, InterruptedException {

        Controller control = new Controller();



        if(args.length == 1){


            control.startGame(args[0]);


            FullMap fm = new FullMap(control.getGameState().getMap().getMapNodes());
            Visualize v = new Visualize(fm);


            while(true){
                v.printMap();
                Move move1;
                Moves move = control.createShortestPath(v);
                move1 = new Move(move.getList().get(0), control.getPlayerID());
                ResponseEnvelope<Move> res;
                Thread.sleep(400);
                control.waitForAction();
                ResponseEnvelope<Move> mmm = control.moveBitch(move1);
                Thread.sleep(400);
                System.out.println("-----------------------");
                System.out.println("NAME = " + mmm.getExceptionName());
                System.out.println("Message = " + mmm.getExceptionMessage());
                System.out.println("-----------------------");
                fm = new FullMap(control.getGameState().getMap().getMapNodes());
                Thread.sleep(400);
                v = new Visualize(fm);
                Thread.sleep(400);

            }



        }}*/
