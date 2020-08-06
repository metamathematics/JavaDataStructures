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
private Person[] people;
private BorderPane pane;
private StudentPane sPane;
//private EmployeePane ePane;
private Button[] btNext;
private TextField tfStudentOrEmployee;
private Text tRecord;
private Text tContinue;
private Text tStudent;
private Text tEmployee;
private Text blank;

  public MainFX() {
   pane = new BorderPane();
   sPane = new StudentPane();
   btNext = new Button[4];
   declareButtons();

   tfStudentOrEmployee = new TextField();
   tfStudentOrEmployee.setPrefColumnCount(1);
   tRecord = new Text();
   tRecord.setFont(new Font(25));
   tContinue = new Text();
   tContinue.setFont(new Font(25));
   tStudent = new Text("Student");
   tStudent.setFont(new Font(20));
   tEmployee = new Text("Employee");
   tEmployee.setFont(new Font(20));
   blank = new Text("");
  }

  /** Overrides the start method from the Application class. */
  @Override
  public void start(Stage primaryStage) {
    Scanner input = new Scanner(System.in);
    System.out.print("\nHow many records will be entered?: ");
    int numberOfRecords = input.nextInt();
    people = new Person[numberOfRecords];

    pane.setPadding(new Insets(8, 5, 5, 5));
    setHeader(tRecord, blank, new Text(""));
    setButton(0);

    Scene scene = new Scene(pane, 400, 150);
    primaryStage.setTitle("Persons of Interest");
    primaryStage.setScene(scene);
    primaryStage.show();

    for (int index = 0; index < people.length; index++) {
      tRecord.setText(String.format("Record %d", index + 1));
      firstEntry();

      btNext[0].setOnAction(e -> {        
       boolean student = tfStudentOrEmployee.getText().equalsIgnoreCase("S");
       boolean employee = tfStudentOrEmployee.getText().equalsIgnoreCase("E");
       if (student) {
         setHeader(tRecord, tStudent, blank);
         pane.setCenter(sPane);
         primaryStage.setWidth(575);
         primaryStage.setHeight(275);
         setButton(3);
       }        
      });
      declarePerson(index);
    }
  }

  private void setButton(int index) {
    pane.setBottom(btNext[index]);
    pane.setAlignment(btNext[index], Pos.CENTER_RIGHT);
  }

  private void declareButtons() {
    for (int i = 0; i < btNext.length; i++)
      btNext[i] = new Button("NEXT");
  }

  private void declarePerson(int index) {
    boolean student = tfStudentOrEmployee.getText().equalsIgnoreCase("S");
    if (student)
      people[index] = new Student();
    else
      people[index] = new Employee();
  }

  private void setHeader(Text top, Text middle, Text bottom) {
    VBox vbox = new VBox(5);
    vbox.getChildren().addAll(top, middle, bottom);
    pane.setTop(vbox);
  }

  private void firstEntry() {
    HBox hbox = new HBox(5);
    hbox.getChildren().addAll(new Label("Student or Employee (S/E):"), tfStudentOrEmployee);
    hbox.setAlignment(Pos.CENTER);
    pane.setCenter(hbox);
  }

  private class StudentPane extends GridPane {
    
    private TextField tfName = new TextField();
    private TextField tfSSN = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfType = new TextField();
    private TextField tfSex = new TextField();
    private TextField tfAge = new TextField();
    private TextField tfPhone = new TextField();
    private TextField tfGPA = new TextField();
    private TextField tfYearOfGrad = new TextField();



    private StudentPane() {
      setPadding(new Insets(5, 5, 5, 5));
      setHgap(5);
      setVgap(5);
      
      tfSex.setPrefColumnCount(1);
      tfAge.setPrefColumnCount(2);
      tfGPA.setPrefColumnCount(2);
      tfYearOfGrad.setPrefColumnCount(3);

      add(new Label("Name:"), 0, 0);
      add(tfName, 1, 0);
      add(new Label("SSN (xxx-xx-xxxx):"), 0, 1);
      add(tfSSN, 1, 1);
      add(new Label("Address:"), 0, 2);
      add(tfAddress, 1, 2);
      add(new Label("Phone (xxx-xxxx):"), 0, 3);
      add(tfPhone, 1, 3);

      add(new Label("\tAge:"), 2, 0);
      add(tfAge, 3, 0);
      add(new Label("\tGender (M/F/O):"), 2, 1);
      add(tfSex, 3, 1);
      add(new Label("\tGraduation Year (YYYY):"), 2, 2);
      add(tfYearOfGrad, 3, 2);
    }
  }

  /** The main method. */
  public static void main(String[] args) {
    Application.launch(args);
  }
}
