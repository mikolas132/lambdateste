package testeLambda.com.register.model.deal;

import testeLambda.com.register.model.register.Player;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerImpl {
    public boolean checkIfFileExit(Path path){

        boolean ret = false;

        try {

            Stream<Path> stream = Files.list(path);

            Optional<Path> arq = stream.filter(p -> p.toString().endsWith("players.txt")).findAny();
            ret = arq.isPresent();

        } catch (IOException ex) {

            ex.printStackTrace();

        }

        return ret;

    }


    public List<Player> getPlayersList(Path path) throws IOException {

        Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1);

        List<String> listOfLines = lines.collect(Collectors.toList());

        List<Player> ListOfPlayers = new ArrayList<>();

        Player player;

        Iterator it = listOfLines.listIterator();

        String str = null;

        while (it.hasNext()) {

            str = (String) it.next();

            String info[]  = str.split(",");

            player = new Player();

            player.setName(info[0]);

            player.setPosition(info[1]);

            player.setAge(Integer.parseInt(info[2]));

            player.setCurrentTeam(info[3]);

            player.setGoalsScored(Integer.parseInt(info[4]));

            ListOfPlayers.add(player);

        }

        return ListOfPlayers;

    }

    public void printPlayers(List<Player> players) {

        players.stream().forEach(System.out::println);

    }

    public void printPlayersTeam(List<Player> players, String team) {

        players.stream().filter(player -> player.getCurrentTeam().equals(team)).forEach(System.out::println);

    }

    public void printPlayersTeamGoals(List<Player> players, String team) {

        players.stream().filter(player -> player.getCurrentTeam().equals(team) && player.getGoalsScored() > 10).

                forEach(System.out::println);

    }

    public void groupPlayersForTeam (List<Player> players) {

        players.stream().sorted(Comparator.comparing(Player::getCurrentTeam)).forEach(System.out::println);

    }

    public double calculateAverageAge (List<Player> players){

        return players.stream().mapToInt(Player::getAge).average().getAsDouble();

    }

    public void printOldestGuy(List<Player> players) {

        Player player = players.stream().max(Comparator.comparingInt(Player::getAge)).get();
        System.out.println("Oldest Guy: " + player.getName());

    }

    public void printNewestGuy(List<Player> players) {

        Player player = players.stream().min(Comparator.comparingInt(Player::getAge)).get();
// .min((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))

        System.out.println("Newest Guy: " + player.getName());

    }

    public void printBestScorer(List<Player> players) {

        Player player = players.stream().max(Comparator.comparingInt(Player::getGoalsScored)).get();

        System.out.println("Best Scorer guy: " + player.getName());

    }

    public int printSumOfGoals (List<Player> players){

        int sum = players.stream().mapToInt(player -> player.getGoalsScored()).sum();

        return sum;

    }

    public void groupPlayerForTeam(List<Player> players){

        Map<String, List<Player>> groupByTime = players.stream().collect(

                Collectors.groupingBy(Player::getCurrentTeam));

        System.out.println(groupByTime);

    }

    public void orderPlayersByGoals(List<Player> players){

        players.stream().sorted(Comparator.comparingInt(Player::getGoalsScored).

                reversed()).forEach(System.out::println);

    }
}
