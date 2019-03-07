package services;

import model.IpReputation;

import java.net.InetAddress;

// An IP reputation lookup service
public class IpReputationVendor {

    public IpReputation getReputation(InetAddress ipAddress) {
        // Assume we call the servie and get the result
        return new IpReputation();
    }
}
