# Food-Buddy-App

## Description of the Project

Welcome to our project! Food-Buddy,The project aims to develop a standalone Web app that enables effective shared grocery and 
expense management. The app automates inventory management and sends notifications when supplies run low, reducing the 
risk of running out of essential items. It also facilitates expense management by allowing users to split grocery bills 
and track expenses in real-time. The app further aims to reduce food waste and expenses through monthly usage analysis,
providing users with insights into their consumption patterns and enabling them to make informed decisions about their 
shopping habits. Overall, the app offers a convenient and efficient solution to grocery and expense management for 
individuals and households.

## Dependencies

This project has a few dependencies that need to be installed before it can be run successfully. 
Please follow the instructions below to install the necessary dependencies:


Note: If you encounter any issues during the installation process, please refer to the 
documentation of each library for troubleshooting or contact the library's support team.

Thank you for using this project!


## Build/Deployment Instructions

This project can be deployed in a variety of ways depending on your needs.
Below are some general instructions for deploying this project:

1. Clone the project: First, you need to clone the project from the source code repository to your 
   local machine using the following command:

git clone https://github.com/[username]/[project-name].git

Note: Replace [username] with your GitHub username and [project-name] with the name of your project.

2. Install dependencies: Before deploying the project, make sure to install the required dependencies
   by following the instructions provided in the Dependencies section of this README file.

3. Set up environment variables: If your project requires environment variables, create a .env file in the 
   root directory of your project and set the required environment variables. You can also set environment variables 
   on your deployment platform if necessary.

4. Deploy the project: Once you have chosen a deployment platform, follow the instructions provided by the platform 
   to deploy your project. This may involve creating a deployment package, setting up a server or container, and 
   configuring any necessary settings.

Congratulations, your project is now deployed and ready to use! If you encounter any issues during the deployment process, 
please refer to the documentation provided by your deployment platform or contact their support team for assistance.


## Usage Scenario

### Use Case: Manage Food Recipes

### Description: This use case scenario involves managing a collection of food recipes using a Maven project. The system allows users to add, edit, delete, and view recipes. Users can also search for recipes based on keywords or ingredients. The system also provides a user authentication and authorization feature to ensure only authorized users can access and modify recipes.

Actors:

1. User

### Preconditions:

The system is installed and running
The user is authenticated and authorized to access the recipe management system

### Flow of Events:

1. User logs in to the system using their credentials if not they can register themselves.
2. User is redirected to the homepage where they can see option to add group/create group.
3. User clicks on "Create Group" button to create a new group.
4. User enters the Product name, Quantity, Expiry, and Amount and saves the Transaction.
5. User is redirected back to the inventory management where they can see the product added to the list.
6. User clicks on a recipe management from the list to view its details.
7. User can edit or delete the product from the inventory page.
8. User can see the all possible recipes based ingredients they have in stock.
9. User can also notify the other user if the ingredient is getting low in the count and user gets notified.
10. User can view, add, edit, or delete any ingredients  in the system.
11. User can also view a list of all users and their roles.
12. User can log out of the system.

### Alternate Flows:

If the user enters invalid credentials during login, the system displays an error message and prompts the user to enter valid credentials.
If the user enters incomplete or invalid details while adding or editing a ingredient, the system displays an error message and prompts the user to enter valid Quanity.
If the user tries to access the system without logging in, the system redirects them to the login page.