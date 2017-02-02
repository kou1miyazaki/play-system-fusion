package utils.cmn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Web extends SystemFusionUtils {

	public static void main (String[] args) {
		try {
			URL url = new URL(args[0]);
			Object content = url.getContent();
			if (content instanceof InputStream) {
	            BufferedReader bf = new BufferedReader(new InputStreamReader((InputStream)content));
	            String line;
	            while ((line = bf.readLine()) != null) {
	                System.out.println(line);
	            }
	        }
	        else {
	            System.out.println(content.toString());
	        }
	    }
	    catch (ArrayIndexOutOfBoundsException e) {
	        System.err.println("引数にURLを指定してください");
	            System.exit(-1);
	        }
	        catch (IOException e) {
	            System.err.println(e);
	            System.exit(-1);
	    }
	}
}
