package services;

import model.SpamContentData;

public class ImageRecognitionService {

    public SpamContentData.Result score(String imagePath) {
        // Send the image file to one of our trained image recognition vendors

        // Assume the vendor came back with the result 90% SPAM
        return SpamContentData.Result.SPAM;
    }
}
