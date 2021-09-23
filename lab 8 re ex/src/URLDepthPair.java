import java.util.LinkedList;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair 
{
	
	public static final String URL_PREFIX =  "http://";
    public String url;
    private int depth;
    URL hostPath;
    
    public static boolean isurlValid(String url) 
    {
        if (url == null)
            return false;
        Pattern urlValidationPattern = Pattern.compile(URL_PREFIX);
        return urlValidationPattern.matcher(url).find();
    }

    public URLDepthPair (String url, int depth) throws MalformedURLException
    {
    	if (!isurlValid(url))
    	{
            throw new MalformedURLException();
    	}
        this.url=url;
        this.depth=depth;
        try 
        {
            this.hostPath= new URL(url);
        } 
        catch (MalformedURLException e) 
        {
            e.printStackTrace();
        }
    }

    public String getHost()
    {
        return hostPath.getHost();
    }
    
    public String getPath()
    {
        return hostPath.getPath();
    }
    
    public int getDepth() 
    {
        return depth;
    }
    
    public String getURL() 
    {
        return url;
    }

    public static boolean check(LinkedList<URLDepthPair> resultLink, URLDepthPair pair) 
    {
        boolean isAlready = true;
        for (URLDepthPair c : resultLink)
        {
            if (c.getURL().equals(pair.getURL()))
            {
                isAlready=false;
            }
        }
        return isAlready;
    }
}