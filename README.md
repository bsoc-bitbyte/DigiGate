<h1 align="center">DigiGate</h1>

<p align="center">
  Smarter entry. No slips. No queues. Just tap & go.
</p>

---

## Project Overview

Tired of writing your name in that dusty old register every time you enter your hostel?  
Or being told, *"Show me your ID"* for the third time that day?

**DigiGate** is here to change the game.

We're building an Android app where:

- 📲 Students generate **QR passes** with their ID & purpose  
- 🔍 Guards **scan the QR**, verify identity, and auto-log the entry  
- 🛂 Admins get **real-time records** of who entered when — no chaos, no paper trails  

From the **main gate** to the **hostel**, and eventually the **library**, **SAC**, or any restricted campus zone — DigiGate makes access smooth and digital.

---

## Features

### 🎓 For Students
- Generate a **QR code** linked to your student profile  
- Enter your **purpose or destination** before generating  
- Access past **entry/exit logs** anytime  

### 🛡️ For Guards
- Scan QR using the **DigiGate Scanner**  
- Instantly view student **info, photo, and purpose**  
- **One-tap logging** — no manual writing  

### 🧑‍💼 For Admins
- View all **gate logs** in one dashboard  
- **Filter** by student, date, location, or gate  
- **Export logs** for compliance or review  

---

## Tech Stack

- **Language**: Kotlin  
- **UI**: Jetpack Compose  
- **Backend**: Firebase (Auth, Firestore, Storage)  

---

## Development Flow

### Phase 1 – UI First
- Build UI components using Jetpack Compose  
- Focus on **student flow** first  

### Phase 2 – Architecture & State
- ViewModels + StateFlow for smooth state management  
- Following **MVVM Design Pattern**  
- Dependency Injection via **Dagger Hilt**  
- Navigation using **Jetpack Navigation 3**  

### Phase 3 – Backend Integration
- Firebase Auth for **sign-in**  
- Firestore for **real-time logs**  
- **QR code generation + scanning**  

---

## Why DigiGate?

Because it's 2025, and **paper logbooks** still being a thing? That’s just sad.  

DigiGate makes gatekeeping (the good kind) smooth, smart, and scalable.

Once it’s running, **entry logs become one tap away**, **identity verification becomes instant**, and **nobody stands waiting in line** anymore.

---

## Getting Started

1. Fork this repo  
2. Clone it 🔽
   ```bash
   git clone https://github.com/bsoc-bitbyte/DigiGate.git
   ```
3. Open in Android Studio
4. Build & run
5. Start contributing 

---

## Contribution Guidelines

Before you dive into the code, make sure to check out the [CONTRIBUTING.md](./CONTRIBUTING.md).
It’s short, clear, and saves everyone time. Let’s keep things clean and collaborative!

---

## Design Preview (Coming Soon)
We’re sketching flows in Figma right now. Stay tuned! 👀

---
🚀 Built with ❤️ by Team DigiGate — unlocking smarter campus access.
