package com.audio.audiotranscribe.services;

import com.audio.audiotranscribe.model.AudioTranscript;
import com.audio.audiotranscribe.repository.AudioRepository;
import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.audio.TranscriptionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AudioServices {

    private final AudioRepository audioRepository;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    public AudioTranscript transcribeAndSave(MultipartFile file) {
        try {
            // ✅ Save the uploaded audio file temporarily
            File tempFile = File.createTempFile("audio", file.getOriginalFilename());
            file.transferTo(tempFile);

            // ✅ Create OpenAI Whisper transcription request
            OpenAiService service = new OpenAiService(openAiApiKey);
            CreateTranscriptionRequest request = CreateTranscriptionRequest.builder()
                    .model("whisper-1")
                    .responseFormat("json")
                    .build();

            // ✅ Perform transcription using Whisper
            TranscriptionResult result = service.createTranscription(request, tempFile);

            // ✅ Delete temp file after use
            tempFile.delete();

            // ✅ Save result to MongoDB
            AudioTranscript audioTranscript = new AudioTranscript();
            audioTranscript.setId(UUID.randomUUID().toString());
            audioTranscript.setFilename(file.getOriginalFilename());
            audioTranscript.setTranscript(result.getText());

            return audioRepository.save(audioTranscript);

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to save temp file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Transcription failed: " + e.getMessage());
        }
    }

    public AudioTranscript getAudioById(String id) {
        return audioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transcript not found for ID: " + id));
    }

    public List<AudioTranscript> getAllTranscripts() {
        return audioRepository.findAll();
    }

    public boolean deleteTranscriptById(String id) {
        if (audioRepository.existsById(id)) {
            audioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
