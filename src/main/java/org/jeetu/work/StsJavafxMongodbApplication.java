package org.jeetu.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SpringBootApplication
public class StsJavafxMongodbApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;

	public void init() throws Exception {
		this.springContext = SpringApplication.run(StsJavafxMongodbApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		this.rootNode = fxmlLoader.load();
	}

	public void stop() throws Exception {
		springContext.close();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@SuppressWarnings("exports")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("My Todo App");
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(this.rootNode, 800, 600);
		primaryStage.setScene(scene);
		Image image = new Image("/image/todo.png");
		primaryStage.getIcons().add(image);
		primaryStage.show();
	}

}
