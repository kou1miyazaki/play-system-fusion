package samples;
import java.util.Arrays;
import java.util.List;

public class StreamSample {

	public static void main(String[] args) {


		List<String> names = (List<String>) Arrays.asList("hoge hoge", "foo bar", "naoki", "kishida");

		names.stream()
		  .map(s -> "[" + s + "]")
		  .forEach(System.out::println);
	}

}
