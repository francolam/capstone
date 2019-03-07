package model;

import java.util.Calendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Location {
    private long _latitude;
    private long _altitude;
    private String _city;
    private String _country;
    private TimeZone _timeZone;

    public Location() {
        _altitude = 0;
        _latitude = 0;
        _city = "Los_angeles";
        _country = "us";
        _timeZone = new SimpleTimeZone(-28800000,
                                    "America/Los_Angeles",
                                        Calendar.APRIL,
                                1,
                                        -Calendar.SUNDAY,
                                7200000,
                                        Calendar.OCTOBER,
                                -1,
                                        Calendar.SUNDAY,
                                7200000,
                              3600000);
    }

    public long getAltitude() {
        return _altitude;
    }

    public long getLatitude() {
        return _latitude;
    }

    public String getCity() {
        return _city;
    }

    public String getCountry() {
        return _country;
    }

    public TimeZone getTimeZone() {
        return _timeZone;
    }

    @Override
    public String toString() {
        return String.format("latitude: %s; altitude: %s; city: %s; country: %s; timeZone: %s",
                _latitude,
                _altitude,
                _city,
                _country,
                _timeZone);
    }
}
