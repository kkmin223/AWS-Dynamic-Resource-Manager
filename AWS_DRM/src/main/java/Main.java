import java.util.*;

public class Main {
    static Map<Integer, String> functions = new TreeMap<>();
    static EC2Manager EC2Manager = new EC2Manager();
    public static void main(String[] args) {
        init();
        int input = 0;
        Scanner sc = new Scanner(System.in);
        while(input != 99){
            printScreen();
            input = sc.nextInt();
            if (input == 1) {
                listInstances();
            }
            else if (input == 4){
                listRegions();
            }
        }

    }

    public static void init(){
        functions.put(1, "list instance");
        functions.put(2, "available zones");
        functions.put(3, "start instance");
        functions.put(4, "available regions");
        functions.put(5, "stop instance");
        functions.put(6, "create instance");
        functions.put(7, "reboot instance");
        functions.put(8, "list images");
        functions.put(99, "quit");
    }

    public static void printScreen(){
        Iterator<Map.Entry<Integer, String>> entryIterator = functions.entrySet().iterator();
        System.out.println("----------------------------------------");
        while (entryIterator.hasNext()){
            Map.Entry<Integer, String> function = entryIterator.next();
            System.out.println(function.getKey() + ". " + function.getValue());
        }
        System.out.println("----------------------------------------");
        System.out.print("input : ");
    }

    public static void listInstances(){
        EC2Manager.listInstances();
    }

    public static void listRegions(){
        EC2Manager.listRegions();
    }
}
