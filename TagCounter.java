import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class TagCounter extends DefaultHandler {
    HashMap<String, Integer> tags=new HashMap<>();
    public void startElement(String namespaceURI, String sName, String qName, Attributes attrs){
        if(!tags.containsKey(qName)){
            tags.put(qName, 0);
        }
        if(tags.containsKey(qName)){
            tags.replace(qName, tags.get(qName)+1);
        }
    }
    public void endDocument(){
        for(String s : tags.keySet()){
            System.out.println(s+": "+tags.get(s));
        }
    }
}
