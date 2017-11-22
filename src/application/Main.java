package application;
	
import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	private static Controller ctrl = new Controller();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane ap = new AnchorPane();
	        ObservableList<String> items = FXCollections.observableArrayList("View Person", "Add Person", "Add Account");
			ListView<String> listView = new ListView<String>(items);
			ap.setTopAnchor(listView, 0.0);
			ap.setBottomAnchor(listView, 0.0);
			ap.getChildren().add(listView);
			
			HBox hb = new HBox();
			
			Pane viewPersonPane = new Pane();
			
			viewPersonPane.getChildren().add(viewPerson());
			Pane addPersonPane = new Pane();
			
			addPersonPane.getChildren().add(addPerson());
			Pane addAccountPane = new Pane();
			
			addAccountPane.getChildren().add(addAccount());
			
			
			ObservableList<Node> list = hb.getChildren();
			list.add(ap);
			list.add(viewPersonPane);
			
			listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
				
		        @Override
		        public void handle(MouseEvent event) {
		        	String value = listView.getSelectionModel().getSelectedItem();
		            list.clear();
		            list.add(ap);
		            if(value.equals("View Person")){
		            	list.add(viewPerson());
		            }
		            else if(value.equals("Add Person")){
		            	list.add(addPerson());
		            }
		            else if(value.equals("Add Account")){
		            	list.add(addAccount());
		            }
		        }
		    });
			
			
			
			Scene scene = new Scene(hb);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static VBox viewPerson(){
		
		VBox vb = new VBox();
		HBox hb = new HBox();
		ObservableList<Node> hList = hb.getChildren();
		TextField findPerson = new TextField();
		
		Button btn1 = new Button("Find Person");
		Button btn = new Button("Remove Person");
		
		hList.add(findPerson);
		hList.add(btn1);
		hb.setMargin(findPerson, new Insets(0,0,0,20));
		
		ObservableList<Node> list = vb.getChildren();
		Label personLabel = new Label("PERSON");
		personLabel.setId("title");
		Label detailsLabel = new Label("DETAILS");
		detailsLabel.setId("title");
		Label accountsLabel = new Label("ACCOUNTS");
		accountsLabel.setId("title");
		
		list.add(personLabel);
		list.add(hb);
		list.add(detailsLabel);
		list.add(new Label("Name:"));
		list.add(new Label(ctrl.getName()));
		list.add(new Label("Personal Identity Number:"));
		list.add(new Label(ctrl.getPNbr()));
		list.add(btn);
		vb.setMargin(btn, new Insets(7,0,5,20));
		list.add(accountsLabel);
		for(String[] account: ctrl.getAccounts()){
			list.add(new Label("Account Number:"));
			list.add(new Label(account[0]));
			list.add(new Label("Balance:"));
			list.add(new Label(account[1]));
		}
		btn.setOnAction(e -> {
			ctrl.removeCurrentPerson();
			list.clear();
			
			list.add(personLabel);
			list.add(hb);
			list.add(detailsLabel);
			list.add(new Label("Name:"));
			list.add(new Label(ctrl.getName()));
			list.add(new Label("Personal Identity Number:"));
			list.add(new Label(ctrl.getPNbr()));
			list.add(btn);
			vb.setMargin(btn, new Insets(7,0,5,20));
			list.add(accountsLabel);
			for(String[] account: ctrl.getAccounts()){
				list.add(new Label("Account Number:"));
				list.add(new Label(account[0]));
				list.add(new Label("Balance:"));
				list.add(new Label(account[1]));
			}
		});
		btn1.setOnAction(e -> {
			ctrl.getPersonalData(findPerson.getText());
			list.clear();
			
			list.add(personLabel);
			list.add(hb);
			list.add(detailsLabel);
			list.add(new Label("Name:"));
			list.add(new Label(ctrl.getName()));
			list.add(new Label("Personal Identity Number:"));
			list.add(new Label(ctrl.getPNbr()));
			list.add(btn);
			vb.setMargin(btn, new Insets(7,0,5,20));
			list.add(accountsLabel);
			for(String[] account: ctrl.getAccounts()){
				list.add(new Label("Account Number:"));
				list.add(new Label(account[0]));
				list.add(new Label("Balance:"));
				list.add(new Label(account[1]));
			}
		});
		
		return vb;
	}
	
	private static VBox addPerson(){
		VBox vb = new VBox();
		
		
		
		Label title = new Label("PERSON");
		title.setId("title");
		
		VBox hb = new VBox();
		Label lb = new Label("Personal Identity Number");
		TextField tf = new TextField();
		hb.setMargin(tf, new Insets(7,0,5,20));
		ObservableList<Node> hList = hb.getChildren();
		hList.add(lb);
		hList.add(tf);
		
		VBox hb1 = new VBox();
		Label lb1 = new Label("Name");
		TextField tf1 = new TextField();
		hb1.setMargin(tf1, new Insets(7,0,5,20));
		ObservableList<Node> hList1 = hb1.getChildren();
		hList1.add(lb1);
		hList1.add(tf1);
		
		Button add = new Button("Add");
		vb.setMargin(add, new Insets(7,0,5,20));
		add.setOnAction(e -> {
			ctrl.addPerson(tf.getText(), tf1.getText());
		});
		ObservableList<Node> list = vb.getChildren();
		list.add(title);
		list.add(hb);
		list.add(hb1);
		list.add(add);
		
		return vb;
	}
	
	private static VBox addAccount(){
		VBox vb = new VBox();
		Label title = new Label("ACCOUNT");
		title.setId("title");
		
		VBox hb = new VBox();
		Label lb = new Label("Account Number");
		TextField tf = new TextField();
		hb.setMargin(tf, new Insets(5,0,7,20));
		ObservableList<Node> hList = hb.getChildren();
		hList.add(lb);
		hList.add(tf);
		
		VBox hb1 = new VBox();
		Label lb1 = new Label("Balance");
		TextField tf1 = new TextField();
		hb1.setMargin(tf1, new Insets(5,0,7,20));
		ObservableList<Node> hList1 = hb1.getChildren();
		hList1.add(lb1);
		hList1.add(tf1);
		
		VBox hb2 = new VBox();
		Label lb2 = new Label("Owner Personal Identity Number");
		TextField tf2 = new TextField();
		hb2.setMargin(tf2, new Insets(5,0,7,20));
		ObservableList<Node> hList2 = hb2.getChildren();
		hList2.add(lb2);
		hList2.add(tf2);
		
		Button add = new Button("Add");
		vb.setMargin(add, new Insets(7,0,5,20));
		add.setOnAction(e -> {
			ctrl.addAccount(tf.getText(), tf1.getText(), tf2.getText());
		});
		
		ObservableList list = vb.getChildren();
		list.add(title);
		list.add(hb);
		list.add(hb1);
		list.add(hb2);
		list.add(add);
		
		return vb;
	}
}
