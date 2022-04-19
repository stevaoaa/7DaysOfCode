import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

/**
 * Goal: access IMDB Api, create a http client, perform a request and print the result in a JSON format.
 */

public class Day1 {
	
	public static void main(String[] args) throws IOException, InterruptedException {

		Properties props = new Properties();

		try {
			InputStream input = new FileInputStream("local.properties");
			props.load(input);
		}
		catch (IOException exception){
			System.out.println("Create local.properties file in the root of the project and add the IMDB \"apiKey\" value to it.");
			return;
		}

		final String API_KEY = props.getProperty("apiKey");

		String url = String
				.format("https://imdb-api.com/en/API/Top250Movies/%s", API_KEY) ;

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.build();

		HttpResponse<String> response =
				client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.statusCode());
		System.out.println(response.body());

	}
}
