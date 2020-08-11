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
/** Array of Person objects. */
private Person[] people;
/** The pane that holds all nodes. */
private BorderPane pane;
/** The pane for holding nodes related to Student objects. */
private StudentPane sPane;
/**  The pane for holding nodes related to Employee ojects */
private EmployeePane ePane;
/** Array of Button obects. */
private Button[] btNext;
/** The text field for the first question. */
private TextField tfStudentOrEmployee;
/** Text object for the word "Record". */
private Text tRecord;
/** Text object for the word "Record (continued)". */
private Text tContinue;
/** Text object for the word "Student". */
private Text tStudent;
/** Text object for the word "Employee". */
private Text tEmployee;
/** Text object for a blank word. */
private Text blank;
/** Reference variable for a given people[i]. */
private Person individual;
/** Variable to determine if an object is a Student. */
private boolean isStudent;
/** The index of the people array. */
private int index;

  /** Initialize/declare some variables. */
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
    // Prompt user to enter the number of records to be added
    Scanner input = new Scanner(System.in);
    System.out.print("\nHow many records will be entered?: ");

    // Initialize the people array
    int numberOfRecords = input.nextInt();
    people = new Person[numberOfRecords];
    
    // Set padding for the pane
    pane.setPadding(new Insets(8, 5, 5, 5));

    // Create a scene and add it to the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Persons of Interest");
    primaryStage.setScene(scene);
    primaryStage.show();
      
    // Set up the first page of the current record
    firstEntry(primaryStage);


    // Assign actions to the btNext[0]
    btNext[0].setOnAction(e -> {

      // Determine if current record should be Student or Employee object
      boolean student = tfStudentOrEmployee.getText().equalsIgnoreCase("S");
      boolean employee = tfStudentOrEmployee.getText().equalsIgnoreCase("E");
      isStudentObject(student);

      
      if (student) {
        // if Student object, declare people[index] as Student
        declarePerson();
        // Set the header for the next page
        setHeader(tContinue, tStudent, blank);
        // Add StudentPane to the pane
        pane.setCenter(sPane);
        // Update the stage dimensions
        primaryStage.setWidth(575);
        primaryStage.setHeight(310);
        // Change the button to btNext[1]
        setButton(1);
      } else if (employee) {
        // if Employee object, declare people[index] as Employee
        declarePerson();
        // Set the header for the next page
        setHeader(tContinue, tEmployee, blank);
        // Add page 1 of the EmployeePane to the pane
        pane.setCenter(ePane);
        ePane.page1();
        // Update stage dimensions
        primaryStage.setWidth(510);
        primaryStage.setHeight(270);
        // Change the button to btNext[2]
        setButton(2);
     }
     // Clear the TextField
     tfStudentOrEmployee.clear();
    });


    // Assign action to btNext[1]
    btNext[1].setOnAction(e -> {
      
      // Update variables for this Student object
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
        // If there are more record to add, proceed to the first page
        firstEntry(primaryStage);
      } else {
        // Else, close the stage and print all info
        primaryStage.close();
        printInfo();
      }
      // Clear all sPane text fields 
      sPane.clearTextFields();
    });


    // Assign action to btNext[2]
    btNext[2].setOnAction(e -> {

      // Update specified variables for this Employee
      individual.setName(ePane.tfName.getText());
      individual.setSocial(ePane.tfSSN.getText());
      individual.setAddress(ePane.tfAddress.getText());
      individual.setPhone(ePane.tfPhone.getText());
      individual.setAge(Integer.parseInt(ePane.tfAge.getText()));
      individual.setGender(ePane.tfSex.getText());

      // Remove all Employee nodes
      ePane.removePage1();
      // Add all nodes for the next page
      ePane.page2();
      // Adjust width if the stage
      primaryStage.setWidth(525);
      // Change button to btNext[3]
      setButton(3);
    });


    // Assign action to button btNext[3]
    btNext[3].setOnAction(e -> {
      
      // Update specified variables for this Employee
      ((Employee) individual).setDepartment(ePane.tfDepartment.getText());
      ((Employee) individual).setTitle(ePane.tfTitle.getText());
      ((Employee) individual).setSalaried(ePane.tfHourlyOrSalary.getText());
      ((Employee) individual).setYearHired(ePane.tfYearHired.getText());

      // Determine if Employee is hourly or salary
      boolean hourly = ePane.tfHourlyOrSalary.getText().equalsIgnoreCase("H");
      boolean salary = ePane.tfHourlyOrSalary.getText().equalsIgnoreCase("S");
      ePane.removePage2();

      // if hourly, set up page with hourly questions
      if (hourly) {
        ePane.page3();
        primaryStage.setWidth(690);
        primaryStage.setHeight(200);
        setButton(4);

      // else set up salary questions page
      } else if (salary) {
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
        ePane.page4();
        setButton(5);
      }
    });

    
    // Assign action to button btNext[4]
    btNext[4].setOnAction(e -> {
      
      // Update specified variabel for this Employee
      ((Employee) individual).setHourlyRate(Float.parseFloat(ePane.tfHourlyRate.getText()));
      ((Employee) individual).setHoursWorked(Float.parseFloat(ePane.tfHoursWorked.getText()));
      ((Employee) individual).setUnionDues(Float.parseFloat(ePane.tfUnionDues.getText()));

      // Remove Employee page 3 nodes
      ePane.removePage3();
      
      // if there are more records to add, proceed to the first page of the next record
      if (index < people.length) {
        firstEntry(primaryStage);

      // else close the window and print all record info
      } else {
        primaryStage.close();
        printInfo();
      }

      // Clear all EmployeePane textfields
      ePane.clearTextFields();
    });


    // Assign action to button btNext[5]
    btNext[5].setOnAction(e -> {

      // Update the AnnualSalary for this Employee
      ((Employee) individual).setAnnualSalary(ePane.tfAnnualSalary.getText());
      // Remove employee page 4
      ePane.removePage4();

      // if there are more records to add, proceed to the first page fo the next record
      if (index < people.length) {
        firstEntry(primaryStage);
      // else close the window and print all record info
      } else {
        primaryStage.close();
        printInfo();
      }
      // Clear all EmployeePane text fields
      ePane.clearTextFields();
    });
  }


  /** Prints the toString() info for each people[i]. */
  private void printInfo() {
    for (int i = 0; i < people.length; i++) {
      System.out.println();
      System.out.println(people[i].toString());
      System.out.println();
    }
  }


  /** Assigns isStudent to True/False if answer is True/False.
      @param answer True or False
    */
  private void isStudentObject(boolean answer) {
    isStudent = answer;
  }


  /** Position the btNext[index] in the buttom right of the pane.
      @param index The index of btNext
    */
  private void setButton(int index) {
    pane.setBottom(btNext[index]);
    pane.setAlignment(btNext[index], Pos.CENTER_RIGHT);
  }


  /** Set up each btNext[i]. */
  private void declareButtons() {
    for (int i = 0; i < btNext.length; i++)
      btNext[i] = new Button("NEXT");
  }
  

  /** Declares people[index] as the appropriate object (Student or Employee). */
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
  

  /** Sets the header.
      @param top The text at the top
      @param middle The text in the middle
      @param bottom The text at the bottom
    */
  private void setHeader(Text top, Text middle, Text bottom) {
    VBox vbox = new VBox(5);
    vbox.getChildren().addAll(top, middle, bottom);
    pane.setTop(vbox);
  }
  

  /** Set up the first page the current record.
      @param stage The primaryStage
    */
  private void firstEntry(Stage stage) {
    // Update width and height of the stage
    stage.setWidth(400);
    stage.setHeight(160);

    // Update the title texts
    tRecord.setText(String.format("Record %d", index + 1));
    tContinue.setText(String.format("Record %d (continued)", index + 1));

    // Set the header and the NEXT button
    setHeader(tRecord, blank, new Text(""));
    setButton(0);
    
    // Add the Student/Emplyee question
    HBox hbox = new HBox(5);
    hbox.getChildren().addAll(new Label("Student or Employee (S/E):"), tfStudentOrEmployee);
    hbox.setAlignment(Pos.CENTER);
    pane.setCenter(hbox);
  }


  /** Pane for displaying Student forms. */
  private class StudentPane extends GridPane {
    // TextFields
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

    /** Constructs a StudentPane object. */
    private StudentPane() {
      setPadding(new Insets(5, 5, 5, 5));
      setHgap(5);
      setVgap(5);
      
      tfSex.setPrefColumnCount(1);
      tfAge.setPrefColumnCount(2);
      tfGPA.setPrefColumnCount(2);
      tfYearOfGrad.setPrefColumnCount(3);
      
      // Add all column 1 nodes to this StudentPane
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
      // Add all column 2 nodes to this StudentPane
      add(new Label("\tAge:"), 2, 0);
      add(tfAge, 3, 0);
      add(new Label("\tGender (M/F/O):"), 2, 1);
      add(tfSex, 3, 1);
      add(new Label("\tGPA:"), 2, 2);
      add(tfGPA, 3, 2);
      add(new Label("\tGraduation Year (YYYY):"), 2, 3);
      add(tfYearOfGrad, 3, 3);
    }
    
    /** Clear all TextFields for this StudentPane. */
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


  /** Pane for displaying Employee forms. */
  private class EmployeePane extends GridPane {
    // TextFields
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

    // Labels
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
    
    /** Constructs an EmployeePane object. */
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
    

    /** Clears all TextFields for this EmployeePane. */
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

    
    /** Adds nodes for the first Employee page. */
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


    /** Adds nodes for the second Employee page. */
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


    /** Adds nodes for the third Employee page. */
    private void page3() {
      add(lHourlyRate, 0, 0);
      add(tfHourlyRate, 1, 0);

      add(lHoursWorked, 2, 0);
      add(tfHoursWorked, 3, 0);

      add(lUnionDues, 4, 0);
      add(tfUnionDues, 5, 0);
    }


    /** Adds nodes for the fourth Employee page. */
    private void page4() {
      add(lAnnualSalary, 1, 0);
      add(tfAnnualSalary, 2, 0);
    }


    /** Removes all nodes from the first Employee page. */
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


    /** Removes all nodes from the decond Employee page. */
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


    /** Removes all nodes from the third Employee page. */
    private void removePage3() {      
      getChildren().remove(lHourlyRate);
      getChildren().remove(tfHourlyRate);

      getChildren().remove(lHoursWorked);
      getChildren().remove(tfHoursWorked);

      getChildren().remove(lUnionDues);
      getChildren().remove(tfUnionDues);
    }


    /** Removes all nodes from the fourth Employee page. */
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
