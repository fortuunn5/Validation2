package com.example.Validation.Controller;

import com.example.Validation.Dto.ProfanityDto;
import com.example.Validation.Exception.EntityNotFoundException;
import com.example.Validation.Mapper.ProfanityMapper;
import com.example.Validation.Service.ProfanityService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profanities")
@RequiredArgsConstructor
public class ProfanityController {
    private final ProfanityService profanityService;

    @PostMapping
    public ResponseEntity<ProfanityDto> createProfanity(@RequestBody String word) {
        return new ResponseEntity<>(ProfanityMapper.map(profanityService.createProfanity(word)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfanityDto> getProfanity(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(ProfanityMapper.map(profanityService.getProfanity(id)));
    }

    @GetMapping
    public ResponseEntity<ProfanityDto> getProfanityByWord(@PathParam("word") String word) {
        return ResponseEntity.ok(ProfanityMapper.map(profanityService.getProfanityByWord(word)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfanityDto>> getAllProfanities() {
        return ResponseEntity.ok(ProfanityMapper.mapToDtoSet(new HashSet<>(profanityService.getAllProfanities().stream().toList())).stream().toList());
    }

    @PutMapping
    public ResponseEntity<ProfanityDto> updateProfanity(@RequestBody ProfanityDto profanity) {
        return ResponseEntity.ok(ProfanityMapper.map(profanityService.updateProfanity(ProfanityMapper.map(profanity))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProfanity(@PathVariable(name = "id") Long id) {
        profanityService.deleteProfanity(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity deleteProfanityByWord(@PathParam("word") String word) {
        profanityService.deleteProfanityByWord(word);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
