import React, { useState } from 'react';

function FileUpload() {
  const [file, setFile] = useState(null);
  const [transcription, setTranscription] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleUpload = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem('token');

    if (!token) {
      setMessage('❌ You need to login first before uploading a file.');
      return;
    }

    if (!file) {
      setMessage('⚠️ Please select an audio file first.');
      return;
    }

    if (!file.type.startsWith('audio/')) {
      setMessage('🚫 Only audio files are allowed!');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);

    try {
      setLoading(true);
      setMessage('');

      const response = await fetch('http://localhost:8080/api/audio/transcribe', {
        method: 'POST',
        body: formData,
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) {
        throw new Error('Server responded with an error');
      }

      const result = await response.json();
      if (result.transcript) {
        setTranscription(result.transcript);
        setMessage('✅ Transcription successful!');
      } else {
        setTranscription('');
        setMessage('⚠️ No transcript received.');
      }

    } catch (error) {
      console.error('Error uploading or transcribing file:', error);
      setMessage('❌ Something went wrong! Check backend and console.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>🎙️ Audio Transcription App</h2>

      <form onSubmit={handleUpload} style={styles.card}>
        <input
          type="file"
          accept="audio/*"
          onChange={handleFileChange}
          style={styles.fileInput}
        />
        <button type="submit" style={styles.button} disabled={loading}>
          {loading ? 'Transcribing...' : 'Upload and Transcribe'}
        </button>

        {message && <p style={styles.message}>{message}</p>}
      </form>

      {transcription && (
        <div style={styles.transcriptionBox}>
          <h3 style={styles.subheading}>📄 Transcription:</h3>
          <p style={styles.transcriptText}>{transcription}</p>
        </div>
      )}
    </div>
  );
}

const styles = {
  container: {
    textAlign: 'center',
    padding: '2rem',
    fontFamily: 'Segoe UI, sans-serif',
    backgroundImage: 'url("bg-img6.jpg")',
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    minHeight: '100vh',
    color: '#f0f0f0',
  },
  heading: {
    fontSize: '2rem',
    marginBottom: '1.5rem',
    color: '#ffffff',
  },
  card: {
    backgroundColor: 'rgba(0, 0, 0, 0.6)',
    padding: '2rem',
    borderRadius: '16px',
    maxWidth: '420px',
    margin: '0 auto',
    width: '90%',
  },
  fileInput: {
    margin: '1rem 0',
    padding: '0.5rem',
    borderRadius: '8px',
    border: 'none',
    width: '100%',
    fontSize: '1rem',
  },
  button: {
    padding: '0.6rem 1.5rem',
    backgroundColor: '#1f7aec',
    color: '#fff',
    border: 'none',
    borderRadius: '8px',
    cursor: 'pointer',
    width: '100%',
    fontSize: '1rem',
  },
  message: {
    marginTop: '1rem',
    fontWeight: '500',
    fontSize: '1rem',
  },
  transcriptionBox: {
    marginTop: '2rem',
    padding: '1.5rem',
    backgroundColor: 'rgba(255, 255, 255, 0.1)',
    borderRadius: '12px',
    width: '90%',
    maxWidth: '600px',
    marginLeft: 'auto',
    marginRight: 'auto',
    textAlign: 'left',
  },
  subheading: {
    marginBottom: '0.5rem',
    fontSize: '1.25rem',
    color: '#fff',
  },
  transcriptText: {
    whiteSpace: 'pre-wrap',
    color: '#e0e0e0',
    lineHeight: '1.6',
  },
};

export default FileUpload;
