import java.io.*;
import java.util.List;
import java.util.Properties;

public class Day6 {

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


        // will use the new implementation of ImdbJsonParser to encapsulate the behaviour of the parser
        ImdbMovieJsonParser parser = new ImdbMovieJsonParser();

        // encapsulate the list into a list of objects instance of Movie class
        List<Movie> listOfMovies = (List<Movie>) parser.parse(url);


        // maintains the same behaviour of Day 05 from here

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
