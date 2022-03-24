public class StoneWorker extends Worker {
    private double harvest = 0.0;
    private int average = 0;

    public StoneWorker(String name, int average) {
        super(name);
        if (average < 40) {
            average = 40;
        }
        this.average = average;
    }

    public int work() {
        int currentMood = this.getMood();
        if (currentMood == 0) {
            harvest = .50 * average;
        } else if (currentMood == 1) {
            harvest = average;
        } else {
            harvest = 1.50 * average;
        }
        return (int) harvest;
    }

    public String toString() {
        return "[ Stone Worker ] " + super.toString() + " (avg stone/day: " + average + ")";
    }

    // public static void main(String[] args) {
    // StoneWorker c1 = new StoneWorker("Kevin Han", 100);
    // System.out.println(c1);
    // System.out.println(c1.work());
    // }
}
