package in.terxlabs.rtbms.dashboard.request.bean;

public class Hospital {

    String name;
    private String address;
    private String image;
    private String url;
    private String phone;
    private String hid;
    private String email;
    private String latitude;
    private String longitude;
    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Hospital(String photoURL) {
        this.photoURL = photoURL;
    }

    private  String photoURL;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }







    public Hospital(String name, String address, String image, String url, String latitude, String longitude, String hid, String email) {
        this.name = name;
        this.hid=hid;
        this.address = address;
        this.image = image;
        this.url = url;
        this.email=email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Hospital(){

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
