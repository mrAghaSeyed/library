# Library App

## Table of Contents

- [Library App](#library-app)
  - [Table of Contents](#table-of-contents)
  - [Description](#description)
  - [Features](#features)
  - [Screens](#screens)
  - [Code Structure](#code-structure)
  - [Dependencies](#dependencies)
  - [Getting Started](#getting-started)
  - [Usage](#usage)

## Description

Library App is an Android application that allows users to browse and manage books. It includes features such as viewing book details, adding books to favorites, and managing a shopping cart.

## Features

- Browse and search books
- View book details
- Add books to favorites
- Manage a shopping cart

## Screens

The app consists of the following screens:

1. Home: Displays a list of books and provides search functionality.
2. Details: Shows detailed information about a selected book.
3. Favorites: Displays a list of favorite books.
4. Cart: Shows the books added to the shopping cart and allows proceeding to checkout.

## Code Structure

The codebase is organized into several Kotlin files:

- `BookList.kt`: Contains the `BooksList` composable function used to display a list of books.
- `Cart.kt`: Contains the `Cart` composable function and `CartViewModel` for managing the shopping cart.
- `Database.kt`: Defines the `LibraryDatabase` class for managing the app's SQLite database.
- `Details.kt`: Contains the `Details` composable function for displaying book details.
- `Favorites.kt`: Contains the `Favorites` composable function and `FavoritesViewModel` for managing favorite books.
- `Home.kt`: Contains the `Home` composable function and `HomeViewModel` for displaying and searching books.
- Other classes: `Book`, `BookWithCount`, `CartItem`, `FavoriteBook`, and `User` are entity classes representing different objects in the app.

## Dependencies

The app relies on the following dependencies:

- Jetpack Compose: A modern UI toolkit for building native UIs.
- Coil: An image loading library for loading book covers.
- Room: A persistence library for managing the app's SQLite database.
- Dagger Hilt: A dependency injection library for managing dependencies.
- Firebase Authentication: For user authentication.

## Getting Started

To run the app locally, follow these steps:

1. Clone the repository: `git clone "https://github.com/mrAghaSeyed/library.git"`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or a connected device.

## Usage

Upon launching the app, you will be presented with the login screen. If you don't have an account, you can click on the "Sign Up" button to create a new account. Once logged in, you will be directed to the main screen where you can browse and search for books. You can also navigate to the terms and conditions screen to view the app's terms and conditions. To log out, click on the logout button in the app toolbar.
