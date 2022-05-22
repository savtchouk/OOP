import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utils.Utils;

import java.util.*;

public class CourseworkClient {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders header = new HttpHeaders();
    private static final Scanner in = new Scanner(System.in);

    // get by id
    private static void getById(String url) {
        String id = Utils.readId(in);
        ResponseEntity<Object> response = restTemplate.getForEntity(url + "/" + id, Object.class);
        System.out.println(response.getBody());
    }
    private static void getJournalById() {
        try {
            getById("http://localhost:8080/journals/find");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Journal not found");
            } else {
                throw e;
            }
        }
    }
    private static void getAutoById() {
        try {
            getById("http://localhost:8080/autos/find");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Auto not found");
            } else {
                throw e;
            }
        }
    }
    private static void getPersonnelById() {
        try {
            getById("http://localhost:8080/personnels/find");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Personnel not found");
            } else {
                throw e;
            }
        }
    }
    private static void getRouteById() {
        try {
            getById("http://localhost:8080/routes/find");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Route not found");
            } else {
                throw e;
            }
        }
    }
    // get all
    private static void getAll(String url) {
        ResponseEntity<List<Object>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){}
        );
        List<Object> list = Objects.requireNonNull(response.getBody());
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    private static void getAllJournals() {
        getAll("http://localhost:8080/journals/list");
    }
    private static void getAllAuto() {
        getAll("http://localhost:8080/autos/list");
    }
    private static void getAllPersonnel() {
        getAll("http://localhost:8080/personnels/list");
    }
    private static void getAllRoutes() {
        getAll("http://localhost:8080/routes/list");
    }
    // delete
    private static void deleteById(String url) {
        String id = Utils.readId(in);

        HttpEntity<String> request = new HttpEntity<>(null, header);
        restTemplate.exchange(
                url + "/" + id, HttpMethod.DELETE, request, Object.class
        );
        System.out.println("Successful deletion");
    }
    private static void deleteJournalById() {
        try {
            deleteById("http://localhost:8080/journals/delete");
        } catch (Exception e) {
            System.out.println("Access denied or journal not found");
        }
    }
    private static void deleteAutoById() {
        try {
            deleteById("http://localhost:8080/autos/delete");
        } catch (Exception e) {
            System.out.println("Access denied or auto not found");
        }
    }
    private static void deletePersonnelById() {
        try {
            deleteById("http://localhost:8080/personnels/delete");
        } catch (Exception e) {
            System.out.println("Access denied or personnel not found");
        }
    }
    private static void deleteRouteById() {
        try {
            deleteById("http://localhost:8080/routes/delete");
        } catch (Exception e) {
            System.out.println("Access denied or route not found");
        }
    }
    // add
    private static void add(String url, JSONObject json) {
        HttpEntity<String> request = new HttpEntity<>(json.toString(), header);
        ResponseEntity<Object> response = restTemplate.exchange(
                url, HttpMethod.POST, request, Object.class
        );
        System.out.println(response.getBody());
    }
    private static void addJournal() throws JSONException {
        JSONObject json = Utils.readJournal(in);
        try {
            add("http://localhost:8080/journals/add", json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Auto/route not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void addAuto() throws JSONException {
        JSONObject json = Utils.readAuto(in);
        try {
            add("http://localhost:8080/autos/add", json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Personnel not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }

    }
    private static void addPersonnel() throws JSONException {
        JSONObject json = Utils.readPersonnel(in);
        try {
            add("http://localhost:8080/personnels/add", json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void addRoute() throws JSONException {
        JSONObject json = Utils.readRoute(in);
        try {
            add("http://localhost:8080/routes/add", json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    // update
    private static void updateById(String url, String id, JSONObject json) {
        HttpEntity<String> request = new HttpEntity<>(json.toString(), header);
        ResponseEntity<Object> response = restTemplate.exchange(
                url + "/" + id, HttpMethod.PUT, request, Object.class
        );
        System.out.println(response.getBody());
    }
    private static void updateJournalById() throws JSONException {
        String id = Utils.readId(in);
        JSONObject json = Utils.readJournal(in);
        try {
            updateById("http://localhost:8080/journals/update", id, json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Journal not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void updateAutoById() throws JSONException {
        String id = Utils.readId(in);
        JSONObject json = Utils.readAuto(in);
        try {
            updateById("http://localhost:8080/autos/update", id, json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Auto not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void updatePersonnelById() throws JSONException {
        String id = Utils.readId(in);
        JSONObject json = Utils.readPersonnel(in);
        try {
            updateById("http://localhost:8080/personnels/update", id, json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Personnel not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void updateRouteById() throws JSONException {
        String id = Utils.readId(in);
        JSONObject json = Utils.readRoute(in);
        try {
            updateById("http://localhost:8080/routes/update", id, json);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Route not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    // complicated
    private static void getAllBySubjectId(String url) {
        String id = Utils.readId(in);
        ResponseEntity<List<Object>> response = restTemplate.exchange(
                url + "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){}
        );
        List<Object> list = Objects.requireNonNull(response.getBody());
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    private static void getJournalsByAutoId() {
        try {
            getAllBySubjectId("http://localhost:8080/journals/find-by-auto");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Auto not found");
            } else {
                throw e;
            }
        }
    }
    private static void getJournalsByRouteId() {
        try {
            getAllBySubjectId("http://localhost:8080/journals/find-by-route");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Route not found");
            } else {
                throw e;
            }
        }
    }
    private static void getAutoByPersonnelId() {
        try {
            getAllBySubjectId("http://localhost:8080/autos/find-by-personnel");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Personnel not found");
            } else {
                throw e;
            }
        }
    }

    private static void getJournalsByAutoNum() {
        System.out.println("Num:");
        String num = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z0-9]+");
        String url = "http://localhost:8080/journals/find-by-auto-num/" + num;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    private static void getAutosByPersonnelLastName() {
        System.out.println("Last name:");
        String lastName = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        String url = "http://localhost:8080/autos/find-by-personnel-last-name/" + lastName;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    // auto
    private static void getAutoByNum() {
        System.out.println("Num:");
        String num = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z0-9]+");
        String url = "http://localhost:8080/autos/find-by-num/" + num;
        try {
            Object obj = restTemplate.getForObject(url, Object.class);
            System.out.println(obj);
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("Auto not found");
            } else {
                throw e;
            }
        }
    }
    private static void getAutosByColor() {
        System.out.println("Color:");
        String color = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        String url = "http://localhost:8080/autos/find-by-color/" + color;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    private static void getAutosByMark() {
        System.out.println("Mark:");
        String mark = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        String url = "http://localhost:8080/autos/find-by-mark/" + mark;
        List list = restTemplate.getForObject(url, List.class);
        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    // authorization
    private static void signIn() throws JSONException {
        System.out.println("Username: ");
        String username = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");
        System.out.println("Password: ");
        String password = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z0-9]+");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);
        String url = "http://localhost:8080/auth/signin";
        try {
            String token = restTemplate.postForObject(url, request, String.class);
            if (token != null) {
                header.set("Authorization", "Bearer " + token);
                System.out.println("Authorization succeeded");
            }
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Bad credentials");
            } else {
                throw e;
            }
        }
    }
    private static void addUser() throws JSONException {
        System.out.println("Username: ");
        String username = Utils.readWithRegex(in, "[а-яА-Яa-zA-Z]+");
        System.out.println("Password: ");
        String password = Utils.readWithRegex(in, "[a-zA-Z]+");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);
        String url = "http://localhost:8080/auth/add-user";
        try {
            restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
            System.out.println("User added with privilege USER");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("User not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void deleteUser() throws JSONException {
        System.out.println("Username: ");
        String username = Utils.readWithRegex(in,"[а-яА-Яa-zA-Z]+");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);
        String url = "http://localhost:8080/auth/delete-user";
        try {
            restTemplate.exchange(url, HttpMethod.DELETE, request, Object.class);
            System.out.println("Successful deletion");
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();

            if (status.equals(HttpStatus.NOT_FOUND)) {
                System.out.println("User not found");
            } else if (status.equals(HttpStatus.FORBIDDEN)) {
                System.out.println("Access denied");
            } else {
                throw e;
            }
        }
    }
    private static void getAllUsers() throws JSONException {
        String url = "http://localhost:8080/auth/list-users";
        List list = restTemplate.getForObject(url, List.class);

        if (list.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            list.forEach(System.out::println);
        }
    }
    // help
    private static void getCommands() {
        String[] commands;
        commands = new String[]{
            "get journal by id",
            "get auto by id",
            "get personnel by id",
            "get route by id",
            "list journals",
            "list autos",
            "list personnels",
            "list routes",
            "add journal [USER, ADMIN]",
            "add auto [USER, ADMIN]",
            "add personnel [USER, ADMIN]",
            "add route [USER, ADMIN]",
            "update journal by id [USER, ADMIN]",
            "update auto by id [USER, ADMIN]",
            "update personnel by id [USER, ADMIN]",
            "update route by id [USER, ADMIN]",
            "delete journal by id [ADMIN]",
            "delete auto by id [ADMIN]",
            "delete personnel by id [ADMIN]",
            "delete route by id [ADMIN]",
            "get journals by auto id",
            "get journals by route id",
            "get autos by personnel id",
            "get journals by auto num",
            "get autos by personnel last name",
            "get auto by num",
            "get autos by color",
            "get autos by mark",
            "sign in [ADMIN]",
            "add user [ADMIN]",
            "delete user [ADMIN]",
        };
        Arrays.stream(commands).forEach(System.out::println);
    }

    public static void main(String[] args) {
        header.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("Hello! In order to see all available commands, enter 'help'");
        String command = in.nextLine();
        while (!(command.equals("exit"))) {
            try {
                // 29
                switch (command) {
                    case "get journal by id" -> getJournalById();
                    case "get auto by id" -> getAutoById();
                    case "get personnel by id" -> getPersonnelById();
                    case "get route by id" -> getRouteById();
                    case "add journal" -> addJournal();
                    case "add auto" -> addAuto();
                    case "add personnel" -> addPersonnel();
                    case "add route" -> addRoute();
                    case "update journal by id" -> updateJournalById();
                    case "update auto by id" -> updateAutoById();
                    case "update personnel by id" -> updatePersonnelById();
                    case "update route by id" -> updateRouteById();
                    case "delete journal by id" -> deleteJournalById();
                    case "delete auto by id" -> deleteAutoById();
                    case "delete personnel by id" -> deletePersonnelById();
                    case "delete route by id" -> deleteRouteById();
                    case "list journals" -> getAllJournals();
                    case "list autos" -> getAllAuto();
                    case "list personnels" -> getAllPersonnel();
                    case "list routes" -> getAllRoutes();
                    case "get journals by auto id" -> getJournalsByAutoId();
                    case "get journals by route id" -> getJournalsByRouteId();
                    case "get autos by personnel id" -> getAutoByPersonnelId();
                    case "get journals by auto num" -> getJournalsByAutoNum();
                    case "get autos by personnel last name" -> getAutosByPersonnelLastName();
                    case "get auto by num" -> getAutoByNum();
                    case "get autos by color" -> getAutosByColor();
                    case "get autos by mark" -> getAutosByMark();
                    case "sign in" -> signIn();
                    case "add user" -> addUser();
                    case "delete user" -> deleteUser();
                    case "list users" -> getAllUsers();
                    case "help" -> getCommands();
                    case "" -> System.out.print("");
                    default -> System.out.println("Wrong command entered");
                }
            } catch (JSONException | IllegalArgumentException e) {
                System.out.println("Invalid arguments entered");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong, please try again");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            command = in.nextLine();
        }
    }
}
