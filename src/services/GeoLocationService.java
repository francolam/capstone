package services;


import model.Location;

import java.net.InetAddress;

// A Geo Location lookup service
public class GeoLocationService {
    public Location getLocation(InetAddress ipAddress) {
        // Lookup geolocation by IP Address
        return new Location();
    }
}
