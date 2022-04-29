import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient{


    /**
     * Given an url perform a http request and return a response
     * @param url a valid url
     * @return a HttpResponse response in String format
     * @throws IOException simple ignore the exception
     * @throws InterruptedException simple ignore the exception
     */
    public static HttpResponse<String> httpRequest(String url) throws IOException, InterruptedException {

        // create the client
        HttpClient client = HttpClient.newHttpClient();

        // build the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // client send the request and the method returns the response
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }


}