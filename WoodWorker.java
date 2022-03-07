public class WoodWorker extends Worker {
    private double harvest = 0.0;
    private int average = 0;

    public WoodWorker(String name, int average) {
        super(name);
        if (average < 50) {
            average = 50;
        }
        this.average = average;
    }

    public int work() {
        int currentMood = this.getMood();
        if (currentMood == 0) {
            harvest = .75 * average;
        } else if (currentMood == 1) {
            harvest = average;
        } else {
            harvest = 1.25 * average;
        }
        return (int) harvest;
    }

    public String toString() {
        return "[ Wood Worker ] " + super.toString() + " (avg logs/day: " + average + ")";
    }

    public static void main(String[] args) {
        WoodWorker c1 = new WoodWorker("Kevin", 100);
        System.out.println(c1);
        System.out.println(c1.work());
    }
}
