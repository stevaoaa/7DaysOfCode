import java.io.*;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Properties;

/**
 * Goal: Encapsulate methods in other classes.
 */

public class Day5 {


    public static void main(String[] args) throws IOException, InterruptedException {

        Properties props = new Properties();

        // load the properties from the local file
        try {
            InputStream input = new FileInputStream("local.properties");
            props.load(input);
        }
        catch (IOException exception){
            System.out.println("WARNING: Create local.properties file in the root of the project and add the IMDB \"apiKey\" value to it.");
            return;
        }

        // get the IMDB apiKey
        final String API_KEY = props.getProperty("apiKey");

        // build the url with the apiKey
        String url = String
                .format("https://imdb-api.com/en/API/Top250Movies/%s", API_KEY) ;

        // get the response from the ImdbClient
        HttpResponse<String> response = ImdbApiClient.httpRequest(url);

        // get the json content from the response
        String jsonMovies = response.body();

        // parse the json content into a list of strings where each element is a movie
        String[] listOfMoviesInString = ImdbMovieJsonParser.parseJsonMovies(jsonMovies);

        // encapsulate the list into a list of objects instance of Movie class
        List<Movie> listOfMovies = ImdbMovieJsonParser.getMovies(listOfMoviesInString);

        // the file that will store the HTML generated page
        File file = new File ("index.html");

        // the writer that will handle the generation process
        PrintWriter printWriter = new PrintWriter (file);

        // create an instance of the HTMLGeneration class
        HTMLGenerator htmlGenerator = new HTMLGenerator(printWriter);

        // generate the HTML
        htmlGenerator.generator(listOfMovies);

        // at the end of the process close the writer
        printWriter.close ();


    }

}
