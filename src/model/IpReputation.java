package model;

import java.util.ArrayList;
import java.util.List;

// Reference: https://apility.io/apidocs/#full-ip-address-reputation
public class IpReputation {
    private DomainType _domainType;
    private List<String> _mxRecords;
    private List<Blacklist> _blacklist;
    private long _ipScore;
    private long _domainScore;
    private boolean _isQuarantined;

    public IpReputation() {
        _domainType = DomainType.BAD_DOMAIN;
        _mxRecords = new ArrayList<>();
        _mxRecords.add("mail2.mailinator.com");
        _mxRecords.add("mail.mailinator.com");
        _blacklist = new ArrayList<>();
        _blacklist.add(Blacklist.DEA);
        _blacklist.add(Blacklist.FREEMAIL);
        _ipScore = 0;
        _domainScore = -1;
        _isQuarantined = false;
    }

    public DomainType getDomainType() {
        return _domainType;
    }

    public boolean isQuarantined() {
        return _isQuarantined;
    }

    public List<Blacklist> getBlacklist() {
        return _blacklist;
    }

    public long getDomainScore() {
        return _domainScore;
    }

    public long getIpScore() {
        return _ipScore;
    }

    public List<String> getMxRecords() {
        return _mxRecords;
    }

    @Override
    public String toString() {
        return String.format("Domain Type: %s; Ip Score: $s; Domain Score: %s; isQuarantined: %s",
                _domainType,
                _ipScore,
                _domainScore,
                _isQuarantined);
    }

    public enum DomainType {
        BAD_DOMAIN,
        GOOD_DOMAIN,
        UNKNOWN
    }

    public enum Blacklist {
        IVOLO_DED,
        FREEMAIL,
        DEA
    }
}
