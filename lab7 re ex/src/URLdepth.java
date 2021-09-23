import java.net. *;
import java.io. *;
import java.util. *;

public class URLdepth {
	public static final String URL_PREFIX = "http://";
	private String url;
	private int depth;
	
	public URLdepth (String s, int n) { 
		this.depth = n;
		this.url = s;
	}
	
	public String getHost () throws MalformedURLException {
		URL s = new URL(this.url);
		return s.getHost();
	}
	
    public String getPath() throws MalformedURLException 
    {
        URL s = new URL(this.url);
        return s.getPath();
    }
	
	public String getUrl () {
		return this.url;
	}
	
	public int getDepth () {
		return this.depth;
	}
	
	public static boolean check(LinkedList<URLdepth> resultLink, URLdepth pair) 
    {
        boolean isAlready = true;
        for (URLdepth c : resultLink)
        {
            if (c.getUrl().equals(pair.getUrl()))
            {
                isAlready=false;
            }
        }
        return isAlready;
    }
}
