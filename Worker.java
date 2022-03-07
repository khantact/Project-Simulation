import java.util.*;

public abstract class Worker {

    // **** YOU MAY ONLY MODIFY THE equals() METHOD IN THIS FILE ****

    // constants for mood attributes
    private static final String[] MOOD_DESCRIPTIONS = { "tired", "normal", "pumped!" };
    protected static final int MOOD_TIRED = 0;
    protected static final int MOOD_NORMAL = 1;
    protected static final int MOOD_PUMPED = 2;

    // These instance/class variables must remain private
    private String name;
    private int currentMood;
    private int id;
    private static int idCount = 3000;

    // Reuse this random object accross your various classes!
    protected static Random rand = new Random();

    public Worker(String name) {
        this.name = name;
        this.id = idCount++;
        updateMood();
    }

    public String toString() {
        return name + " (#" + id + "), currently feeling " + MOOD_DESCRIPTIONS[currentMood];
    }

    public void updateMood() {
        currentMood = rand.nextInt(MOOD_DESCRIPTIONS.length);
    }

    public int getMood() {
        return currentMood;
    }

    // You may only modify this method
    public boolean equals(Worker other) {
        if (other instanceof Worker) {
            return this.id == other.id;
        }
        return false;
    }

    public abstract int work(); // to be implemented by children classes
}
