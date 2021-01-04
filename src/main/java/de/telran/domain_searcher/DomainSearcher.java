package de.telran.domain_searcher;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DomainSearcher {

    private final List<String> zones;

    public DomainSearcher(
            @Value("#{'de.telran.domain_searcher_with_spring.zones'.split(',')}")
                    List<String> zones) {
        this.zones = new ArrayList<>(zones);
    }

    public List<String> getAvailableDomains(String line) {
        return zones.stream()
                .map(zone -> line + "." + zone)
                .filter(this::isAvailable)
                .collect(Collectors.toList());
    }

    private boolean isAvailable(String domainName) {
        String urlString = "http://" + domainName;
        URL url;
        try {
            url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            //  url.openStream();
        } catch (UnknownHostException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}