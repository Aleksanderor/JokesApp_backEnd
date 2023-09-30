package com.example.jokes.service.externalApiDbService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class DadJokeDbService {

    private static final String url = "https://dad-jokes.p.rapidapi.com/random/joke";
    private static final String xRapidApiKey = "34046c41dfmsha85a4ea73447649p15d2b5jsn833ea7324a8c";
    private static final String getxRapidApiHost = "dad-jokes.p.rapidapi.com";

    @Autowired
    private RestTemplate restTemplate;

    public Object getRandomJoke() {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", xRapidApiKey);
            headers.set("X-RapidAPI-Host", getxRapidApiHost);


            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,new HttpEntity<>(headers),String.class);

            log.info("Output formr rapidApi:{}",response.getBody());

            return response.getBody();
        } catch (Exception e) {
            log.error("Something went wrong while loadind dad joke from api :(", e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exeption while calling ednpoint of RapidApi dadJOke",
                    e
            );
        }

    }
}
