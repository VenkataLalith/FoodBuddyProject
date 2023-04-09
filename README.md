# Food-Buddy-App

## Description
Welcome to our project! The project aims to develop a standalone Web app that enables effective shared grocery and expense management. The app automates inventory management and sends notifications when supplies run low, reducing the risk of running out of essential items. It also facilitates expense management by allowing users to split grocery bills and track expenses in real-time. The app further aims to reduce food waste and expenses through monthly usage analysis, providing users with insights into their consumption patterns and enabling them to make informed decisions about their shopping habits. Overall, the app offers a convenient and efficient solution to grocery and expense management for individuals and households.

## Instructions
1. Fork this repository
2. Clone the forked repository
3. Add your contributions (code or documentation)
4. Commit and push
5. Wait for pull request to be merged

## Dependencies

## Build/Deployment Instructions

## Usage Scenario

### Use Case: Manage Food Recipes

### Description: This use case scenario involves managing a collection of food recipes using a Maven project. The system allows users to add, edit, delete, and view recipes. Users can also search for recipes based on keywords or ingredients. The system also provides a user authentication and authorization feature to ensure only authorized users can access and modify recipes.

Actors:

1. User
2. Admin

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