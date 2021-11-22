
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ReadFileSAX {

    public static void main(String [] args)
    {

        String fname = "bme.xml";
        double lat = 47.4786346;
        double lon = 19.0555773;

        for (int i = 0; i < args.length; i++)
        {
            if(args[i].equals("-i"))
                fname = args[i+1];
            else if (args[i].equals("-lat"))
                lat = Double.parseDouble(args[i+1]);
            else if (args[i].equals("-lon"))
                lat = Double.parseDouble(args[i+1]);
        }

        SAXParserFactory fy = SAXParserFactory.newInstance();

        try
        {

            SAXParser sxp = fy.newSAXParser();
            BusStopHandler handler = new BusStopHandler(lat,lon);
            TagCounter tc=new TagCounter();
            File f = new File(fname);
            sxp.parse(f, handler);
            sxp.parse(f, tc);

            List <BusStop> BSList = handler.getList();
            Collections.sort(BSList, Comparator.comparingDouble(BusStop::getdistance));
            BSList.forEach(bs -> System.out.println(bs));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
