package com.airtimeadvance;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AirtimeController {

    private final Map<String, Double> userBalances = new HashMap<>();

    @PostMapping("/advance")
    public String requestAdvance(@RequestBody AirtimeRequest request) {
        if (!request.getNumber().matches("\\d{10}")) {
            return "ensInvalid phone number format.";
        }

        if (request.getAmount() <= 0 || request.getAmount() > 50) {
            return "Invalid amount. Please enter between R1 and R50.";
        }

        userBalances.put(
            request.getNumber(),
            userBalances.getOrDefault(request.getNumber(), 0.0) + request.getAmount()
        );

        return "✅ Airtime advance of R" + request.getAmount() + " credited to " + request.getNumber();
    }

    @GetMapping("/balance/{number}")
    public String checkBalance(@PathVariable String number) {
        if (!number.matches("\\d{10}")) {
            return "❌ Invalid phone number format.";
        }

        double balance = userBalances.getOrDefault(number, 0.0);
        return "📱 Balance for " + number + " is R" + balance;
    }
}