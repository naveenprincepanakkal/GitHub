# GitHub User App

The GitHub User Search App is an Android application that allows you to search for GitHub users and view their details.

This Android application, built using the MVVM (Model-View-ViewModel) architecture, relies on several powerful libraries and technologies to provide an efficient and feature-rich user experience. Below are the key dependencies used in this project:


## Features

- Search for GitHub users by their usernames.
- View detailed user profiles, including their avatar, username, number of public repositories and number of followers.
- Clean and modern user interface built with Android Jetpack Compose.


## Dependencies

[Android Jetpack Compose](https://developer.android.com/jetpack/compose) is a modern Android UI toolkit that simplifies and accelerates UI development on Android. In this application, Retrofit is used to connect to the GitHub API and retrieve user data based on search queries. It handles HTTP requests and responses seamlessly.

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is a dependency injection library for Android that simplifies the management of dependencies in your Android app. It's built on top of Dagger and provides an easy and consistent way to inject dependencies into your Android components, such as Activities, Fragments, and ViewModels.

[Coil](https://coil-kt.github.io/coil/) is a modern and fast image loading library for Android. It's designed for efficiency and performance, making it ideal for loading and displaying images in your Android app. Coil supports features like caching, placeholders, and resizing, making it a versatile choice for image loading.


### ToDo
- UI testing in compose
- Adding build flavors
- Configuring proguard for production build
- Need to handle various screen sizes for tablet and horizontal screen orientations


## Contact

If you have any questions or suggestions, feel free to contact us at naveenprincep@gmail.com.

Happy coding!


