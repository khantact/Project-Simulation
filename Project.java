
public class Project {
    private String name = new String();
    private int sqft = 0;
    private int days = 0;
    private int workers = 0;
    private int woodstash = 0;
    private int stonestash = 0;
    private int woodstashfuture = 0;
    private int stonestashfuture = 0;
    private int sqftbuilt = 0;
    private int remainingsqft = 0;
    private int woodharvest = 0;
    private int stoneharvest = 0;
    private Worker[] Workerlist;

    public Project(String name, int sqft, int days, int workers) {
        if (sqft < 0) {
            this.sqft = 10000;
        }
        if (days < 0) {
            this.days = 10;
        }
        if (workers < 0) {
            this.workers = 5;
        }
        this.name = name;
        this.sqft = sqft;
        this.days = days;
        this.workers = workers;
        Workerlist = new Worker[workers];
        remainingsqft = sqft;
    }

    public boolean assignWorker(Worker w) {
        if (w == null) {
            return false;
        }
        for (int i = 0; i < Workerlist.length; i++) {
            if (w.equals(Workerlist[i])) {
                return false;
            }
        }
        for (int k = 0; k < Workerlist.length; k++) {
            if (Workerlist[k] == null) {
                Workerlist[k] = w;
                break;
            }
        }
        return true;
    }

    public boolean unassignWorker(Worker w) {
        for (int i = 0; i < Workerlist.length; i++) {
            if (w.equals(Workerlist[i])) {
                Workerlist[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean simulateConstruction() {
        System.out.println("Beginning " + this.name + " simulation...");
        for (int i = 1; i < days + 1; i++) {
            System.out.println("*** Day " + i + " ***");
            System.out.println("--- Starting Totals --- Stash: " + woodstash + " logs and " + stonestash + " stone, "
                    + remainingsqft + "sq feet left to build!\n");
            for (int w = 0; w < workers; w++) {
                if (Workerlist[w] == null) { // skips any unassigned workers
                    continue;
                }
                System.out.println(this.Workerlist[w]);

                if (Workerlist[w] instanceof StoneWorker) {
                    System.out.println("...mined " + Workerlist[w].work() + " stones!\n");
                    stonestashfuture += Workerlist[w].work();
                    stoneharvest += Workerlist[w].work();
                } else if (Workerlist[w] instanceof WoodWorker) {
                    System.out.println("...logged " + Workerlist[w].work() + " logs!\n");
                    woodstashfuture += Workerlist[w].work();
                    woodharvest += Workerlist[w].work();
                } else if (Workerlist[w] instanceof ConstructionWorker) {
                    if (i == 1) {
                        System.out.println("...built 0 square feet!\n");
                        continue;
                    }
                    if (woodstash <= stonestash) {
                        if (Workerlist[w].work() > woodstash) {
                            System.out.println("...built " + woodstash + " square feet!\n");

                            sqftbuilt += woodstash;
                            stonestash -= woodstash;
                            stonestashfuture -= stonestash;
                            woodstashfuture -= woodstash;
                            woodstash -= woodstash;
                        } else {
                            System.out.println("...built " + Workerlist[w].work() + " square feet!\n");
                            sqftbuilt += Workerlist[w].work();
                            stonestashfuture -= Workerlist[w].work();

                            woodstashfuture -= Workerlist[w].work();

                            woodstash -= Workerlist[w].work();
                            stonestash -= Workerlist[w].work();
                        }
                    } else if (stonestash < woodstash) {
                        if (Workerlist[w].work() > stonestash) {
                            System.out.println("...built " + stonestash + " square feet!\n");
                            sqftbuilt += stonestash;
                            stonestashfuture -= stonestash;
                            woodstashfuture -= stonestash;
                            woodstash -= stonestash;
                            stonestash -= stonestash;
                        } else {
                            System.out.println("...built " + Workerlist[w].work() + " square feet!\n");
                            sqftbuilt += Workerlist[w].work();
                            stonestashfuture -= Workerlist[w].work();
                            woodstashfuture -= Workerlist[w].work();
                            stonestash -= Workerlist[w].work();
                            woodstash -= Workerlist[w].work();
                        }
                    }
                }
            }

            System.out.println(
                    "--- Day Totals --- " + woodharvest + " logs and " + stoneharvest + " stone gained, "
                            + sqftbuilt + " square feet built!");
            System.out.println("\n");
            remainingsqft -= sqftbuilt;
            sqftbuilt = 0;
            stonestash = stonestashfuture;
            woodstash = woodstashfuture;
            woodharvest = 0;
            stoneharvest = 0;
            if (remainingsqft < 0) {
                System.out.println('"' + name + '"' + " completed successfully after " + i + " days!");
                return true;
            }
        }
        System.out.println('"' + name + '"' + " was not completed within the given deadline.");
        return false;
    }

    public static void main(String[] args) {
        // testing default constructor values
        // Worker jax = new WoodWorker("Jax Everfrost", -1);
        // Worker khan = new StoneWorker("Kevin Han", -1);
        // Worker jason = new ConstructionWorker("Jason Everfrost", -1, -1, -1);
        // ex.assignWorker(jax);
        // ex.assignWorker(khan);
        // ex.assignWorker(jason);

        Worker jax = new WoodWorker("Jax Everfrost", 200);
        Worker khan = new StoneWorker("Kevin Han", 300);
        Worker jason = new ConstructionWorker("Jason Everfrost", 300, 350, 400);
        Worker odin = new WoodWorker("Odin Everfrost", 150);
        Worker jaxon = new ConstructionWorker("Jaxon Everfrost", 150, 175, 500);
        Project ex = new Project("Gladsheim", 600, 6, 5);

        ex.assignWorker(jax);
        ex.assignWorker(khan);
        ex.assignWorker(jason);
        ex.assignWorker(odin);
        ex.assignWorker(jaxon);

        ex.simulateConstruction();
    }
}
