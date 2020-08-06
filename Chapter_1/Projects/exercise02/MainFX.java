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
private int width;
private int height;
private Person[] people;
private StudentPane sPane;
//private EmployeePane ePane;
private Button btNext;
private int state;
TextField tfStudentOrEmployee;
Text tRecord;
Text tContinue;
Text tStudent;
Text tEmployee;
Text blank;

  public MainFX() {
   width = 400;
   height = 150;
   sPane = new StudentPane();
   btNext = new Button("NEXT");
   state = 0;
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

    BorderPane pane = new BorderPane();
    pane.setPadding(new Insets(8, 5, 5, 5));
    setHeader(pane, tRecord, new Text(""), new Text(""));
    pane.setBottom(btNext);
    pane.setAlignment(btNext, Pos.CENTER_RIGHT);

    Scene scene = new Scene(pane, width, height);
    primaryStage.setTitle("Persons of Interest");
    primaryStage.setScene(scene);
    primaryStage.show();

    for (int index = 0; index < people.length; index++) {
      tRecord.setText(String.format("Record %d", index + 1));
      firstQuestion(pane);

      btNext.setOnAction(e -> {
        if (state == 1) {
          if (tfStudentOrEmployee.getText().equalsIgnoreCase("S")) {
            state = 2;
            setHeader(pane, tRecord, tStudent, blank);
            pane.setCenter(sPane);
            width = 575;
            height = 275;
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
          }
        }
      });
      declarePerson(state, index);
    }
  }

  public void declarePerson(int state, int index) {
    if (state == 1)
      people[index] = new Student();
    else
      people[index] = new Employee();

    state = 0;
  }

  public void setHeader(BorderPane pane, Text top, Text middle, Text bottom) {
    VBox vbox = new VBox(5);
    vbox.getChildren().addAll(top, middle, bottom);
    pane.setTop(vbox);
  }

  public void firstQuestion(BorderPane pane) {
    state = 1;
    HBox hbox = new HBox(5);
    hbox.getChildren().addAll(new Label("Student or Employee (S/E):"), tfStudentOrEmployee);
    hbox.setAlignment(Pos.CENTER);
    pane.setCenter(hbox);
  }

  public class StudentPane extends GridPane {
    
    private TextField tfName = new TextField();
    private TextField tfSSN = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfType = new TextField();
    private TextField tfSex = new TextField();
    private TextField tfAge = new TextField();
    private TextField tfPhone = new TextField();
    private TextField tfGPA = new TextField();
    private TextField tfYearOfGrad = new TextField();



    public StudentPane() {
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
      add(new Label("Phone:"), 0, 3);
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
