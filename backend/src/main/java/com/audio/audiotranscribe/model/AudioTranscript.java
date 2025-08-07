package com.audio.audiotranscribe.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transcripts")
public class AudioTranscript {

    @Id
    private String id;

    private String filename;
    private String transcript;
}
