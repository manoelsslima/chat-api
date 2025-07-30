package br.eti.manoel.chat.controller;

import br.eti.manoel.chat.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private OpenAIService openAIService;

    @PostMapping
    public ResponseEntity<?> chat(@RequestBody Map<String, String> body) {
        String mensagem = body.get("message");
        if (mensagem == null || mensagem.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Mensagem é obrigatória");
        }

        try {
            String resposta = openAIService.gerarResposta(mensagem);
            return ResponseEntity.ok(Map.of("response", resposta));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
