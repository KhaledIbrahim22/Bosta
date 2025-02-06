# Bosta Cities & Districts App

## 📌 Overview
This is an Android application built using **Kotlin**, implementing **MVVM architecture** and **Clean Architecture principles**. The app fetches a list of cities and their respective districts from the **Bosta API** and displays them in a structured list.

## 🚀 Features
- Fetches cities and districts using **Retrofit2**.
- Uses **Jetpack Compose** for UI rendering.
- **Dagger-Hilt** for dependency injection.
- **Coroutines & Flow** for asynchronous operations.
- **State Management** to handle loading, success, and error states.
- **Optimized performance** with background processing.

## 📂 Project Structure
```
com.task.bosta
├── app                 # App Layer
├── data                # Data Layer (API, Repository, Models)
│   ├── api             # Retrofit API interface
│   ├── model           # Data models
│   ├── repository      # Repository implementation
├── domain              # Domain Layer (Business Logic)
│   ├── repository      # Repository interface
│   ├── usecase         # Use Cases (Business Logic)
├── presentation        # Presentation Layer (UI & ViewModel)
│   ├── screen          # Composable Screens
│   ├── viewModel       # ViewModel for managing UI state
├── di                  # Dependency Injection (Dagger-Hilt)
├── utils               # Utility classes (Network, State Handling)
└── MainActivity.kt     # Entry point of the app
```

## 🛠️ Technologies & Libraries
- **Kotlin** - Main programming language
- **Jetpack Compose** - UI framework
- **MVVM Architecture** - Separation of concerns
- **Retrofit2** - Networking library
- **Dagger-Hilt** - Dependency Injection
- **Coroutines & Flow** - Asynchronous operations
- **LiveData** - UI state management

## ⚙️ Installation & Setup
### Prerequisites
- Android Studio **Giraffe or newer**
- JDK 11+
- Internet connection

### Steps to Run
1. **Clone the repository**:
   ```sh
   git clone https://github.com/KhaledIbrahim22/Bosta.git
   cd bosta-app
   ```
2. **Open in Android Studio**.
3. **Sync Gradle**.
4. **Run the app on an emulator or physical device**.

## ✨ Author
Developed by **[Your Name]** - [LinkedIn Profile](https://github.com/KhaledIbrahim22)

