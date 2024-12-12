[ title and purpose of the application ]


	The purpose of this application is to provide a GUI-based scheduling desktop application.
	The contract is with a global consulting organization that conducts business in multiple languages.
	The database is used for other systems, so its structure cannot be modified.
		
////////////////////////////////////////////////////////////////

[ author ]

     DAVID KRUPAR
	

////////////////////////////////////////////////////////////////

[ IDE including version number (e.g., IntelliJ Community 2020.01), full JDK of version used
 (e.g., Java SE 17.0.1), and JavaFX version compatible with JDK version (e.g. JavaFX-SDK-17.0.1) ]

3 - 	IntelliJ IDEA 2021.1.3 (Community Edition)
	Build #IC-211.7628.21, built on June 30, 2021
	Runtime version: 11.0.11+9-b1341.60 amd64
	VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
	Windows 10 10.0

	C:\Program Files\Java\jdk-11.0.11

	C:\Users\LabUser\Desktop\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib

////////////////////////////////////////////////////////////////

[ directions for how to run the program ]

4 - 	Program was created within Lab environment.  Java, Java FX, MySQL versions 
	listed also include filepaths provided in Lab environment.  Only thing to 
	mention is in modify appointment screen, if ANY OF THE FOUR date/time comboboxes
        are empty, the value used will be the label date and time.  In order to modify, 
	start or end times, they must have all 4 combobxes filled.  

	Lab Filepath = C:\Users\LabUser\IdeaProjects

	Project FolderName = SBConnectionAppV1

	Javadocs = C:\Users\LabUser\IdeaProjects\SBConnectionAppV1\javadoc

	labmda 1 = src/controller/LoginController  LINE 52
	lambda 2 = src/controller/AddCustomerController LINE 31

	Also, if I may request, if there is a failpoint I would ask for as much
	feedback as possible.  I would greatly appreciate the option to fix multiple bugs
	if found instead of one at a time.  I tested already, but, who knows.  

	THANK YOU!  ![-_-]!
	
////////////////////////////////////////////////////////////////

[ a description of the additional report of your choice you ran in part A3f ]

5 - 	The additional report is listed in the reports tab, it is the last report titled "Past Appointments By Contact"
	It was created by using a MYSQL SELECT query with inputs of String contactName.  Below is the query.  

	"Select * from appointments, contacts where contacts.Contact_Name = ? and contacts.Contact_ID = appointments.Contact_ID and appointments.Start < now() group by Appointment_ID order by Appointment_ID";

	SQL QUERY method is located in src/DBAccess/DBAppointments Line 314 
	Event handler located in src/controller/ReportsController Line 261
	Table located in reports page

////////////////////////////////////////////////////////////////

[ the MySQL Connector driver version number, including the update number (e.g., mysql-connector-java-8.1.23) ]

6 - 	C:\Users\LabUser\Desktop\mysql-connector-java-8.0.25\mysql-connector-java-8.0.25.jar

////////////////////////////////////////////////////////////////


Create a README.txt file that includes the following information:

•  title and purpose of the application

•  author, contact information, student application version, and date

•  IDE including version number (e.g., IntelliJ Community 2020.01), full JDK of version used (e.g., Java SE 17.0.1), and JavaFX version compatible with JDK version (e.g. JavaFX-SDK-17.0.1)

•  directions for how to run the program

•  a description of the additional report of your choice you ran in part A3f

•  the MySQL Connector driver version number, including the update number (e.g., mysql-connector-java-8.1.23)
	