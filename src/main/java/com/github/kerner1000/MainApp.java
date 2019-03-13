/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.kerner1000;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Alexander Kerner
 *
 */
public class MainApp extends Application {

    final AtomicInteger cnt = new AtomicInteger(0);
    final ObservableList<String> content = FXCollections.observableArrayList();

    public static void main(final String[] args) {

	launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
	preFillContent();
	final TableView<String> tableView = new TableView<>();
	final TableColumn<String, String> c1 = new TableColumn<>("C1");
	c1.setCellValueFactory(e -> new ReadOnlyObjectWrapper<>(e.getValue().toString()));
	tableView.getColumns().add(c1);
	tableView.setItems(content);
	tableView.setOnScroll(e -> {
	    final ScrollBar scrollBar = (ScrollBar) tableView.lookup(".scroll-bar:vertical");
	    if (scrollBar.getValue() == 0) {
		fillAtTop();
	    } else if (scrollBar.getValue() == 1) {
		fillAtBottom();
	    }
	});
	final StackPane root = new StackPane();
	root.getChildren().add(tableView);
	primaryStage.setScene(new Scene(root, 300, 250));
	primaryStage.show();
    }

    private void preFillContent() {
	for (int i = 0; i < 10; i++) {
	    content.add("element " + cnt.incrementAndGet());
	}
    }

    private void fillAtBottom() {
	content.add("element " + cnt.incrementAndGet());
    }

    private void fillAtTop() {
	content.add(0, "element " + cnt.incrementAndGet());
    }
}
