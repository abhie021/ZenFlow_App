package com.abhimurkute.zenflow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class WebsiteBlocker implements Blockable {
    private static final String HOSTS_REDIRECT_IP = "127.0.0.1";
    private static final Path HOSTS_PATH = getHostsPath();
    private final String url;

    public WebsiteBlocker(String url) {
        this.url = url;
    }

    @Override
    public String getIdentifier() {
        return url;
    }

    @Override
    public void block() throws IOException {
        String blockLine = HOSTS_REDIRECT_IP + " " + url + "\n";
        Files.write(HOSTS_PATH, blockLine.getBytes(), StandardOpenOption.APPEND);
    }

    @Override
    public void unblock() throws IOException {
        List<String> lines = Files.readAllLines(HOSTS_PATH);
        List<String> updatedLines = lines.stream()
                .filter(line -> !line.contains(url))
                .collect(Collectors.toList());
        Files.write(HOSTS_PATH, updatedLines);
    }

    private static Path getHostsPath() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return Paths.get(System.getenv("SystemRoot"), "/System32/drivers/etc/hosts");
        } else { // For macOS and Linux
            return Paths.get("/etc/hosts");
        }
    }
}