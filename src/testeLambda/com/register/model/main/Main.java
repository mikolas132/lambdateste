package testeLambda.com.register.model.main;

import testeLambda.com.register.model.deal.PlayerImpl;
import testeLambda.com.register.model.register.Player;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        PlayerImpl playerImpl = new PlayerImpl();
        String pathDir = "/home/micael.pereira/Desktop";
        String nameArq = "players.txt";
        List<Player> listOfPlayers = playerImpl.getPlayersList(Paths.get(pathDir+"/"+nameArq));

        if(!playerImpl.checkIfFileExit(Paths.get(pathDir))){
            System.out.println("file not exit");
        }else {
            playerImpl.printBestScorer(listOfPlayers);
            playerImpl.printOldestGuy(listOfPlayers);
            playerImpl.printNewestGuy(listOfPlayers);
        }
    }
}
