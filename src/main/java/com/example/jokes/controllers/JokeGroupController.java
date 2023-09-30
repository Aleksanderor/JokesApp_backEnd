package com.example.jokes.controllers;

import com.example.jokes.domain.JokeGroup;
import com.example.jokes.domain.JokeGroupDto;
import com.example.jokes.exception.GroupNotFoundException;
import com.example.jokes.mapper.JokeGroupMapper;
import com.example.jokes.service.JokeGroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/jokeGroups")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JokeGroupController {

    private final JokeGroupMapper jokeGroupMapper;
    private final JokeGroupDbService jokeGroupDbService;

    @Autowired
    public JokeGroupController(JokeGroupDbService jokeGroupDbService, JokeGroupMapper jokeGroupMapper) {
        this.jokeGroupDbService = jokeGroupDbService;
        this.jokeGroupMapper = jokeGroupMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addGroup(@RequestBody JokeGroupDto jokeGroupDto) {
        jokeGroupDbService.addJokeGroup(jokeGroupMapper.maptoJokeGroup(jokeGroupDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JokeGroupDto>> getGroups() {
        List<JokeGroupDto> jokeGroups = jokeGroupMapper.mapToJokeGroupDtoList(jokeGroupDbService.getAllJokeGroups());
        return ResponseEntity.ok(jokeGroups);
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<JokeGroupDto> getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return jokeGroupDbService.getJokeGroupById(groupId)
                .map(jokeGroup -> ResponseEntity.ok(jokeGroupMapper.mapToJokeGroupDto(jokeGroup)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<JokeGroupDto> updateGroup(@RequestBody JokeGroupDto jokeGroupDto)throws GroupNotFoundException {
        JokeGroup jokeGroup = jokeGroupMapper.maptoJokeGroup(jokeGroupDto);
        JokeGroup savedJokeGroup = jokeGroupDbService.addJokeGroup(jokeGroup);

        return ResponseEntity.ok(jokeGroupMapper.mapToJokeGroupDto(savedJokeGroup));
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        jokeGroupDbService.deleteJokeGroupById(groupId);
        return ResponseEntity.ok().build();
    }
}
