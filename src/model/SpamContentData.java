package model;

/*
    This represent the database schema for storing training data
 */
public class SpamContentData {
    private Result _classificationResult;
    private String _textContent;
    private String _imagePath;
    private IpReputation _ipReputation;
    private Location _geoLocation;

    public SpamContentData(Result result, String textContent, String imagePath, IpReputation ipReputation, Location location) {
        _classificationResult = result;
        _textContent = preprocessTextContent(textContent);
        _imagePath = imagePath;
        _ipReputation = ipReputation;
        _geoLocation = location;
    }

    public IpReputation getIpReputation() {
        return _ipReputation;
    }

    public Result getCassificationResult() {
        return _classificationResult;
    }

    public String getImagePath() {
        return _imagePath;
    }

    public Location getGeoLocation() {
        return _geoLocation;
    }

    public String getTextContent() {
        return _textContent;
    }

    private String preprocessTextContent(String textContent) {
        // Assume we do some pre-processing work here to minimize content stored
        // obfuscate/remove any sensitive information such as email address, phone number, social security
        // it won't be a perfect "cleaning"
        // it still require offline data preparation
        return textContent;
    }

    @Override
    public String toString() {
        return String.format("ClassificationResult: $s; textContent: %s; imagePath: %s; ipReputation: %s; geolocation: %s",
                _classificationResult,
                _textContent,
                _imagePath,
                _ipReputation,
                _geoLocation);
    }

    public enum Result {
        SPAM,
        HAM
    }
}
