# RailwayTicketBooking - Java EE
##### Description:
* №22: Railway Ticket Booking*
Passenger makes a request for a ticket with a specific time and date to the destination station.
System searches for a suitable Train.   
Passenger makes a choice of Train and receives a invoice for payment.
The administrator manages the list of registered passengers.

##### Software and tools needed to run the project
* JDK 8
* MySQl Server 5.6
* Tomcat 9
* Maven 3

##### Database configuration:
* name: railway_ticket_booking
* user: root
* password: 3727

##### Application user credentials:
admin:
	email: vas.admin@gmail.com
	password: pass_1
user:
	email: gen.omel@gmail.com
	password: pass_2

##### Installation instructions:
1.Click "Clone or download" button, Download ZIP and unzip archive
or if you have installed "git", type git clone 'link' in a command line
2.In MySQL command line type these commands:
 - set names 'utf8';
 - source 'sql file PATH'; (without brackets)
WHERE sql file PATH is: src/main/resources/sqlScripts/create_db.sql

##### How to run application:
* in the command line navigate to the unziped project path and type: mvn clean install
* after that copy booking.war from the target folder to the Tomcat/webapps directory
* run Tomcat using bat file or via services
* navigate to the application URL - http://localhost:8080/booking
