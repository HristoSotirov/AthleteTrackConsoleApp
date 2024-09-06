package client;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();

        System.out.println("Welcome to Athlete Track App.\n");
        client.loginRegistartionMenu();
    }

    private void loginRegistartionMenu(){
        System.out.println("""
        Please enter your login credentials.
        If you don't have an account, reach out to your trainer or club administrator for assistance.
        If your club is not yet registered, please complete the registration process.
        
        1: Login
        2: Registration""");

    }
}
