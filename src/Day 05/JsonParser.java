import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JsonParser{


    /**
     * parse a JSON file that contains a list of films with a range of attributes
     * @param jsonMovies a json file returned from imdb api
     * @return an array of String witch each element is a String with a range of attributes separated by comma ","
     */
    public static String[] parseJsonMovies(String jsonMovies) {

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
    public static List<String> getAttributesValues(String[] listOfMovies, String att) {

        // will store the results
        List<String> results = new ArrayList<>();

        // define a regex pattern to retrieve the information about the target attribute
        String pattern = "(?<=\"" + att + "\":\").+?(?=\")";

        // Create a Pattern object
        Pattern p = Pattern.compile(pattern);

        // for each movie in the list of movies
        for (String movie: listOfMovies){

            // match the pattern against the movie
            p.matcher(movie)
                    .results()                       // Stream<MatchResult>
                    .map(mr -> mr.group())          // Stream<String> - the group of each result
                    .forEach(e -> results.add(e)); // add to the list of results
        }

        return results;
    }

    public static List<Movie> getMovies(String[] listOfMovies){

        // will store the results
        List<Movie> results = new ArrayList<>();

        // define a regex pattern to retrieve the value content after any key attribute ("id":, "rank":, "title": ...)
        String regex = "(?<=:\").+?(?=\")";

        // compile the pattern
        Pattern p = Pattern.compile(regex);

        // for each entry
        for(String movie: listOfMovies){

            // will store the elements of a movie
            List<String> matches = new ArrayList<>();

            // spread each attribute of the movie as an element of the list
            p.matcher(movie)
                    .results()
                    .map(mr -> mr.group())
                    .forEach(r -> matches.add(r));

            // create local variables to assign to the Movie instance
            String id = matches.get(0);
            String rank = matches.get(1);
            String title = matches.get(2);
            String fullTitle = matches.get(3);
            int year = Integer.parseInt(matches.get(4));
            String urlImage = matches.get(5);
            String crew = matches.get(6);
            float imDbRating = Float.parseFloat(matches.get(7));
            int imDbRatingCount = Integer.parseInt(matches.get(8));

            // create a movie instance
            Movie m = new Movie(id, rank, title, fullTitle, year, urlImage, crew, imDbRating, imDbRatingCount);

            // add to the result list
            results.add(m);

        }

        return results;
    }


}