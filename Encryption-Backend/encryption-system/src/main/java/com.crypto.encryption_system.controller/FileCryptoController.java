package com.crypto.encryption_system.controller;

import com.crypto.encryption_system.util.AESUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
public class FileCryptoController {

    @PostMapping("/encrypt")
    public ResponseEntity<byte[]> encryptFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("method") String method) {
        try {
            byte[] fileBytes = file.getBytes();
            byte[] result;

            if (method.equalsIgnoreCase("base64")) {
                result = Base64.getEncoder().encode(fileBytes);
            } else if (method.equalsIgnoreCase("aes")) {
                String encrypted = AESUtil.encrypt(new String(fileBytes));
                result = encrypted.getBytes();
            } else {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=encrypted_" + file.getOriginalFilename())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<byte[]> decryptFile(@RequestParam("file") MultipartFile file,
                                              @RequestParam("method") String method) {
        try {
            byte[] fileBytes = file.getBytes();
            byte[] result;

            if (method.equalsIgnoreCase("base64")) {
                result = Base64.getDecoder().decode(fileBytes);
            } else if (method.equalsIgnoreCase("aes")) {
                String decrypted = AESUtil.decrypt(new String(fileBytes));
                result = decrypted.getBytes();
            } else {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=decrypted_" + file.getOriginalFilename())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(result);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
