public class ConstructionWorker extends Worker {
    private int lowav = 0;
    private int medav = 0;
    private int highav = 0;
    private int built = 0;

    public ConstructionWorker(String name, int low, int med, int high) {
        super(name);
        if (low > 0 || med > 0 || high > 0 && low < med && low < high && med < high) {
            lowav = low;
            medav = med;
            highav = high;
        }

        lowav = 100;
        medav = 200;
        highav = 300;

    }

    public int work() {
        int currentMood = this.getMood();
        if (currentMood == 0) {
            built = lowav;
        } else if (currentMood == 1) {
            built = medav;
        } else {
            built = highav;
        }
        return built;
    }

    public String toString() {
        return "[ Construction Worker ] " + super.toString() + " (avg sq ft/day: " + lowav + "/" + medav + "/"
                + highav + ")";
    }

    public static void main(String[] args) {
        ConstructionWorker c1 = new ConstructionWorker("Kevin Han", 100, 200, 300);
        System.out.println(c1);
        System.out.println(c1.work());
    }

}