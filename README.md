# Word App  
A simple non-Jetpack Compose, MVVM architecture based Android application for storing and organizing any vocabulary. You can save a word together with its meaning and optional synonyms or any extra details.
The app works fully offline using Room Database and includes search, sorting, and progress tracking features.

## Features

### Add & Manage Words
Each word entry includes:
- Word (title)  
- Meaning / definition  
- Synonyms (optional)  
- Extra details (optional notes)

You can:
- Add new words  
- Edit words  
- Delete words

---

### Track Learning Progress
Two tabs provide a simple workflow:

- **New Words** — Words you're still learning  
- **Completed Words** — Words you've mastered  

You can freely move words between these lists.

---

### Search & Sort
- Strict keyword search  
- Sort by:
  - Title (A → Z / Z → A)
  - Date added (Newest → Oldest / Oldest → Newest)

 ---

### Word Details Screen
Shows the full meaning, synonyms, and extra notes for a selected word.

Actions available:
- Mark as completed  
- Move back to new  
- Edit word  
- Delete word
  

## Live Demo — Download APK
You can try the app instantly by **[downloading the latest APK](https://github.com/keshster98/word-app/releases/tag/v1.1.1)**. This APK installs on any Android device without additional setup.

## Running the Project Locally

1. Clone the repository using the command below in Android Studio
```bash
git clone https://github.com/<your-username>/word-app.git
```
2. After gradle has finished building, run it using the built-in emulator or by connecting your Android Phone to your PC.
