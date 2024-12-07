package edu.sdccd.cisc190;
import java.io.*;
import java.util.*;

public class Leaderboard {
    private final String leaderboardFile;
    private List<PlayerScore> scores;

    public Leaderboard(String leaderboardFile) {
        this.leaderboardFile = leaderboardFile;
        this.scores = new ArrayList<>();
        loadScores();
    }

    // Class to store player name, completion time, and fail count
    public static class PlayerScore {
        String name;
        long time; // Completion time in milliseconds
        int fails; // Number of fails

        public PlayerScore(String name, long time, int fails) {
            this.name = name;
            this.time = time;
            this.fails = fails;
        }
    }

    // Add player to leaderboard
    public void addPlayer(String name, long time, int fails) {
        scores.add(new PlayerScore(name, time, fails));
        Collections.sort(scores, Comparator.comparingLong(o -> o.time)); // Sort by time (fastest first)
        saveScores();
    }

    // Load scores from a file
    private void loadScores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(leaderboardFile))) {
            scores = (List<PlayerScore>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Save scores to a file
    private void saveScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(leaderboardFile))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the top 10 players (or all players if fewer than 10)
    public List<PlayerScore> getTopScores() {
        return scores.size() > 10 ? scores.subList(0, 10) : scores;
    }
}
