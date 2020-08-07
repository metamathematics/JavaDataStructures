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
private Person individual;
private boolean isStudent;
private int index;

  public MainFX() {
   pane = new BorderPane();
   sPane = new StudentPane();
   btNext = new Button[6];
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
   index = 0;
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
      
    tRecord.setText(String.format("Record %d", index + 1));
    tContinue.setText(String.format("Record %d (continued)", index + 1));
    firstEntry();

    btNext[0].setOnAction(e -> {        
     boolean student = tfStudentOrEmployee.getText().equalsIgnoreCase("S");
     boolean employee = tfStudentOrEmployee.getText().equalsIgnoreCase("E");
     isStudentObject(student);
     System.out.println("student: " + student);

     if (student) { 
       System.out.println(index);
       declarePerson();
       setHeader(tContinue, tStudent, blank);
       pane.setCenter(sPane);
       primaryStage.setWidth(575);
       primaryStage.setHeight(310);
       setButton(1);
     }   
    });

    btNext[1].setOnAction(e -> {
        
      individual.setName(sPane.tfName.getText());
      individual.setSocial(sPane.tfSSN.getText());
      individual.setAddress(sPane.tfAddress.getText());
      individual.setPhone(sPane.tfPhone.getText());
      individual.setAge(Integer.parseInt(sPane.tfAge.getText()));
      individual.setGender(sPane.tfSex.getText());
      ((Student) individual).setGpa(Float.parseFloat(sPane.tfGPA.getText()));
      ((Student) individual).setGradYear(sPane.tfYearOfGrad.getText());
      ((Student) individual).setMajor(sPane.tfMajor.getText());

      if (index < people.length) {
        tRecord.setText(String.format("Record %d", index + 1));
        tContinue.setText(String.format("Record %d (continued)", index + 1));
        setHeader(tRecord, blank, new Text(""));
        setButton(0);
        firstEntry();
      } else {
        primaryStage.close();
        printInfo();
      }
    });
  }

  private void printInfo() {
    for (int i = 0; i < people.length; i++) {
      System.out.println();
      System.out.println(people[i].toString());
      System.out.println();
    }
  }

  private void isStudentObject(boolean answer) {
    isStudent = answer;
  }

  private void setButton(int index) {
    pane.setBottom(btNext[index]);
    pane.setAlignment(btNext[index], Pos.CENTER_RIGHT);
  }

  private void declareButtons() {
    for (int i = 0; i < btNext.length; i++)
      btNext[i] = new Button("NEXT");
  }

  private void declarePerson() {
    //student = true
    System.out.println("isStudent: " + isStudent);
    if (isStudent) {
      people[index] = new Student();
      individual = people[index];
      index++;
    } else {
      people[index] = new Employee();
      individual = people[index];
      index++;
    }
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
    private TextField tfMajor = new TextField();
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
      add(new Label("Major:"), 0, 1);
      add(tfMajor, 1, 1);
      add(new Label("SSN (xxx-xx-xxxx):"), 0, 2);
      add(tfSSN, 1, 2);
      add(new Label("Address:"), 0, 3);
      add(tfAddress, 1, 3);
      add(new Label("Phone (xxx-xxxx):"), 0, 4);
      add(tfPhone, 1, 4);

      add(new Label("\tAge:"), 2, 0);
      add(tfAge, 3, 0);
      add(new Label("\tGender (M/F/O):"), 2, 1);
      add(tfSex, 3, 1);
      add(new Label("\tGPA:"), 2, 2);
      add(tfGPA, 3, 2);
      add(new Label("\tGraduation Year (YYYY):"), 2, 3);
      add(tfYearOfGrad, 3, 3);
    }
  }

  /** The main method. */
  public static void main(String[] args) {
    Application.launch(args);
  }
}
