package com.abhimurkute.zenflow;

import javafx.concurrent.Task;
import java.util.List;

public class FocusTimerTask extends Task<Void> {
    private final int durationMinutes;
    private final List<Blockable> blockables;

    public FocusTimerTask(int durationMinutes, List<Blockable> blockables) {
        this.durationMinutes = durationMinutes;
        this.blockables = blockables;
    }

    @Override
    protected Void call() throws Exception {
        // Block all items first
        for (Blockable item : blockables) {
            item.block();
        }

        long durationSeconds = durationMinutes * 60L;
        for (int i = 0; i <= durationSeconds; i++) {
            if (isCancelled()) break;

            long remainingSeconds = durationSeconds - i;
            long mins = remainingSeconds / 60;
            long secs = remainingSeconds % 60;
            updateMessage(String.format("Time Remaining: %02d:%02d", mins, secs));
            updateProgress(i, durationSeconds);
            Thread.sleep(1000);
        }
        return null;
    }

    // This method runs on the UI thread when the task is finished
    private void cleanup() {
        updateMessage("Session finished. Sites unblocked.");
        try {
            for (Blockable item : blockables) {
                item.unblock();
            }
        } catch (Exception e) {
            updateMessage("Error unblocking sites. Manual edit may be needed.");
            e.printStackTrace();
        }
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        cleanup();
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        cleanup();
    }
}