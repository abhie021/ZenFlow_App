package com.abhimurkute.zenflow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;
import java.util.stream.Collectors;

public class ZenFlowController {
    @FXML private TextField websiteInputField;
    @FXML private ListView<String> blocklistListView;
    @FXML private Spinner<Integer> durationSpinner;
    @FXML private Label timerLabel;
    @FXML private ProgressBar timerProgressBar;
    @FXML private Button toggleButton;

    private final ObservableList<String> blocklistItems = FXCollections.observableArrayList();
    private Task<Void> timerTask;

    @FXML
    public void initialize() {
        blocklistListView.setItems(blocklistItems);
        timerLabel.setText(""); // Start with an empty timer label
    }

    @FXML
    protected void onAddButtonClick() {
        String url = websiteInputField.getText().trim();
        if (!url.isEmpty() && !blocklistItems.contains(url)) {
            blocklistItems.add(url);
            websiteInputField.clear();
        }
    }

    @FXML
    protected void onRemoveButtonClick() {
        String selected = blocklistListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            blocklistItems.remove(selected);
        }
    }

    @FXML
    protected void onToggleButtonClick() {
        if (timerTask != null && timerTask.isRunning()) {
            timerTask.cancel();
        } else {
            startFocusSession();
        }
    }

    private void startFocusSession() {
        List<Blockable> blockables = blocklistItems.stream()
                .map(WebsiteBlocker::new)
                .collect(Collectors.toList());

        int duration = durationSpinner.getValue();
        timerTask = new FocusTimerTask(duration, blockables);

        timerLabel.textProperty().bind(timerTask.messageProperty());
        timerProgressBar.progressProperty().bind(timerTask.progressProperty());

        toggleButton.setText("Stop Session");
        toggleButton.getStyleClass().setAll("button", "stop-button");

        timerTask.setOnSucceeded(event -> resetUI("Session Finished"));
        timerTask.setOnCancelled(event -> resetUI("Session Stopped"));
        timerTask.setOnFailed(event -> resetUI("An Error Occurred"));

        new Thread(timerTask).start();
    }

    private void resetUI(String message) {
        timerLabel.textProperty().unbind();
        timerProgressBar.progressProperty().unbind();

        timerLabel.setText(message);
        timerProgressBar.setProgress(0);
        toggleButton.setText("Start Focus Session");
        toggleButton.getStyleClass().setAll("button", "start-button");
    }
}