# ZenFlow 🧘‍♂️

ZenFlow is a simple, cross-platform desktop application designed to help you focus by temporarily blocking distracting websites. Built with JavaFX, it provides a minimal and professional interface to manage your focus sessions.

## Features

* **Block Distracting Websites:** Add any website (e.g., `youtube.com`, `facebook.com`) to your blocklist.
* **Timed Focus Sessions:** Set a timer for your focus session. Websites are automatically unblocked when the timer finishes.
* **Minimalist UI:** A clean, professional, and easy-to-use interface.
* **Cross-Platform:** Works on Windows, macOS, and Linux.

## How It Works

ZenFlow works by temporarily adding entries to your system's **hosts file**, which redirects the distracting websites to your local machine (127.0.0.1). Because it modifies a system file, **the application must be run with administrator privileges**.

## Screenshots

![Image](https://github.com/user-attachments/assets/23f2d697-289e-4474-a035-bc23f4679a5d)

## How to Run

### Prerequisites

* Java Development Kit (JDK) 17 or higher
* Apache Maven

### Steps

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/your-username/zenflow.git](https://github.com/your-username/zenflow.git)
    ```
2.  **Navigate to the project directory:**
    ```sh
    cd zenflow
    ```
3.  **Run the application using Maven:**
    **Important:** You must run this command from a terminal with **administrator/root privileges**.
    ```sh
    mvn javafx:run
    ```

## Contributing

Contributions are welcome! Please see the [CONTRIBUTING.md](CONTRIBUTING.md) file for guidelines.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

