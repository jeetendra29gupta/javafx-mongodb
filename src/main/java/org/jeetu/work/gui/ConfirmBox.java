package org.jeetu.work.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	private static boolean answer = false;

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		Label label_message = new Label();
		label_message.setText(message);
		label_message.setStyle("-fx-font-size: 18.0px; -fx-padding: 5.0px; -fx-font-weight: bold;");

		Button button_yes = new Button("YES");
		button_yes.setOnAction(event -> {
			answer = true;
			window.close();
		});
		Button button_no = new Button("NO");
		button_no.setOnAction(event -> {
			answer = false;
			window.close();
		});
		HBox hbox = new HBox(20);
		hbox.getChildren().addAll(button_yes, button_no);
		hbox.setAlignment(Pos.BASELINE_CENTER);

		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(label_message, hbox);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox, 400, 150);
		window.setScene(scene);
		window.showAndWait();

		return answer;
	}
}
