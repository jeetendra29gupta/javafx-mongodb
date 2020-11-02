package org.jeetu.work.controller;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.jeetu.work.bean.Todo;
import org.jeetu.work.gui.ConfirmBox;
import org.jeetu.work.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

@Service
@SuppressWarnings({ "exports", "unchecked" })
public class MainController implements Initializable {

	@FXML
	public BorderPane borderPane_main;
	@FXML
	public Label label_dateTime;
	@FXML
	public TextField textField_item, textField_id, textField_search;
	@FXML
	public TableView<Todo> tableView_todoTable;

	@Autowired
	private TodoService todoService;

	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeDragable();
		setDateTime();
		showTodo();
	}

	private void makeDragable() {
		borderPane_main.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		borderPane_main.setOnMouseDragged(event -> {
			Window primaryStage = borderPane_main.getScene().getWindow();
			primaryStage.setX(event.getScreenX() - xOffset);
			primaryStage.setY(event.getScreenY() - yOffset);
		});
	}

	public void setDateTime() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					label_dateTime.setText(new Date() + "");
				});
			}
		}, 0, 1000);
	}

	@FXML
	public void closeWindow(ActionEvent event) {
		Window primaryStage = borderPane_main.getScene().getWindow();
		if (ConfirmBox.display("Confirm Box", "Are you sure you want to exit?")) {
			((Stage) primaryStage).close();
			System.exit(0);
		}
	}

	@FXML
	public void saveTodo(ActionEvent event) {
		if (textField_item.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Text field must be filled out.");
			alert.show();
		} else {
			Todo _todo = new Todo();
			_todo.setId(textField_id.getText());
			_todo.setTask(textField_item.getText());
			_todo.setCompleted(false);
			_todo.setAdded(new Date());
			todoService.saveTodo(_todo);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Todo item is saved.");
			alert.show();
			textField_item.clear();
			showTodo();
		}
	}

	public void showTodo() {

		TableColumn<Todo, String> id = new TableColumn<>("ID");
		id.setMinWidth(0);
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id.setVisible(false);

		TableColumn<Todo, String> task = new TableColumn<>("Task");
		task.setMinWidth(230);
		task.setCellValueFactory(new PropertyValueFactory<>("task"));
		task.setEditable(true);
		task.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Todo, Date> added = new TableColumn<>("Added At");
		added.setMinWidth(230);
		added.setCellValueFactory(new PropertyValueFactory<>("added"));

		TableColumn<Todo, Date> finished = new TableColumn<>("Completed At");
		finished.setMinWidth(230);
		finished.setCellValueFactory(new PropertyValueFactory<>("finished"));

		ObservableList<Todo> todos = FXCollections.observableArrayList();
		List<Todo> todoList = todoService.getAllTodoList();
		for (Todo todo : todoList) {
			todos.add(todo);
		}

		tableView_todoTable.refresh();
		tableView_todoTable.getColumns().clear();
		tableView_todoTable.getColumns().addAll(id, task, added, finished);
		tableView_todoTable.getItems().clear();
		tableView_todoTable.setItems(todos);

		// productTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}

	@FXML
	public void completedTodo() {
		ObservableList<Todo> selectedTodo = tableView_todoTable.getSelectionModel().getSelectedItems();
		if (!selectedTodo.isEmpty()) {
			Todo _todo = new Todo();
			_todo.setId(selectedTodo.get(0).getId());
			_todo.setTask(selectedTodo.get(0).getTask());
			_todo.setCompleted(true);
			_todo.setAdded(null);
			_todo.setFinished(new Date());
			todoService.saveTodo(_todo);
			showTodo();
		}
	}

	@FXML
	public void inCompleteTodo() {
		ObservableList<Todo> selectedTodo = tableView_todoTable.getSelectionModel().getSelectedItems();
		if (!selectedTodo.isEmpty()) {
			Todo _todo = new Todo();
			_todo.setId(selectedTodo.get(0).getId());
			_todo.setTask(selectedTodo.get(0).getTask());
			_todo.setCompleted(false);
			_todo.setAdded(new Date());
			_todo.setFinished(null);
			todoService.saveTodo(_todo);
			showTodo();
		}
	}

	@FXML
	public void updateTodo() {
		ObservableList<Todo> selectedTodo = tableView_todoTable.getSelectionModel().getSelectedItems();
		if (!selectedTodo.isEmpty()) {
			textField_id.setText(selectedTodo.get(0).getId());
			textField_item.setText(selectedTodo.get(0).getTask());
		}
	}

	@FXML
	public void deleteTodo() {
		ObservableList<Todo> selectedTodo = tableView_todoTable.getSelectionModel().getSelectedItems();
		if (!selectedTodo.isEmpty()) {
			todoService.deleteTodo(selectedTodo.get(0).getId());
			showTodo();
		}
	}

	@FXML
	public void searchTodo() {
		if (textField_search.getText().isBlank()) {
			showTodo();
		} else {
			searchingTodo(textField_search.getText());
		}
	}

	public void searchingTodo(String item) {

		TableColumn<Todo, String> id = new TableColumn<>("ID");
		id.setMinWidth(0);
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id.setVisible(false);

		TableColumn<Todo, String> task = new TableColumn<>("Task");
		task.setMinWidth(230);
		task.setCellValueFactory(new PropertyValueFactory<>("task"));
		task.setEditable(true);
		task.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn<Todo, Date> added = new TableColumn<>("Added At");
		added.setMinWidth(230);
		added.setCellValueFactory(new PropertyValueFactory<>("added"));

		TableColumn<Todo, Date> finished = new TableColumn<>("Completed At");
		finished.setMinWidth(230);
		finished.setCellValueFactory(new PropertyValueFactory<>("finished"));

		ObservableList<Todo> todos = FXCollections.observableArrayList();
		List<Todo> todoList = todoService.getAllTodoList();
		for (Todo todo : todoList) {
			if (todo.getTask().toUpperCase().contains(item.toUpperCase())) {
				todos.add(todo);
			}
		}

		tableView_todoTable.refresh();
		tableView_todoTable.getColumns().clear();
		tableView_todoTable.getColumns().addAll(id, task, added, finished);
		tableView_todoTable.getItems().clear();
		tableView_todoTable.setItems(todos);
	}
}
