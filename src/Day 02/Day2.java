import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Goal: Parse and extract the information retrieved from the JSON.
 */

public class Day2 {

    /**
     * Given an url perform a http request and return a response
     * @param url a valid url
     * @return a HttpResponse response in String format
     * @throws IOException
     * @throws InterruptedException
     */
    private static HttpResponse<String> httpRequest(String url) throws IOException, InterruptedException {

        // create the client
        HttpClient client = HttpClient.newHttpClient();

        // build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // client send the request and the method returns the response
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * parse a JSON file that contains a list of films with a range of attributes
     * @param jsonMovies
     * @return an array of String witch each element is a String with a range of attributes separated by comma ","
     */
    private static String[] parseJsonMovies(String jsonMovies) {

        // find the index of 'begin' and 'end' of the content
        int begin = jsonMovies.indexOf('[');
        int end   = jsonMovies.indexOf(']');

        // maintains only the content surrounded by the square brackets
        jsonMovies = jsonMovies.substring(begin + 1, end - 1);

        // simple remove the open curly brackets "{" from the content
        jsonMovies = jsonMovies.replace("{","");

        // use the close curly bracket as a delimiter to create a list where each element is an item from the json
        String[] listOfMovies = jsonMovies.split("},");

        return listOfMovies;
    }

    /**
     * Given a listOfMovies and an attribute, return a new list with the information of the attribute of all movies
     * @param listOfMovies a list which each element is a movie with a range of attributes separated by comma ","
     * @param att the target attribute that we want to get all values
     * @return a list with the value of the attribute from all movies
     */
    private static List<String> getAttributesValues(String[] listOfMovies, String att) {

        // will store the results
        List<String> results = new ArrayList<>();

        // define a regex pattern to retrieve the information about the target attribute
        String pattern = "(?<=\"" + att + "\":\").+?(?=\")";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // for each movie in the list of movies
        for (String movie: listOfMovies){

            // match the pattern against the movie
            r.matcher(movie)
                    .results()                       // Stream<MatchResult>
                    .map(mr -> mr.group())          // Stream<String> - the group of each result
                    .forEach(e -> results.add(e)); // add to the list of results
        }

        return results;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        Properties props = new Properties();

        // load the properties from the local file
        try {
            InputStream input = new FileInputStream("local.properties");
            props.load(input);
        }
        catch (IOException exception){
            System.out.println("Create local.properties file in the root of the project and add the IMDB \"apiKey\" value to it.");
            return;
        }

        // get the IMDB apiKey
        final String API_KEY = props.getProperty("apiKey");

        // build the url with the apiKey
        String url = String
                .format("https://imdb-api.com/en/API/Top250Movies/%s", API_KEY) ;

        // get the response
        HttpResponse<String> response = httpRequest(url);

        // get the json content from the response
        String jsonMovies = response.body();

        // parse the json content into a list of strings where each element is a movie
        String[] listOfMovies = parseJsonMovies(jsonMovies);

        // all the attributes of each film
        String[] attributes = {"id", "rank", "title", "fullTitle", "year", "image", "crew", "imDbRating", "imDbRatingCount"};

        // get all titles from the list of movies
        List<String> listOfTitles = getAttributesValues(listOfMovies, attributes[2]);

        // get image url of all movies from the list of movies
        List<String> listOfImages = getAttributesValues(listOfMovies, attributes[5]);

        // print all the titles
        listOfTitles.forEach(System.out::println);
    }

}
