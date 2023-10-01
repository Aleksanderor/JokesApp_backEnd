package com.example.jokes.controllers.externalApiControllers;

import com.example.jokes.service.externalApiDbService.MemeGeneratorDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/memes")
@RequiredArgsConstructor
public class MemeApiController {

    public final MemeGeneratorDbService memeGeneratorDbService;
    @GetMapping("/getRandomMeme")
    public ResponseEntity<?>getMeme(){
        return ResponseEntity.ok(memeGeneratorDbService.getMeme());

    }

}
