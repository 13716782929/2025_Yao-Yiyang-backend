package com.example.coinapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    @PostMapping("/minimum")
    public ResponseEntity<List<Double>> calculateMinimumCoins(
            @RequestParam double targetAmount,
            @RequestBody List<Double> denominations) {

        if (targetAmount < 0 || targetAmount > 10000) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        if (denominations.isEmpty() || !denominations.stream().allMatch(denom ->
                List.of(0.01, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 50.0, 100.0, 1000.0).contains(denom))) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<Double> result = new ArrayList<>();
        denominations.sort(Collections.reverseOrder());

        for (double denom : denominations) {
            while (targetAmount >= denom) {
                targetAmount = Math.round((targetAmount - denom) * 100.0) / 100.0;
                result.add(denom);
            }
        }

        return ResponseEntity.ok(result);
    }
}
