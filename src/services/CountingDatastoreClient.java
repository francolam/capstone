package services;

import java.util.HashMap;
import java.util.Map;

// A counting service to track how many times a member sent spam messages in one day
public class CountingDatastoreClient {
    Map<Long, Long> counter= new HashMap<>();

    public void count(long memberId) {
        counter.put(memberId, counter.get(memberId) + 1);
    }

    public long getCount(long memberId) {
        return counter.get(memberId);
    }
}
