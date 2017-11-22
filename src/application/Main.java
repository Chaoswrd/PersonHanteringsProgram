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
			AnchorPane ap = new AnchorPane(); //2. En layout som tillåter en att sätta storleken på uttrymmet mellan dess element och dess parent
	        ObservableList<String> items = FXCollections.observableArrayList("View Person", "Add Person", "Add Account"); //Sätter menyns värden annars blir listan helt tom
			ListView<String> listView = new ListView<String>(items); //Deklarerar listview med dess värden som constructor input
			ap.setTopAnchor(listView, 0.0); //Sätter utrymmet mellan parent och barn
			ap.setBottomAnchor(listView, 0.0);
			ap.getChildren().add(listView); //Lägger till listview som ett barn av 2. anchorpane
			
			HBox hb = new HBox(); //Skapar en 1. Horizontell layout pane
			
			ObservableList<Node> list = hb.getChildren(); //1. Den horizontella panen tar 2. Anchorpane och viewPersonPane och lägger dem som barn till hb
			list.add(ap);
			list.add(viewPerson());  //Denna panen tar privat funktionen viewPerson som sitt barn
			
			listView.setOnMouseClicked(new EventHandler<MouseEvent>() { //Denna lyssnar på om något av menyitemsen klickas
				
		        @Override
		        public void handle(MouseEvent event) { //Om en sådan trycks så körs denna biten av kod
		        	String value = listView.getSelectionModel().getSelectedItem(); //Denna sparar namnet på listitemen
		            list.clear(); //Tömmer hb's lista så den har inga barn
		            list.add(ap); //Lägger till ap för annars har man ingen meny
		            if(value.equals("View Person")){ //Om man trycker på view Person så ska view person läggas till höger.
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
			
			
			
			Scene scene = new Scene(hb); //Huvudpane allting som inte är det som är "operativramen"
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //Lägger till stylesen från application.css till hela "contenten"
			primaryStage.setScene(scene); //Fönstret får sin content
			primaryStage.setMaximized(true); //Sätter fönstret till full size så den fyller skärmen
			primaryStage.show(); //Displayar "programmet" till skärmen
		} catch(Exception e) {
			e.printStackTrace(); //Fångar någon exception som skulle kunna hända
		}
	}
	
	public static void main(String[] args) {
		launch(args); //Kör programmet!
	}
	
	private static VBox viewPerson(){ //Om man klickar på view person i menyn så displayar den högra sidan denna funktion som returnerar en vertikal layout pane
		
		VBox vb = new VBox(); //Main pane, där allting slutligen som ska returneras ligger i.
		HBox hb = new HBox(); //En horizontell layout pane som innehåller en input och en hitta person knapp. Den är horizontell så att knappen hamnar till höger om inputen istället för under.
		ObservableList<Node> hList = hb.getChildren(); //skapar en lista så man kan lägga till mer än ett barn.
		TextField findPerson = new TextField();
		
		Button btn1 = new Button("Find Person");
		Button btn = new Button("Remove Person"); //Läggs till under personnummeret
		
		hList.add(findPerson);
		hList.add(btn1);
		hb.setMargin(findPerson, new Insets(0,0,0,20)); //Flyttar inputen 20 px till höger så den inte ligger intill kanten
		
		ObservableList<Node> list = vb.getChildren();
		Label personLabel = new Label("PERSON"); //Titel, alla labels som har set id title har större 
		personLabel.setId("title");
		Label detailsLabel = new Label("DETAILS");
		detailsLabel.setId("title");
		Label accountsLabel = new Label("ACCOUNTS");
		accountsLabel.setId("title");
		
		list.add(personLabel); //Lägger till titeln
		list.add(hb); //Lägger till inputen med find person knappen 
		list.add(detailsLabel);
		list.add(new Label("Name:"));
		list.add(new Label(ctrl.getName())); //Hämtar namnet på personen som är just nu person, om default så kör default person annars finns det en riktig användarperson
		list.add(new Label("Personal Identity Number:"));
		list.add(new Label(ctrl.getPNbr())); //Hämtar personnummret
		list.add(btn); //Knappen som raderar person
		vb.setMargin(btn, new Insets(7,0,5,20));
		list.add(accountsLabel);
		for(String[] account: ctrl.getAccounts()){ //Tar fram personens alla account
			list.add(new Label("Account Number:"));
			list.add(new Label(account[0])); //Account nummret i account
			list.add(new Label("Balance:"));
			list.add(new Label(account[1])); //Balancen i account
		}
		btn.setOnAction(e -> { //Raderar knappen, tar bort nuvarande person och displayar default person. Allting är som ovanstående.
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
		btn1.setOnAction(e -> { //Letar efter person, om personen finns så displayas det annars displayas default person
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
	
	private static VBox addPerson(){ //Displayar addPerson panen
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
		add.setOnAction(e -> { //Add hämtar texten som finns i inputsen och skapar en ny person med hjälp av de namnet
			ctrl.addPerson(tf.getText(), tf1.getText());
		});
		ObservableList<Node> list = vb.getChildren();
		list.add(title);
		list.add(hb);
		list.add(hb1);
		list.add(add);
		
		return vb;
	}
	
	private static VBox addAccount(){ //Visar en pane som låter en lägga till en nytt konto till en person
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
		add.setOnAction(e -> { //Hämtar texten hos de tre inputsen och skapar ett konto baserat på det kopplat till det personnummret.
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
