# 🎓 Multiplayer Quiz App

[![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk&logoColor=white)](https://www.java.com/)
[![Architecture](https://img.shields.io/badge/Architecture-Client%2FServer-007396?logo=java&logoColor=white)](#)
[![UI](https://img.shields.io/badge/UI-Java_Swing-3A75B0?logo=java&logoColor=white)](#)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A classic Client-Server multiplayer Quiz Application built entirely in Java. This project demonstrates core computer science concepts such as **Socket Programming**, **Multithreading**, and **Object Serialization**.

---

## ✨ Features

- **Client-Server Architecture**: Custom TCP socket implementation.
- **Multithreaded Server**: Capable of handling multiple clients simultaneously via `ClientHandler`.
- **Graphical User Interface (GUI)**: Built with Java Swing for an intuitive user experience.
- **Object-Oriented Design**: Clean separation of `Client`, `Server`, and `Shared` (Request/Response) models.
- **Protocol**: Custom request/response serialization over the network.

---

## 🛠️ Tech Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java |
| **Networking** | `java.net.Socket`, `ServerSocket` |
| **Concurrency** | `java.lang.Thread` |
| **User Interface** | Java Swing / AWT |
| **I/O** | `ObjectInputStream`, `ObjectOutputStream` |

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Project Structure

```text
src/
├── client/          # Client-side logic and Java Swing UI
│   ├── Client.java
│   └── QuizAppUI.java
├── server/          # Server-side logic and multithreading
│   └── net/
│       ├── Main.java
│       ├── Server.java
│       └── ClientHandler.java
└── shared/          # Shared communication models
    ├── Request.java
    └── Response.java
```

### How to Run

Since this is a client-server application, you must start the server first, then connect one or more clients.

**1. Start the Server:**
Compile and run the server application.
```bash
javac src/server/net/*.java src/shared/*.java
java -cp src server.net.Main
```

**2. Start the Client:**
Compile and run the client UI in a new terminal window.
```bash
javac src/client/*.java src/shared/*.java
java -cp src client.QuizAppUI
```

---

## 👤 Author

**Jean Yves Nkwane**  
Software Engineering Student

- 🐙 GitHub: [@Nejy190501](https://github.com/Nejy190501)

---

<p align="center">Made with ☕ in Java</p>
