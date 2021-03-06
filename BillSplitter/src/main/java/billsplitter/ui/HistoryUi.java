
package billsplitter.ui;

import billsplitter.domain.Bill;
import billsplitter.domain.HistoryService;
import billsplitter.domain.LoginService;
import java.util.List;
import javafx.beans.value.ObservableValue;
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

public class HistoryUi implements Ui {
    
    private final HistoryService historyService;
    private final LoginService loginService;
    
    public HistoryUi(HistoryService historyService, LoginService loginService) {
        this.historyService = historyService;
        this.loginService = loginService;
    }
    
    /**
     * Returns the scene for the history screen.
     *
     * @param   window   Stage object
     *
     * @return Scene
     */
    @Override
    public Scene getScene(Stage window) {
        
        ListView listView = GetListView(window);
        GridPane grid = getGrid(window);
        VBox box = new VBox();
        Scene scene = new Scene(box);
        box.getChildren().add(grid);
        box.getChildren().add(listView);
        
        return scene;
    }

    /**
     * Returns a gridpane for the contents of the history screen.
     *
     * @param   window   Stage object
     *
     * @return GridPane
     */
    private GridPane getGrid(Stage window) {
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int top = 5, right = 20, bottom = 15, left = 20;
        grid.setPadding(new Insets(top, right, bottom, left));
        
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(getLogoutButton(window), col, row);
        
        col++;
        Button t2 = new Button("New bill");
        t2.setOnAction((ActionEvent event) -> window.setScene(new NewBillUi(this.historyService, this.loginService).getScene(window)));
        grid.add(t2, col, row);
        
        return grid;
    }
    
    /**
     * Returns a listview with the titles of the current user's bills.
     *
     * @param   window   Stage object
     *
     * @return ListView
     */
    private ListView GetListView(Stage window) {
        
        ListView<Bill> list = new ListView<>();
        ObservableList<Bill> items = FXCollections.observableArrayList();
        List<Bill> bills = this.historyService.getAll();
        
        bills.forEach((bill) -> {
            items.add(bill);
        });
        
        list.setItems(items);
        
        list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Bill> observable, Bill oldValue, Bill newValue) -> {
            window.setScene(new NewBillUi(this.historyService, this.loginService, newValue.getId()).getScene(window));
        });
        
        return list;
    }
    
    /**
     * Returns a logout button.
     *
     * @param   window   Stage object
     *
     * @return Node
     */
    private Node getLogoutButton(Stage window) {
        
        Button logout = new Button("Log out");
        logout.setOnAction((ActionEvent event) -> {
            this.historyService.setUser(null);
            window.setScene(new LoginUi(this.historyService, this.loginService).getScene(window));
        });
        
        return logout;
    }
    
}
