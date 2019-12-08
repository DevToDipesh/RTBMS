package in.terxlabs.rtbms.dashboard.events.bean;

public class Events {
    public Events(String name   ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCityPincode() {
        return cityPincode;
    }

    public void setCityPincode(String cityPincode) {
        this.cityPincode = cityPincode;
    }

    private String date;

    public Events(String date, String time, String venue, String cityPincode) {
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.cityPincode = cityPincode;
    }

    private String time;
    private String venue;
    private String cityPincode;
    public Events(){

    }

}
