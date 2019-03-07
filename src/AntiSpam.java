import model.IpReputation;
import model.SpamContentData;
import services.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.util.logging.Logger;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/antispam")
public class AntiSpam {
    private static final DataStoreClient dataStoreClient = new DataStoreClient();
    private static final GeoLocationService geoLocationService = new GeoLocationService();

    @POST
    @Path("/scoreText")
    public SpamContentData.Result scoreTextContent(HttpServletRequest request,
                                                   long memberId,
                                                   String textContent) {
        SpamContentData.Result result = SpamContentData.Result.HAM;
        try {
            SpamFIlterVendor spamFilterVendor = new SpamFIlterVendor();

            // In the future, when our model is mature to measure performance in production
            // we can switch the scoring service from spam filter to our model
            result = spamFilterVendor.score(textContent);

            checkResult(result, memberId);

            InetAddress ipAddress = InetAddress.getByName(request.getRemoteAddr());
            IpReputation ipReputation = getIpReputation(ipAddress);
            SpamContentData spamContentData = new SpamContentData(result,
                    textContent, null, ipReputation, geoLocationService.getLocation(ipAddress));
            dataStoreClient.store(spamContentData);
        } catch (Exception e) {
            Logger.getLogger(AntiSpam.class.getName()).severe(e.getMessage());
        }

        return result;
    }

    @POST
    @Path("/scoreImage")
    public SpamContentData.Result scoreImageContent(HttpServletRequest request,
                                                   long memberId,
                                                   String imagePath) {
        SpamContentData.Result result = SpamContentData.Result.HAM;
        try {
            ImageRecognitionService imageRecognitionService = new ImageRecognitionService();
            result = imageRecognitionService.score(imagePath);

            checkResult(result, memberId);

            InetAddress ipAddress = InetAddress.getByName(request.getRemoteAddr());
            IpReputation ipReputation = getIpReputation(ipAddress);
            SpamContentData spamContentData = new SpamContentData(result,
                    null, imagePath, ipReputation, geoLocationService.getLocation(ipAddress));
            dataStoreClient.store(spamContentData);
        } catch (Exception e) {
            Logger.getLogger(AntiSpam.class.getName()).severe(e.getMessage());
        }

        return result;
    }

    private void checkResult(SpamContentData.Result result, long memberId) {
            CountingService countingService = new CountingService();
            if (result == SpamContentData.Result.SPAM) {
                countingService.count(memberId);
                if (countingService.getCount(memberId) >= 10) {
                    // send a request to restriction service to restrict the member from using the message feature
                }
            }
    }

    private IpReputation getIpReputation(InetAddress ipAddress) {
        IpReputationVendor ipReputationVendor = new IpReputationVendor();
        return ipReputationVendor.getReputation(ipAddress);
    }
}