package ClassRelationShips;
import java.util.ArrayList;
import java.util.List;


/*
* Aggregation is a specialized form of association that represents a "has-a"
  relationship where the parts can exist independently of the whole—but they are grouped together by a container.
  Think of a Team and its Players: a team has players, yet the players can exist even if the team is disbanded.
* */

// Aggregation is the super-set of composition
class Player {
    String name;
    Player(String name) {
        this.name = name;
    }
}

class Team {
    String teamName;
    // Aggregation: A team "has" players.
    List<Player> players = new ArrayList<>();
    Team(String teamName) {
        this.teamName = teamName;
    }
    void addPlayer(Player player) {
        players.add(player);
    }
    void showTeam() {
        System.out.println("Team " + teamName + " has players:");
        for (Player p : players) {
            System.out.println(" - " + p.name);
        }
    }
}

public class Aggregation {
    public static void main(String[] args) {
        Team team = new Team("Warriors");
        team.addPlayer(new Player("Stephen"));
        team.addPlayer(new Player("Klay"));
        team.showTeam();
    }
}
