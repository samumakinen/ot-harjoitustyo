
package billsplitter.ui;

import billsplitter.domain.Bill;
import billsplitter.domain.HistoryService;
import billsplitter.domain.LoginService;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryUi {
    private final HistoryService historyService;
    private final LoginService loginService;
    
    public HistoryUi(HistoryService historyService, LoginService loginService) {
        this.historyService = historyService;
        this.loginService = loginService;
    }
    
    public Scene buildGui(Stage window) {
        
        ListView listView = buildListView();
        GridPane grid = buildGrid(window);
        VBox box = new VBox();
        Scene scene = new Scene(box);
        box.getChildren().add(grid);
        box.getChildren().add(listView);
        
        return scene;
    }

    private GridPane buildGrid(Stage window) {
        
        // Creating the GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int top = 5, right = 20, bottom = 15, left = 20;
        grid.setPadding(new Insets(top, right, bottom, left));
        
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(buildLogoutButton(window), col, row);
        
        col++;
        Button t2 = new Button("Tee uusi");
        t2.setOnAction((ActionEvent event) -> window.setScene(new NewBillUi(this.historyService, this.loginService).buildGui(window)));
        grid.add(t2, col, row);
        
        return grid;
    }
    
    private ListView buildListView() {
        
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        List<Bill> bills = this.historyService.getAll();
        
        for (Bill bill : bills) {
            items.add(bill.getTitle());
        }
        
        list.setItems(items);
        
        return list;
    }
    
        private Node buildLogoutButton(Stage window) {
        
        Button logout = new Button("Log out");
        logout.setOnAction((ActionEvent event) -> {
            this.historyService.setUser(null);
            window.setScene(new LoginUi(this.historyService, this.loginService).buildGui(window));
        });
        
        return logout;
    }
    
}
