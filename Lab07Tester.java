import java.util.*;

public class Lab07Tester
{
    
    public static void main(String[] args)
    {

            StoneWorker m = new StoneWorker("MattStoneWorker",100);
            WoodWorker l = new WoodWorker("MattLog", 100);
            ConstructionWorker b = new ConstructionWorker("MattBuild", 100, 200, 300);
            System.out.println("***** WORKER TESTS ******");
            System.out.println("\t"+m);
            System.out.println("\t"+l);
            System.out.println("\t"+b);
            for(int i = 0; i < 100; i++)
            {
                m.updateMood();
                int w = m.work();
                if (w > 150 || w < 50){
                    System.err.println("\t ERROR! StoneWorker work total out of range -- average = 100, got: " + w);
                    break;
                }
            }
            for(int i = 0; i < 100; i++)
            {
                l.updateMood();
                int w = l.work();
                if (w > 125 || w < 75){
                    System.err.println("\t ERROR! WoodWorker work total out of range -- average = 100, got: " + w);
                    break;
                }
            }     
            for(int i = 0; i < 100; i++)
            {
                b.updateMood();
                int w = b.work();
                if (w != 100 && w != 200 && w != 300){
                    System.err.println("\t ERROR! ConstructionWorker work total out of range -- low/med/high = 100/200/300, got: " + w);
                    break;
                }
            }     
                               
            System.out.println("***** ASSIGNMENT TESTS ******");            
            ArrayList<Worker> dupeTest = new ArrayList<Worker>();
            Project dupeProj = new Project("Dupe Proj", 100, 5, 100);
            for (int i = 0; i < 3; i++){                
                dupeTest.add(new WoodWorker("Dupe Test!", 100));
                dupeTest.add(new StoneWorker("Dupe Test!", 100));
                dupeTest.add(new ConstructionWorker("Dupe Test!", 100, 200, 300));
            }
            int i = 0;
            for (i = 0; i < dupeTest.size(); i++){
                if (!dupeProj.assignWorker(dupeTest.get(i))){
                    System.err.println("\t ERROR! Failed to assign worker: " + dupeTest.get(i));
                    break;
                }
            }
            for (int j = 0; j < i; j++){
                if (dupeProj.assignWorker(dupeTest.get(j))){
                    System.err.println("\t ERROR! Allowed adding of duplicate worker: " + dupeTest.get(j));
                    break;
                }
            }            
            for (int j = 0; j < i; j++){
                if (!dupeProj.unassignWorker(dupeTest.get(j))){
                    System.err.println("\t ERROR! Didn't allow for unassigning of assigned worker: " + dupeTest.get(j));
                    break;
                }
            }     
            if (dupeProj.unassignWorker(new WoodWorker("Someone random", 200)))
               System.err.println("\t ERROR! Unassign worker returned true for worker not on project");                              
            Project dupeProj2 = new Project("Dupe Proj 2", 100, 5, 2);
            for (i = 0; i < dupeTest.size(); i++){
                if (dupeProj2.assignWorker(dupeTest.get(i)) != i < 2){
                    System.err.println("\t ERROR! Allowed assigning above cap of 2: " + dupeTest.get(i));
                    break;
                }                
            }            
            
}



}