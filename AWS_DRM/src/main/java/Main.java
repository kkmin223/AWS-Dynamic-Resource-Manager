import java.util.*;

public class Main {
    static Map<Integer, String> functions = new TreeMap<>();
    static EC2Manager ec2Manager = new EC2Manager();
    static Scanner sc = new Scanner(System.in);
    static ConnectEC2 connectEC2 = new ConnectEC2();
    public static void main(String[] args) {
        int input = 0;
        while(true){
            printScreen();
            if(sc.hasNextInt()){
                input = sc.nextInt();
            }

            switch (input) {
                case 1:
                    listInstance();
                    break;
                case 2:
                    availableRegions();
                    break;
                case 3:
                    startInstance();
                    break;
                case 4:
                    availableZones();
                    break;
                case 5:
                    stopInstance();
                    break;
                case 6:
                    createInstance();
                    break;
                case 7:
                    rebootInstance();
                    break;
                case 8:
                    listImages();
                    break;
                case 9:
                    condorStatus();
                    break;
                case 10:
                    listSecurityGroups();
                    break;
                case 99:
                    System.out.println("Bye");
                    sc.close();
                    return;
                default:
                    System.out.println("concentration!");
            }
        }
    }

    public static void printScreen(){
        System.out.println("                                                            ");
        System.out.println("                                                            ");
        System.out.println("------------------------------------------------------------");
        System.out.println("           Amazon AWS Control Panel using SDK               ");
        System.out.println("------------------------------------------------------------");
        System.out.println("  1. list instance                2. available zones        ");
        System.out.println("  3. start instance               4. available regions      ");
        System.out.println("  5. stop instance                6. create instance        ");
        System.out.println("  7. reboot instance              8. list images            ");
        System.out.println("  9. condor status                10. list Security Group   ");
        System.out.println("                                 99. quit                   ");
        System.out.println("------------------------------------------------------------");
        System.out.print("Enter an integer : ");
    }

    public static void listInstance(){
        System.out.println("Listing instance...");
        ec2Manager.listInstances();
    }

    public static void availableRegions(){
        System.out.println("Available regions...");
        ec2Manager.listRegions();
    }

    public static void availableZones(){
        System.out.println("Available zones...");
        ec2Manager.listZones();

    }

    public static void startInstance(){
        String instanceID = "";
        System.out.print("Enter instance id : ");
        if(sc.hasNext()){
            instanceID = sc.nextLine();
        }
        if(!instanceID.isBlank()){
            ec2Manager.startInstance(instanceID);
        }

    }

    public static void stopInstance(){
        String instanceID = "";
        System.out.print("Enter instance id : ");
        if(sc.hasNext()){
            instanceID = sc.nextLine();
        }
        if(!instanceID.isBlank()){
            ec2Manager.stopInstance(instanceID);
        }
    }

    public static void rebootInstance(){
        String instanceID = "";
        System.out.print("Enter instance id : ");
        if(sc.hasNext()){
            instanceID = sc.nextLine();
        }
        if(!instanceID.isBlank()){
            ec2Manager.rebootInstance(instanceID);
        }
    }

    public static void listImages(){
        System.out.println("Listing Images...");
        ec2Manager.listImages();
    }

    public static void createInstance(){
        String amiId = "";
        System.out.print("Enter ami id : ");
        if(sc.hasNext()){
            amiId = sc.nextLine();
        }
        if(!amiId.isBlank()){
            ec2Manager.createInstance(amiId);
        }
    }

    public static void condorStatus(){
        System.out.println("Condor status ...");
        connectEC2.condorStatus();
    }

    public static void listSecurityGroups(){
        System.out.println("Security groups ...");
        ec2Manager.listSecurityGroups();

    }

}
