
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class BusStopHandler extends DefaultHandler {


    private double lat = 0.0;
    private double lon = 0.0;
    private double slat = 0.0;
    private double slon = 0.0;
    private String k = "";
    private List<BusStop> BSList = new ArrayList<>();
    private BusStop bs = null;

    public List<BusStop> getList() {
        return BSList;
    }

    public BusStopHandler(double lat1, double lon1) {
        lat = lat1;
        lon = lon1;
    }


    public void startElement(String uri, String localName, String qName, Attributes att) throws SAXException {
        if (qName.equals("node")) {
            String k = null;
            bs = new BusStop();
            bs.setValid(false);
            if (BSList == null)
                BSList = new ArrayList<>();
            k = att.getValue("lat");
            if (k != null)
                slat = Double.parseDouble(k);
            k = att.getValue("lon");
            if (k != null)
                slon = Double.parseDouble(k);
            k = null;

        } else if (qName.equals("tag")) {
            k = att.getValue("k");
            if (k != null) {
                switch (k) {
                    case "name":
                        bs.setName(att.getValue("v"));
                        break;

                    case "old_name":
                        bs.setOldName(att.getValue("v"));
                        break;

                    case "wheelchair":
                        bs.setWheelchair(att.getValue("v"));
                        break;

                    case "highway":
                        if (att.getValue("v").equals("bus_stop")) {
                            bs.setValid(true);
                            bs.setdistance(bs.dist1(lat, lon, slat, slon));
                        }
                        break;
                }
            }
        k=null;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("node")) {
            if (bs.isValid()) {
                BSList.add(bs);
            }
        }
    }
}