# Casebook

Casebook is a Social Media Application.We have been tasked to implement this application for an unusually low price, by an unusually rich client.
There are several requirements followed in the implementation.

## 1.Database Requirements
The Database of the application needs to support 1 entity:
### User
* Has an Username
* Has a Password
* Has an Gender
* Has Friends (other users)

## 2.Functionality
The Functionality Requirements describe the functionality that the Application must support.
The application provides Guest (not logged in) users with the functionality to:
* Login
* Register
* View the Index page.
The application provides Users (logged in) with the functionality to:
* Logout
* View all Users (Home page)
* Add Friends (Clicking on [Add Friend] button on Home page)
* View self (logged-in user) Profile (Clicking on [Welcome, $(username)] message on Home page)
* View all Friends (Friends page)
* Remove Friends (Clicking on [Unfriend] button on Friends page)
* View friend Profile (Clicking on a friend’s name on Friends page)

The application provides functionality registering a User with 2 possible genders for the time being – “Female”, “Male”.

The Home page views **ONLY** the users which are **NOT** friends of the currently logged in user and are **NOT** the currently logged in user.

The Friends page views **ONLY** the users which **ARE** friends of the currently logged in user.

The application stores its data into a MySQL database, using Hibernate native.

## 3.Security
The Security Requirements are mainly access requirements. Configurations about which users can access specific functionalities and pages.
* Guest (not logged in) users can access Index page.
* Guest (not logged in) users can access Login page.
* Guest (not logged in) users can access Register page.
* Users (logged in) cannot access Guest pages.
* Users (logged in) can access Home page.
* Users (logged in) can access Friends page.
* Users (logged in) can access Add Friend functionality.
* Users (logged in) can access Remove Friend functionality.
* Users (logged in) can access Profile (self) page.
* Users (logged in) can access Profile (friend) page.
* Users (logged in) can access Logout functionality.
