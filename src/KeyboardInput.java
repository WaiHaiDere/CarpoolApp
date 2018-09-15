import java.util.Scanner;

public class KeyboardInput {

    public static String getString(String prompt){

        Scanner user_input = new Scanner(System.in);
        System.out.print(prompt);
        String string = user_input.next();

        user_input.close();
        return string;


    }

    public static double getDouble(String prompt){

        Scanner user_input = new Scanner(System.in);
        System.out.print(prompt);
        Double number = Double.valueOf(user_input.next());
        user_input.close();

        return number;
    }


}
