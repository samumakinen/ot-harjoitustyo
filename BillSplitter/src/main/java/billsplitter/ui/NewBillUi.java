
package billsplitter.ui;

import billsplitter.domain.HistoryService;
import billsplitter.domain.LoginService;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewBillUi implements Ui {
    
    private final TextField billTitle = new TextField();
    private final TextArea billDescription = new TextArea();
    private final TextField billPayers = new TextField();
    private final TextField billAmount = new TextField();
    private final HistoryService historyService;
    private final LoginService loginService;
    private String amountPerPerson = "";
    
    public NewBillUi(HistoryService historyService, LoginService loginService) {
        this.historyService = historyService;
        this.loginService = loginService;
    }
    
    @Override
    public Scene getScene(Stage window) {
        
        GridPane grid = getGrid(window);
        VBox box = new VBox();
        Scene scene = new Scene(box);
        box.getChildren().add(grid);
        
        return scene;
    }
    
    private GridPane getGrid(Stage window) {
        
        // Creating the GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int top = 5, right = 20, bottom = 15, left = 20;
        grid.setPadding(new Insets(top, right, bottom, left));

        // Heading
        Text heading = new Text("Create a new bill");
        heading.setId("heading");
        heading.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(heading, col, row, colSpan, rowSpan);
        
        // Title
        row++;
        grid.add(new Label("Title: *"), col, row);
        row++;
        grid.add(this.billTitle, col, row);
        
        // Description
        row++;
        grid.add(new Label("Description:"), col, row);
        row++;
        grid.add(this.billDescription, col, row);
        this.billDescription.setPrefSize(200, 100);
        this.billDescription.setWrapText(true);
        
        // Number of payers
        row++;
        grid.add(new Label("Number of payers: *"), col, row);
        row++;
        grid.add(this.billPayers, col, row);
        
        // Amount
        row++;
        grid.add(new Label("Amount: *"), col, row);
        row++;
        grid.add(this.billAmount, col, row);
        
        row++;
        grid.add(new Label(this.amountPerPerson), col, row);
        
        row++;
        Button btn = new Button("Calculate");
        btn.setOnAction((ActionEvent event) -> updateAmount());
        grid.add(btn, col, row);
        
        // Buttons at the bottom
        HBox hbox = getButtons(window);
        row += rowSpan;
        grid.add(hbox, col, row);
        
        return grid;
    }
    
    private HBox getButtons(Stage window) {
        
        // Create HBox
        HBox box = new HBox();
        box.setAlignment(Pos.BASELINE_CENTER);

        // Cancel
        Button cancel = new Button("Cancel");
        cancel.setOnAction((ActionEvent event) -> window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window)));
        box.getChildren().add(cancel);
        int top = 0, right = 5, bottom = 0, left = 5;
        HBox.setMargin(cancel, new Insets(top, right, bottom, left));

        // Clear
        Button clear = new Button("Clear");
        clear.setOnAction((ActionEvent event) -> clearText());
        box.getChildren().add(clear);
        top = 0;
        right = 5;
        bottom = 0;
        left = 0;
        HBox.setMargin(clear, new Insets(top, right, bottom, left));

        // Save
        Button save = new Button("Save");
        save.setOnAction((ActionEvent event) -> {
            saveBill();
            window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window));
        });
        box.getChildren().add(save);
        top = 0;
        right = 5;
        bottom = 0;
        left = 0;
        HBox.setMargin(save, new Insets(top, right, bottom, left));

        return box;
    }

    private void clearText() {
        
        this.billTitle.clear();
        this.billDescription.clear();
        this.billPayers.clear();
        this.billAmount.clear();
    }

    private void saveBill() {
        
        String title = this.billTitle.getText();
        String description = this.billDescription.getText();
        int payers = Integer.valueOf(this.billPayers.getText());
        double amount = Double.valueOf(this.billAmount.getText());
        this.historyService.createBill(title, description, payers, amount);
    }

    private void updateAmount() {
        
        if (!this.billPayers.getText().isEmpty() && !this.billAmount.getText().isEmpty()) {
            int payers = Integer.parseInt(this.billPayers.getText());
            double amount = Double.parseDouble(this.billAmount.getText());
            double result = amount / payers;
            this.amountPerPerson = "Amount per person: " + String.valueOf(result);
            System.out.println("result " + result);
        } else {
            System.out.println("Molemmissa kentissä pitää olla jotain!");
        }
    }
    
}
