package com.crypto.encryption_system.controller;

import com.crypto.encryption_system.model.CryptoRequest;
import com.crypto.encryption_system.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/crypto")
@CrossOrigin(origins = "*")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @PostMapping("/encrypt")
    public Map<String, String> encrypt(@RequestBody CryptoRequest request) {
        String result = cryptoService.encrypt(request.getMessage(), request.getMethod());
        return Map.of("result", result);
    }

    @PostMapping("/decrypt")
    public Map<String, String> decrypt(@RequestBody CryptoRequest request) {
        String result = cryptoService.decrypt(request.getMessage(), request.getMethod());
        return Map.of("result", result);
    }
}
