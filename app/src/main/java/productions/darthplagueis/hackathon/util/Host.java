package productions.darthplagueis.hackathon.util;

public enum Host {

    NycOpenDataApi("https://data.cityofnewyork.us/");

    private final String url;

    Host(final String url) {this.url = url;}

    public String getUrl() {return this.url;}
}
