import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        System.out.println("Enter n");
        //int n = enterNumber();
        First first = new First(3);
        first.start();
        Second second = new Second(3);
        second.start();
        new Third(3, first, second).start();

    }

    public static int enterNumber() {
        try {
            return Integer.parseInt(br.readLine());
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            enterNumber();
        }
        return 0;
    }


}
