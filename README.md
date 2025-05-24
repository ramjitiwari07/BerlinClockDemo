# BerlinClock
- This is a sample app which converts normal time to Berlin clock illustration.
- The app is developed by using Clean Architecture with MVVM and Jetpack Compose for UI screens.

# Rules
- The Berlin Clock is a clock that tells the time using a series of illuminated coloured blocks.
- It convert the particular time as 24h format ('hh:mm:ss') and output a string that reproduce the Berlin clock.
- The Berlin clock parameters "O" = Light off, "R" = Red light, "Y" = Yellow light

# How to run app
In Android studio, create a new project using from version control option, provide git link.
```sh
https://github.com/ramjitiwari07/BerlinClockDemo.git
```
After successful gradle sync run the app in emulator or connected device.

You can also use CLI to build to install app
```sh
 ./gradlew installDebug
```


# Application Screens
![app_screen](https://github.com/ramjitiwari07/BerlinClockDemo/blob/main/berlinclock.jpg "App Screen")


### Libraries
- [Jetpack](https://developer.android.com/jetpack)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes.
    - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - is a state-holder observable flow that emits the current and new state updates to its collectors.
    - [Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is the modern toolkit for building native Android UI

### Links
- [Berlin Clock Kata](https://www.codewars.com/kata/5a1463678ba9145a670000f9) - Complete description about Berlin Clock Kata