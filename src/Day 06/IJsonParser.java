import java.io.IOException;
import java.util.List;

public interface IJsonParser {

    public List<? extends Content> parse(String url) throws IOException, InterruptedException;

}
