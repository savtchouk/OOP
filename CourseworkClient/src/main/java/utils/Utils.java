package utils;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.sql.Timestamp;
import java.util.Scanner;

public class Utils {

    public static String readWithRegex(Scanner in, String regex) {
        String str = in.next();
        if (!str.matches(regex)) {
            throw new IllegalArgumentException("Invalid format");
        }
        return str;
    }
    public static String readId(Scanner in) {
        System.out.println("Id:");
        return readWithRegex(in, "[1-9][0-9]*");
    }
    public static String readTimestamp(Scanner in) {
        System.out.println("Year:");
        String year = Utils.readWithRegex(in, "[1-9][0-9]{3}");
        System.out.println("Month:");
        String month = Utils.readWithRegex(in, "(0?[1-9])|(1[0-2])");
        System.out.println("Day:");
        String day = Utils.readWithRegex(in, "(0?[1-9])|([1-2][0-9])|(3[0-2])");
        System.out.println("Hour:");
        String hour = Utils.readWithRegex(in, "(0?[0-9])|(1[0-9])|2[0-3]");
        System.out.println("Minute:");
        String minute = Utils.readWithRegex(in, "(0?[0-9])|([1-5][0-9])");

        return java.lang.String.format("%04d-%02d-%02dT%02d:%02d:00",
                Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day),
                Integer.parseInt(hour),
                Integer.parseInt(minute)
        );
    }
    public static JSONObject readJournal(Scanner in) throws JSONException {
        System.out.println("Time out:");
        String timeOut = Utils.readTimestamp(in);
        System.out.println("Time in:");
        String timeIn = Utils.readTimestamp(in);
        System.out.println("Auto id:");
        String autoId = Utils.readWithRegex(in,"[1-9][0-9]*");
        System.out.println("Route id:");
        String routeId = Utils.readWithRegex(in,"[1-9][0-9]*");

        JSONObject json = new JSONObject();
        json.put("timeOut", timeOut);
        json.put("timeIn", timeIn);
        json.put("autoId", autoId);
        json.put("routeId", routeId);

        return json;
    }
    public static JSONObject readAuto(Scanner in) throws JSONException {
        System.out.println("Num:");
        String num = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z0-9]+");
        System.out.println("Color:");
        String color = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Mark:");
        String mark = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Personnel id:");
        String personnelId = Utils.readWithRegex(in,"[1-9][0-9]*");

        JSONObject json = new JSONObject();
        json.put("num", num);
        json.put("color", color);
        json.put("mark", mark);
        json.put("personnelId", personnelId);

        return json;
    }
    public static JSONObject readPersonnel(Scanner in) throws JSONException {
        System.out.println("First name:");
        String firstName = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Last name:");
        String lastName = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Pather name:");
        String patherName = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");

        JSONObject json = new JSONObject();
        json.put("firstName", firstName);
        json.put("lastName", lastName);
        json.put("patherName", patherName);

        return json;
    }
    public static JSONObject readRoute(Scanner in) throws JSONException {
        System.out.println("Origin:");
        String origin = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Destination:");
        String dest = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");

        JSONObject json = new JSONObject();
        json.put("name", origin + " - " + dest);

        return json;
    }
}
