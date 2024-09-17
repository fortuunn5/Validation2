package com.example.Validation.Controller;

import com.example.Validation.Dto.ProfanityDto;
import com.example.Validation.Dto.ValidationResponse;
import com.example.Validation.Mapper.ProfanityMapper;
import com.example.Validation.Service.ProfanityService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/profanities")
public class ProfanityController {

    @Qualifier("profanityServiceMongoDB")
    @Autowired
    private ProfanityService service;

    @PostMapping
    public ResponseEntity<ProfanityDto> createProfanity(@RequestBody ProfanityDto newProfanity) {
        //TODO: развернуть везде
        return new ResponseEntity<>(ProfanityMapper.map(service.createProfanity(ProfanityMapper.map(newProfanity))), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfanityDto> getProfanity(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(ProfanityMapper.map(service.getProfanity(id)));
    }

    @GetMapping
    public ResponseEntity<ProfanityDto> getProfanityByWord(@PathParam("word") String word) {
        return ResponseEntity.ok(ProfanityMapper.map(service.getProfanityByWord(word)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfanityDto>> getAllProfanities() {
        return ResponseEntity.ok(ProfanityMapper.mapToDtoSet(new HashSet<>(service.getAllProfanities().stream().toList())).stream().toList());
    }

    @PutMapping
    public ResponseEntity<ProfanityDto> updateProfanity(@RequestBody ProfanityDto profanity) {
        return ResponseEntity.ok(ProfanityMapper.map(service.updateProfanity(ProfanityMapper.map(profanity))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfanity(@PathVariable(name = "id") Long id) {
        service.deleteProfanity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProfanityByWord(@PathParam("word") String word) {
        service.deleteProfanityByWord(word);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/positions")
    public ResponseEntity<ValidationResponse> getPositions(@RequestBody(required = false) String message) {
        ValidationResponse validationResponse = new ValidationResponse(service.getPositionsOfProfanities(message));
        return new ResponseEntity<>(validationResponse, HttpStatus.OK);
    }
}
