package Data;

public class Location {
    private String city;
    private String countryCode;
    private String location;

    public Location(String inputCountryCode,String inputCity,String inputLocation)
    {
        location = inputLocation;
        city = inputCity;
        countryCode = inputCountryCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLocation() {
        return location;
    }
}
