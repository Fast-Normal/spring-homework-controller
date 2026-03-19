package com.springboot.coffee;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    //---------------- 여기서 부터 아래에 코드를 구현하세요! -------------------//
    // 1. 커피 정보 수정을 위한 핸들러 메서드 구현
    @PatchMapping("/{coffeeId}")
    public ResponseEntity<Map<String, Object>> patchCoffee(
                @PathVariable long coffeeId,
                @RequestParam String korName,
                @RequestParam int price
                ) {
        Map<String, Object> coffee = coffees.get(coffeeId);
        coffee.put("korName", korName);
        coffee.put("price", price);

        return new ResponseEntity<>(coffee, HttpStatus.OK);

        }
    // 2. 커피 정보 삭제를 위한 핸들러 서드 구현
    @DeleteMapping("/{coffeeId}")
    public ResponseEntity<Map<String, Object>> deleteCoffee(
            @PathVariable long coffeeId
    ) {
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}


