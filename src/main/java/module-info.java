open module todo {
	exports org.jeetu.work;
	exports org.jeetu.work.repository;
	exports org.jeetu.work.service;
	exports org.jeetu.work.gui;
	exports org.jeetu.work.bean;
	exports org.jeetu.work.controller;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.slf4j;
	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires spring.data.mongodb;
}