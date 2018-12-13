The following information is given to Desing and Testing Teachers 
with the purpose of they get access into the project. 
Other uses are not allowed.

If you want to get in our project 
you will have to log into https://github.com/login
and write the following parameters:

# Repository´s Username
* dpprofesores
# Repository´s Password
* @$DP1819_F133/G2

After you log in, you have to see 
on the left of the User Webpage
a box which contains all the repositories. 
For us, Design and Testing created by Angel Delgado.
You click on, and will you see 
all the information related to the deliverable.

Things to take into account before you see our deliverable:

1. We´ve chosen a colour selection for each level to do:

	- Yellow : Level C
	- Purple : Level B
	- Green : Level A

2. Considering profiles requirements, we have considered important to have at least one LinkedIn Profile when doing the inicial inscription.
   This is why, in level C, is required any kind of profile. But in Level B is compulsory to have a LinkedIn profile.

3. Every actor will have to access the system with role assigned. 
   If an actor has other roles that are not the same as he registered in the beginning, he will have to make a new account.

4. As we have decided to get the A+ Mark on this deliverable, You have a PDF into folder 'Item 4'. 
   This PDF explains how we have implemented the Hibernate technology required.

5. A MySQL is attached to this deliverable for the correct execution of the project:

drop database if exists `Acme-HandyWork`;
create database `Acme-HandyWork`;
#drop user 'acme-user'@'%';
#drop user 'acme-manager'@'%';
create user 'acme-user'@'%'
identified by 'ACME-Us3r-P@ssw0rd';

create user 'acme-manager'@'%'
identified by 'ACME-M@n@ger-6874';

grant select, insert, update, delete
on `Acme-HandyWork`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter,
create temporary tables, lock tables, create view, create routine,
alter routine, execute, trigger, show view
on `Acme-HandyWork`.* to 'acme-manager'@'%';

6. According to the requirement 39 (Acme - HandyWorker Requirements PDF), a complaint can not be updated or deleted. So, the Item 2 requested on this deliverable can not be completely satisfied .