import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class main {

    private static ArrayList<PersonINF> personList = new ArrayList<>();
    private final static String APIKEY = "AIzaSyBoKw1aAfLSIMp_HQIcUN5c1i-B9Bwci54";

    public static void main(String[] args){

        String car_model = KeyboardInput.getString("What car model do you have? (Year Make Model)");
        String[] carModelArray = car_model.split(" ");

        double fuelEco = 0;
        // Get data from database and get fuel economy information
        try {
            URL url = new URL("http://fueleconomydb.com/specs/" + carModelArray[0] + "/" + carModelArray[1] + "/"+ carModelArray[2]);
            Scanner urlScan = new Scanner(url.openStream());

            while(urlScan.hasNext()){
                //Search for third "%f litres/100km"
                if(urlScan.next().equals("Combined")){
                    String milage = urlScan.next();
                    Pattern pat = Pattern.compile("^\\d+"+ " mpg");
                    Matcher match = pat.matcher(milage);

                    if(match.matches()) {
                        fuelEco = Double.valueOf(milage);
                    } else {
                        continue;
                    }

                }
            }

        } catch (IOException ex){

        }
        String strAddress = KeyboardInput.getString("What is start address");

        double parkingPrice = KeyboardInput.getDouble("What is the price of parking?");

        double busFare = KeyboardInput.getDouble("What is the total bus fare for one day?");

        int carSpace = (int)KeyboardInput.getDouble("How many people fit in your car(excluding you)");

        //Adding people into list
        while(personList.size() < carSpace){
            String name = KeyboardInput.getString("Enter details for person " + personList.size());

            if(name.equals("end")){
                break;
            }

            String address = KeyboardInput.getString("Enter address for person" + personList.size() + 1 + "(In Order of Pickup");
            String[] addressArray = address.split(" ");

            PersonINF person = new PersonINF(name, addressArray);
            personList.add(person);

        }

//        Calculate Distance and time taken with Google Maps Distance Matrix API
        float distance = 0;
        float timeTaken = 0;
        for(int i = 0; i < personList.size() - 1; i++){

            String[] address = personList.get(i).get_address();
            String origins = address[0] + "," + address[1] + " " + address[2] + "," + address[3];
            String[] nextAddress = personList.get(i + 1).get_address();
            String destination = nextAddress[0] + "," + nextAddress[1] + " " + nextAddress[2] + "," + nextAddress[3];

            String url ="https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + origins + "&destinations=" + destination + "&key=" + APIKEY;



        }

        double distanceTravelled = 20;
        double fuelPrice = 1;
        double fuelEcoK = (fuelEco / 3.7854) / 1.6093; // L/km
        double fuelCost = fuelEcoK * fuelPrice * distanceTravelled;

        double totalCost = parkingPrice + fuelCost;
        double cost = totalCost / personList.size();

        if(cost > busFare){
            System.out.println("It is not worth to car pool");
        }





    }
}



