import java.net. *;
import java.io. *;
import java.util. *;

public class Crawler {
	public static final String PRE_LINK = "a href";
	public static final String AFTER_LINK = "\">";
	static LinkedList <URLdepth> checkedLinks = new LinkedList <URLdepth> ();
	static LinkedList <URLdepth> uncheckedLinks = new LinkedList <URLdepth>();
	
	public static void main (String [] args) throws IOException {
		String [] mainLink = {"http://ito.edu.ru/","4"};
		try {
			Process(mainLink[0], Integer.parseInt(mainLink[1]));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void Process(String pair, int maxDepth) throws IOException 
    {
		uncheckedLinks.add(new URLdepth(pair, 0));
        while (!uncheckedLinks.isEmpty()) 
        {
            URLdepth currentPair = uncheckedLinks.removeFirst(); 
            if (currentPair.getDepth() < maxDepth)
            {
                Socket my_socket = new Socket(currentPair.getHost(), 80);
                my_socket.setSoTimeout(1000);
                try 
                {
                    BufferedReader in = new BufferedReader(new InputStreamReader(my_socket.getInputStream()));
                    PrintWriter out = new PrintWriter(my_socket.getOutputStream(), true);
                    request(out, currentPair);
                    String line;
                    while ((line = in.readLine()) != null) 
                    {
                        if (line.indexOf(currentPair.URL_PREFIX) != -1 && line.indexOf('"') != -1) 
                        {
                            StringBuilder currentLink = new StringBuilder();
                            int i = line.indexOf(currentPair.URL_PREFIX);
                            while (line.charAt(i) != '"' && line.charAt(i) != ' ') 
                            {
                                if (line.charAt(i) == '<') 
                                {
                                    currentLink.deleteCharAt(currentLink.length() - 1);
                                    break;
                                }
                                else 
                                {
                                    currentLink.append(line.charAt(i));
                                    i++;
                                }
                            }
                            URLdepth newPair = new URLdepth(currentLink.toString(), currentPair.getDepth() + 1);
                            if (currentPair.check(uncheckedLinks, newPair) && currentPair.check(checkedLinks, newPair) && !currentPair.getUrl().equals(newPair.getUrl()))
                            {
                            	uncheckedLinks.add(newPair);
                            }
                        }
                    }
                    my_socket.close();
                } 
                catch (SocketTimeoutException e) 
                {
                    my_socket.close();
                }
            }
            checkedLinks.add(currentPair);
        }
        print(checkedLinks);
    }
	
	
    public static void request(PrintWriter out, URLdepth pair) throws MalformedURLException 
    {
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();
    }
    
    public static void print (LinkedList<URLdepth> viewedLink) 
    {
        for (URLdepth c : checkedLinks)
        {
            System.out.println("Depth: "+c.getDepth() + "\tURL: "+c.getUrl());
        }
    }
}
