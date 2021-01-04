package de.telran.domain_searcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// automatically creates one object
@Component
public class ConsoleListener implements ApplicationRunner {

    DomainSearcher domainSearcher;

    public ConsoleListener(/*Automatically injects an object of DomainSearcher*/@Autowired DomainSearcher domainSearcher) {
        this.domainSearcher = domainSearcher;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Type a domain name you want to check for availability. Type \"exit\" to stop the application.");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String line;
            while ((line = br.readLine()) != null && !line.equals("exit")) {
                List<String> availableDomains = domainSearcher.getAvailableDomains(line);
                if (availableDomains.isEmpty())
                    System.out.println("No available domains found!");
                else
                    availableDomains.forEach(System.out::println);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
