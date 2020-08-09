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
private EmployeePane ePane;
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
   ePane = new EmployeePane();
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

     if (student) { 
       declarePerson();
       setHeader(tContinue, tStudent, blank);
       pane.setCenter(sPane);
       primaryStage.setWidth(575);
       primaryStage.setHeight(310);
       setButton(1);
     } else if (employee) {
       declarePerson();
       setHeader(tContinue, tEmployee, blank);
       pane.setCenter(ePane);
       ePane.page1();
       primaryStage.setWidth(510);
       primaryStage.setHeight(270);
       setButton(2);
     }

     tfStudentOrEmployee.clear();
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
        primaryStage.setWidth(400);
        primaryStage.setHeight(160);
      } else {
        primaryStage.close();
        printInfo();
      }
      sPane.clearTextFields();
    });

    btNext[2].setOnAction(e -> {

      individual.setName(ePane.tfName.getText());
      individual.setSocial(ePane.tfSSN.getText());
      individual.setAddress(ePane.tfAddress.getText());
      individual.setPhone(ePane.tfPhone.getText());
      individual.setAge(Integer.parseInt(ePane.tfAge.getText()));
      individual.setGender(ePane.tfSex.getText());

      ePane.removePage1();
      ePane.page2();
      primaryStage.setWidth(525);
      setButton(3);
    });

    btNext[3].setOnAction(e -> {
      
      ((Employee) individual).setDepartment(ePane.tfDepartment.getText());
      ((Employee) individual).setTitle(ePane.tfTitle.getText());
      ((Employee) individual).setSalaried(ePane.tfHourlyOrSalary.getText());
      ((Employee) individual).setYearHired(ePane.tfYearHired.getText());

      boolean hourly = ePane.tfHourlyOrSalary.getText().equalsIgnoreCase("H");
      boolean salary = ePane.tfHourlyOrSalary.getText().equalsIgnoreCase("S");
      ePane.removePage2();
      if (hourly) {
        ePane.page3();
        primaryStage.setWidth(690);
        primaryStage.setHeight(200);
        setButton(4);
      } else if (salary) {
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
        ePane.page4();
        setButton(5);
      }
    });

    btNext[4].setOnAction(e -> {
      
      ((Employee) individual).setHourlyRate(Float.parseFloat(ePane.tfHourlyRate.getText()));
      ((Employee) individual).setHoursWorked(Float.parseFloat(ePane.tfHoursWorked.getText()));
      ((Employee) individual).setUnionDues(Float.parseFloat(ePane.tfUnionDues.getText()));

      ePane.removePage3();
      if (index < people.length) {
        primaryStage.setWidth(400);
        primaryStage.setHeight(160);
        tRecord.setText(String.format("Record %d", index + 1));
        tContinue.setText(String.format("Record %d (continued)", index + 1));
        setHeader(tRecord, blank, new Text(""));
        setButton(0);
        firstEntry();
      } else {
        primaryStage.close();
        printInfo();
      }
      ePane.clearTextFields();
    });

    btNext[5].setOnAction(e -> {

      ((Employee) individual).setAnnualSalary(ePane.tfAnnualSalary.getText());
      
      ePane.removePage4();
      if (index < people.length) {
        primaryStage.setWidth(400);
        primaryStage.setHeight(160);
        tRecord.setText(String.format("Record %d", index + 1));
        tContinue.setText(String.format("Record %d (continued)", index + 1));
        setHeader(tRecord, blank, new Text(""));
        setButton(0);
        firstEntry();
      } else {
        primaryStage.close();
        printInfo();
      }
      ePane.clearTextFields();
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
   
    private TextField tfType = new TextField();
    private TextField tfName = new TextField();
    private TextField tfMajor = new TextField();
    private TextField tfSSN = new TextField();
    private TextField tfAddress = new TextField();
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

    private void clearTextFields() {
      tfName.clear();
      tfMajor.clear();
      tfSSN.clear();
      tfAddress.clear();
      tfType.clear();
      tfSex.clear();
      tfAge.clear();
      tfPhone.clear();
      tfGPA.clear();
      tfYearOfGrad.clear();
    }
  }

  private class EmployeePane extends GridPane {

    private TextField tfName = new TextField();
    private TextField tfMajor = new TextField();
    private TextField tfSSN = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfSex = new TextField();
    private TextField tfAge = new TextField();
    private TextField tfPhone = new TextField();
    private TextField tfDepartment = new TextField();
    private TextField tfTitle = new TextField();
    private TextField tfHourlyOrSalary = new TextField();
    private TextField tfYearHired = new TextField();
    private TextField tfHourlyRate = new TextField();
    private TextField tfHoursWorked = new TextField();
    private TextField tfUnionDues = new TextField();
    private TextField tfAnnualSalary = new TextField();

    private Label lName = new Label("Name:");
    private Label lSSN = new Label("SSN (xxx-xx-xxxx):");
    private Label lAddress = new Label("Address:");
    private Label lPhone = new Label("Phone (xxx-xxxx):");
    private Label lAge = new Label("\tAge:");
    private Label lSex = new Label("\tGender (M/F/O):");
    private Label lDepartment = new Label("Department:");
    private Label lTitle = new Label("Job Title:");
    private Label lHourlyOrSalary = new Label("\tHourly or Salary (H/S):");
    private Label lYearHired = new Label("\tYear Hired (YYYY):");
    private Label lHourlyRate = new Label("Hourly Rate (xx.xx):");
    private Label lHoursWorked = new Label("\tHours Worked (xx.xx):");
    private Label lUnionDues = new Label("\tUnion Dues (xx.xx)");
    private Label lAnnualSalary = new Label("Annual Salary:");

    private EmployeePane() {
      setPadding(new Insets(5, 5, 5, 5));
      setHgap(5);
      setVgap(5);

      tfAge.setPrefColumnCount(2);
      tfSex.setPrefColumnCount(1);
      tfHourlyOrSalary.setPrefColumnCount(3);
      tfYearHired.setPrefColumnCount(3);
      tfHourlyRate.setPrefColumnCount(3);
      tfHoursWorked.setPrefColumnCount(3);
      tfUnionDues.setPrefColumnCount(4);
      tfAnnualSalary.setPrefColumnCount(6);      
    }

    private void clearTextFields() {
      tfName.clear();
      tfSSN.clear();
      tfAddress.clear();
      tfPhone.clear();
      tfSex.clear();
      tfAge.clear();
      tfDepartment.clear();
      tfTitle.clear();
      tfHourlyOrSalary.clear();
      tfYearHired.clear();
      tfHourlyRate.clear();
      tfHoursWorked.clear();
      tfUnionDues.clear();
      tfAnnualSalary.clear();
    }

    private void page1() {
      add(lName, 0, 0);
      add(tfName, 1, 0);
      add(lSSN, 0, 1);
      add(tfSSN, 1, 1);
      add(lAddress, 0, 2);
      add(tfAddress, 1, 2);
      add(lPhone, 0, 3);
      add(tfPhone, 1, 3);

      add(lAge, 2, 0);
      add(tfAge, 3, 0);
      add(lSex, 2, 1);
      add(tfSex, 3, 1);
    }

    private void page2() {
      add(lDepartment, 0, 0);
      add(tfDepartment, 1, 0);
      add(lTitle, 0, 1);
      add(tfTitle, 1, 1);

      add(lHourlyOrSalary, 2, 0);
      add(tfHourlyOrSalary, 3, 0);
      add(lYearHired, 2, 1);
      add(tfYearHired, 3, 1);
    }

    private void page3() {
      add(lHourlyRate, 0, 0);
      add(tfHourlyRate, 1, 0);

      add(lHoursWorked, 2, 0);
      add(tfHoursWorked, 3, 0);

      add(lUnionDues, 4, 0);
      add(tfUnionDues, 5, 0);
    }

    private void page4() {
      add(lAnnualSalary, 1, 0);
      add(tfAnnualSalary, 2, 0);
    }

    private void removePage1() {
      getChildren().remove(lName);
      getChildren().remove(lSSN);
      getChildren().remove(lAddress);
      getChildren().remove(lPhone);
      getChildren().remove(lAge);
      getChildren().remove(lSex);

      getChildren().remove(tfName);
      getChildren().remove(tfSSN);
      getChildren().remove(tfAddress);
      getChildren().remove(tfPhone);
      getChildren().remove(tfAge);
      getChildren().remove(tfSex);
    }

    private void removePage2() {
      getChildren().remove(lDepartment);
      getChildren().remove(lTitle);
      getChildren().remove(lHourlyOrSalary);
      getChildren().remove(lYearHired);

      getChildren().remove(tfDepartment);
      getChildren().remove(tfTitle);
      getChildren().remove(tfHourlyOrSalary);
      getChildren().remove(tfYearHired);
    }

    private void removePage3() {
      
      getChildren().remove(lHourlyRate);
      getChildren().remove(tfHourlyRate);

      getChildren().remove(lHoursWorked);
      getChildren().remove(tfHoursWorked);

      getChildren().remove(lUnionDues);
      getChildren().remove(tfUnionDues);
    }

    private void removePage4() {
      getChildren().remove(lAnnualSalary);
      getChildren().remove(tfAnnualSalary);
    }
  
  }

  /** The main method. */
  public static void main(String[] args) {
    Application.launch(args);
  }
}
