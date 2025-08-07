package com.audio.audiotranscribe.repository;

import com.audio.audiotranscribe.model.AudioTranscript;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AudioRepository extends MongoRepository<AudioTranscript, String> {
}
