package com.vet.pets.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vet.pets.dto.RaceDTO;
import com.vet.pets.entities.Customer;
import com.vet.pets.entities.Races;
import com.vet.pets.service.RaceServices;

@RestController
@RequestMapping("/api/v1/races")
public class RaceController {
    
    @Autowired
    private RaceServices raceServices;

    @PostMapping
    public ResponseEntity<ApiResponse> createRace(@RequestBody RaceDTO dto) {
        Optional<Races> race = raceServices.findRaceByName(dto.name());
        if (race.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Raça já cadastrada."));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<Races>("Created", raceServices.createRace(dto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Races>>> listRace() {
        List<Races> listRace = raceServices.listRace();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<List<Races>>("Ok", listRace));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Races>> findRace(@PathVariable Long id) {
        Races race = raceServices.findRace(id);
        if (race == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<Races>("Ok", race));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRace(@PathVariable Long id) {
        Races race = raceServices.findRace(id);
        if (race == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        raceServices.deleteRace(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateRace(@PathVariable Long id, @RequestBody RaceDTO dto) {
        Races race = raceServices.findRace(id);
        if (race == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Raça não encontrada."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<Customer>("Updated", raceServices.updateRaceById(id, dto)));
    }

}