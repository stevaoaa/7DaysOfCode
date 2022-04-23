import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    PrintWriter writer;

    private String head =
            """
            <!DOCTYPE html>
            <html lang="en">
            <meta charset="UTF-8">
            <title>IMDB Top 250</title>
            <meta name="viewport" content="width=device-width,initial-scale=1">
            <link rel="stylesheet" href="">
            
            <head>
                <meta charset=\"utf-8\">
                <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
                <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
                    + "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">					
            </head>
            
            <style>
            </style>
            <script src=""></script>
            <body>
            """;

    private String divTemplate =
            """
            <div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
                <h4 class=\"card-header\">%s</h4>
                <div class=\"card-body\">
                    <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                    <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
                </div>
            </div>
            """;

    private String foot =
            """
            </body>
            </html>               
            """;
    public HTMLGenerator(PrintWriter writer){
        this.writer = writer;
    }


    public void generator(List<Movie> listOfMovies){

        // create the first part of the HTML
        writer.println(head);

        // create the divs with the real content
        for (Movie movie: listOfMovies) {

            // using the template with the dada of the given movie
            writer.println(String.format(divTemplate, movie.getTitle(), movie.getUrlImage(), movie.getFullTitle(), movie.getImDbRating(), movie.getYear()));
        }

        // finish the html skeleton
        writer.println(foot);

    }
}
