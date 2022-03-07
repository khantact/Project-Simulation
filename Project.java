import java.util.*;

public class Project {
    private String name = new String();
    private int sqft = 0;
    private int days = 0;
    private int workers = 0;
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

        return true;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Workers: " + Arrays.toString(Workerlist);
    }

    public static void main(String[] args) {
        Worker khan = new StoneWorker("Kevin Han", 100);
        Worker jax = new StoneWorker("Kevin Han", 100);
        Project ex = new Project("test", 10000, 5, 5);
        ex.assignWorker(khan);
        ex.assignWorker(khan);
        ex.unassignWorker(jax);

        System.out.println(Arrays.toString(ex.Workerlist));
    }
}
