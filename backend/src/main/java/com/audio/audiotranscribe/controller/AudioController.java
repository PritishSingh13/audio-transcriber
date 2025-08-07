package com.audio.audiotranscribe.controller;

import com.audio.audiotranscribe.model.AudioTranscript;
import com.audio.audiotranscribe.services.AudioServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/audio")
@RequiredArgsConstructor
public class AudioController {

    private final AudioServices audioService;

    // ✅ Upload and simulate transcription
    @PostMapping("/transcribe")
    public ResponseEntity<AudioTranscript> transcribeAudio(@RequestParam("file") MultipartFile file) {
        AudioTranscript transcript = audioService.transcribeAndSave(file);
        return ResponseEntity.ok(transcript);
    }

    // ✅ Get a specific transcript by ID
    @GetMapping("/{id}")
    public ResponseEntity<AudioTranscript> getAudioById(@PathVariable String id) {
        AudioTranscript transcript = audioService.getAudioById(id);
        return ResponseEntity.ok(transcript);
    }

    // ✅ Get all transcripts
    @GetMapping("/all")
    public ResponseEntity<List<AudioTranscript>> getAllTranscripts() {
        return ResponseEntity.ok(audioService.getAllTranscripts());
    }

    // ✅ Delete transcript by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTranscript(@PathVariable String id) {
        boolean deleted = audioService.deleteTranscriptById(id);
        if (deleted) {
            return ResponseEntity.ok("Transcript deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Transcript not found.");
        }
    }
}
