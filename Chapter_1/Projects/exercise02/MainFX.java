import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.Scanner;

/** Collects information on Students and Employees. */
public class MainFX extends Application {
private int width = 320;
private int height = 350;
private Person[] people;
//private PersonPane ppane;
private VBox spane;
//private EmployeePane epane;

  /** Overrides the start method from the Application class. */
  @Override
  public void start(Stage primaryStage) {
    Scanner input = new Scanner(System.in);
    System.out.print("\nHow many records will be entered?: ");
    int numberOfRecords = input.nextInt();
    people = new Person[numberOfRecords];

    GridPane gpane1 = new GridPane();
    gpane1.setPadding(new Insets(5, 5, 5, 5));
    gpane1.setHgap(5);
    gpane1.setVgap(5);

    gpane1.add(new Label("Name:"), 0, 1);
    gpane1.add(new TextField(), 1, 1);
    gpane1.add(new Label("SSN (xxx-xx-xxxx):"), 0, 2);
    gpane1.add(new TextField(), 1, 2);
    gpane1.add(new Label("Address:"), 0, 3);
    gpane1.add(new TextField(), 1, 3);

    TextField tfType = new TextField();
    tfType.setPrefColumnCount(1);
    TextField tfSex = new TextField();
    tfSex.setPrefColumnCount(1);
    TextField tfAge = new TextField();
    tfAge.setPrefColumnCount(2);
    
    HBox hbox1 = new HBox(5);
    hbox1.setAlignment(Pos.CENTER_RIGHT);
    hbox1.getChildren().addAll(new Label("Gender:"), tfSex, new Label("Age:"), tfAge);
    HBox hbox2 = new HBox(5);
    hbox2.setAlignment(Pos.CENTER_RIGHT);
    hbox2.getChildren().addAll(new Label("Student or Employee (S/E):"), tfType);
    
    VBox vbox1 = new VBox(5);
    vbox1.getChildren().addAll(gpane1, hbox1, hbox2);
    
    Button btContinue = new Button("Continue");
    VBox vbox2 = new VBox(5);
    vbox2.setPadding(new Insets(8, 5, 5, 5));
    vbox2.setAlignment(Pos.CENTER_RIGHT);
    vbox2.getChildren().add(btContinue);

    BorderPane pane = new BorderPane();
    pane.setPadding(new Insets(8, 5, 5, 5));
    Text text = new Text("Record 1");
    text.setFont(new Font(15));
    pane.setTop(text);
    pane.setCenter(vbox1);
    pane.setBottom(vbox2);

    Button btNext = new Button("Next");

    btContinue.setOnAction(e -> {
      String type = tfType.getText();
      if (type.equalsIgnoreCase("S"))
        spane = getStudentPane();
        spane.getChildren().add(btNext);
        pane.setBottom(spane);
    });

    Scene scene = new Scene(pane, width, height);
    primaryStage.setTitle("Persons of Interest");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public VBox getStudentPane(){

    GridPane gpane = new GridPane();
    gpane.setPadding(new Insets(5, 5, 5, 5));
    gpane.setHgap(5);
    Label lMajor = new Label("Major:");
    TextField tfMajor = new TextField();
    gpane.add(lMajor, 0, 0);
    gpane.add(tfMajor, 1, 0);
    gpane.setAlignment(Pos.CENTER_RIGHT);
    
    HBox hbox2 = new HBox(5);
    hbox2.setAlignment(Pos.CENTER_RIGHT);

    TextField tfGPA = new TextField();
    tfGPA.setPrefColumnCount(2);
    TextField tfYearOfGrad = new TextField();
    tfYearOfGrad.setPrefColumnCount(4);

    hbox2.getChildren().addAll(new Label("Year of Graduation:"), tfYearOfGrad, new Label("GPA:"), tfGPA);

    VBox vbox = new VBox(5);
    vbox.setAlignment(Pos.CENTER_RIGHT);
    vbox.setPadding(new Insets(5, 5, 5, 5));
    vbox.getChildren().addAll(gpane, hbox2);

    return vbox;
  }

  /** The main method. */
  public static void main(String[] args) {
    Application.launch(args);
  }
}
