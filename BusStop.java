
public class BusStop {

    private String name;
    private String oldName;
    private String wheelchair;
    private double distance;
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getName()
    {
        return name;
    }

    public void setdistance(double distance)
    {
        this.distance = distance;
    }

    public String getOldName()
    {
        return oldName;
    }

    public void setOldName(String oldName)
    {
        this.oldName = oldName;
    }

    public String getWheelchair()
    {
        return wheelchair;
    }

    public void setWheelchair(String wheelchair)
    {
        this.wheelchair = wheelchair;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getdistance()
    {
        return distance;
    }

    @Override
    public String toString() {
        return "Megallo: nev:" + name + " (" + oldName + ") " + "kerekesszek:" + wheelchair + " distance:" + distance;
    }

    public double dist1(double lat1, double lon1, double lat2, double lon2)
    {
        double R = 6371000; // metres
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dphi = phi2-phi1;
        double dl = Math.toRadians(lon2-lon1);
        double a = Math.sin(dphi/2) * Math.sin(dphi/2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(dl/2) * Math.sin(dl/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;

        return d;
    }
}
