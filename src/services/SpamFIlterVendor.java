package services;

import model.SpamContentData;

// A spam filter service
public class SpamFIlterVendor {

    public SpamContentData.Result score(String textContent) {
        // Assume we call teh service and get the result
        return SpamContentData.Result.SPAM;
    }
}
