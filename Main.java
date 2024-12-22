import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[])throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TmsApplication tmsApp = new TmsApplication();
        UserManager umgr = UserManager.getInstance();
        System.out.println("\n\nTracking Management System");
        while(true){
            System.out.println("\nMenu:");
            System.out.println("1.login"); 
            System.out.println("2.Agent Registration");
            System.out.println("3.Exit");
            System.out.println("\nChoose an option:");

            int option = Integer.parseInt(br.readLine());

            if(option == 1){
                int userId = umgr.login(br);
                if(userId == -1){
                    System.out.println("Couldn't login:Try again...");
                    continue;
                }else{ 
                    tmsApp.runInterface(userId,br);
                }
            }
            else if(option == 2){
                umgr.registerAgent(br);
            }
            else if(option == 3){
                break;
            }else{
                System.out.println("Invalid option...");
            }
        }
    }
}
