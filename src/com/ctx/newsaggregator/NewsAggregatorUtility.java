package com.ctx.newsaggregator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.Normalizer;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Node;

public class NewsAggregatorUtility {
	
	public static String StringToSHA1Hash(String stringToBeHashed) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(stringToBeHashed.getBytes());
		
		return bytesToHex(md.digest());
	}
	
	public static String bytesToHex(byte[] b) {
		char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuffer buf = new StringBuffer();
		for (int j=0; j<b.length; j++) {
			buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
			buf.append(hexDigit[b[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	public static String fetchWebAsString(String url) {
		
		StringBuffer webAsString = new StringBuffer();
		
		try {
			InputStream is = new URL(url).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			while ( (line = br.readLine()) != null)
				webAsString.append(line);
			
			br.close();
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return webAsString.toString();

	}
	
	public static String fetchPageHTML(String URL) throws IOException{

        System.getProperties().setProperty("httpclient.useragent", "spider");

        String pageHTML = "";

        InputStream instream = new URL(URL).openStream();
        String encoding = "UTF-8";

        StringWriter writer = new StringWriter();
        IOUtils.copy(instream, writer, encoding);

        pageHTML = writer.toString();

        // convert entire page scrape to ASCII-safe string
        //pageHTML = Normalizer.normalize(pageHTML, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        pageHTML = pageHTML.replaceAll("'", " ");

        return pageHTML;
    }
	
    public static void removeComments(Node node) {
        for (int i = 0; i < node.childNodes().size();) {
            Node child = node.childNode(i);
            if (child.nodeName().equals("#comment"))
                child.remove();
            else {
                removeComments(child);
                i++;
            }
        }
    }  
    
    public static int computeLevenshteinDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
     
        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
          int lastValue = i;
          for (int j = 0; j <= s2.length(); j++) {
            if (i == 0)
              costs[j] = j;
            else {
              if (j > 0) {
                int newValue = costs[j - 1];
                if (s1.charAt(i - 1) != s2.charAt(j - 1))
                  newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                costs[j - 1] = lastValue;
                lastValue = newValue;
              }
            }
          }
          if (i > 0)
            costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
      }
	
}
