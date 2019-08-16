# Authenticated
 
A simple authentication system for zoo workers.  


# Project Overview

The goal of the project was to create a basic login authentication system for a zoo system. The program works by prompting the user to enter in their login information for the system, the system then reads from a text file to validate the user’s information using their username and password. The password used to login is converted to a hash password and is checked against the hash password in the text file. 

## Enhancement Changes

To enhance the program, changes were made to simulate a more real-world like application. To do so, a csv file is read and the user information is extracted and placed into an object which can then be used to authenticate the user on login. 
