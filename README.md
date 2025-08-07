# 🎧 Audio Transcribe App — Full Stack Project (Spring Boot + React + MongoDB + OpenAI Whisper API)


Hi! This is a personal full-stack project I built as a **Java + Spring Boot developer**.

The main idea of this project is very simple:  
👉 A user can **upload an audio file** and get its **transcription (converted text)** using **OpenAI's Whisper model**.

This project helped me connect the **backend** (Spring Boot + MongoDB) with the **frontend** (React), and also learn how to use **third-party APIs securely** (like Whisper).


---


## 🧑‍💻 Why I Built This Project

I'm learning full stack development and wanted to make something practical, not just basic CRUD or to-do apps.  
So I thought, what if I make a simple tool where anyone can upload an audio and get the text from it?

It was a good learning experience in:
- Creating REST APIs in Spring Boot
- Using MongoDB Atlas for cloud database
- Handling file uploads
- Using JWT token for login/logout security
- Working with React frontend and connecting it to backend
- Integrating OpenAI Whisper API

---

## 🔧 Tech Stack I Used

| Layer         | Tech/Tool Used                      |
|---------------|-------------------------------------|
| Backend       | Java + Spring Boot (Maven project)  |
| Frontend      | React.js + HTML + CSS + JS          |
| Database      | MongoDB Atlas (cloud DB)            |
| Auth/Security | JWT, BCryptPasswordEncoder          |
| File Upload   | MultipartFile in Spring Boot        |
| AI Model      | Whisper API from OpenAI             |
| Others        | dotenv for secret management        |

---

## 🌐 Project Features (From a Java Developer POV)

- 🧑‍💼 User Signup & Login (JWT-based)  
- 🔐 Passwords stored securely using BCrypt  
- 🎧 Upload any audio file (MP3, WAV, etc.)  
- 📄 Get real-time transcription using Whisper API  
- 🗃️ User data + transcripts stored in MongoDB  
- 💻 Clean UI using React and Tailwind  
- 📦 API endpoints built using Spring Boot REST

---

## 📁 Project Structure (Overview)
audio-transcribe/
├── backend/ // Spring Boot project
│ ├── src/main/java/com/example/
│ ├── application.properties → now uses .env!
├── frontend/ // React project
│ ├── src/components/
│ ├── FileUpload.jsx, Login.jsx etc.


---

 How to Run This Project (Step-by-Step)

📌 1. Clone the Repo

git clone https://github.com/your-username/audio-transcribe.git

cd audio-transcribe

2. Backend Setup (Spring Boot)

cd backend

---

🧠Step A: Set up .env file (for secrets)

Create a new .env file in the backend/ folder with the following keys:

MONGODB_URI=your-mongodb-uri-here

OPENAI_API_KEY=your-openai-key-here

✅ This .env file is already ignored in GitHub (through .gitignore) — so your keys will stay secret.


---

🌐 Step B: Get Your MongoDB Atlas URI

If you don’t have a MongoDB cluster:

1. Go to https://www.mongodb.com/cloud/atlas

2. Sign up → Create free shared cluster

3. Create a database and collection

4. Under Database > Connect > Drivers, copy the MongoDB URI

5. Paste that in .env as your MONGODB_URI

Example:

MONGODB_URI=mongodb+srv://your-username:your-password@cluster.mongodb.net/dbname?retryWrites=true&w=majority

---

🤖 Step C: Get Your OpenAI Whisper API Key

To use transcription:

1. Go to https://platform.openai.com/account/api-keys

2. Log in / Sign up and click “Create API Key”

3. Copy the key and paste it in .env:

OPENAI_API_KEY=sk-XXXXXXXXXXXXXXXXXXXX

---

:🧾 Note: Using Whisper transcription cost me around $5 (approx ₹520) for multiple test files.

It’s pay-as-you-go — so OpenAI will charge based on how many audio minutes you process.
You can set usage limits in your Billing Settings.

---

⚙️ Step D: Link .env to your Spring Boot

Make sure your application.properties has:

1.spring.data.mongodb.uri=${MONGODB_URI}

2.openai.api.key=${OPENAI_API_KEY}

✅ Done! Now run your backend server:

./mvnw spring-boot:run

It will start on:

http://localhost:8080

---


🎨 3. Frontend Setup (React)

cd ../frontend

npm install

npm run dev

It will run on http://localhost:5173

🎨 Note: I have added some background images in the public/ folder.

You can change or replace the background image easily, this was done to keep it simple and customizable.

---

## 📸 Project Screenshots

### 🖥️ Backend Terminal
![Backend Terminal](screenshots/BackendTerminal.png)

### 🖥️ Frontend Terminal
![Frontend Terminal](screenshots/FrontendTerminal.png)

### 🏠 Home Page
![Home Page](screenshots/HomePage.png)

### 🔐 Login Page
![Login Page](screenshots/LoginPage.png)

### 📝 Signup Page
![Signup Page](screenshots/SignupPage.png)

### 📤 Upload Page
![Upload Page](screenshots/UploadPage.png)

### 📁 Upload Screenshot 
![Upload](screenshots/Upload.png)

### 🧾 Transcription Result
![Result](screenshots/Result.png)


 ---


🔒 Security (My Approach)

-I used .env file to store Mongo URI and OpenAI API key

-My .env file is already added to .gitignore — so no one can access my credentials from GitHub

-For passwords, I used BCryptPasswordEncoder to store only the hashed values

-All APIs are protected using JWT




---


📚 What I Learned from This Project

This is my first full-stack app combining:

-File uploads

-REST APIs

-JWT Auth

-MongoDB Atlas

-Real API (Whisper) integration

-and most importantly — connecting frontend and backend in one complete project!

---


🧑‍💼 About Me

I'm a Java backend learner who recently started diving into Spring Boot, React, and MongoDB.

This project was built with a lot of trial and error, Googling, debugging, and learning — and I’m proud of it 🙂.

Feel free to check it out, run it yourself, or even improve on top of it!

---

📜 License
This project is open for learning purpose only. Not for production/commercial use.


