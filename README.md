[![kotlin](https://img.shields.io/badge/Kotlin-1.4.xxx-brightgreen.svg)](https://kotlinlang.org/)  [![coroutines](https://img.shields.io/badge/coroutines-asynchronous-red.svg)](https://kotlinlang.org/docs/reference/coroutines-overview.html)[![Junit5](https://img.shields.io/badge/Junit5-testing-yellowgreen.svg)](https://junit.org/junit5/)   [![Espresso](https://img.shields.io/badge/Espresso-testing-lightgrey.svg)](https://developer.android.com/training/testing/espresso/) [![Kotlin-Android-Extensions ](https://img.shields.io/badge/Kotlin--Android--Extensions-plugin-red.svg)](https://kotlinlang.org/docs/tutorials/android-plugin.html) [![MVVM ](https://img.shields.io/badge/Clean--Code-MVVM-brightgreen.svg)](https://github.com/googlesamples/android-architecture)  ![MVP ](https://img.shields.io/badge/Clean--Code-MVP-brightgreen.svg)
[![Build Status](https://app.bitrise.io/app/b7eabce000fac983/status.svg?token=i6oJjdA4ZD4wM6NDA5cB7g&branch=master)](https://app.bitrise.io/app/b7eabce000fac983)![MVVM3](https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)



---

###### Brief Overview of Project 👨‍💻 :
1. The user registers or logs into the app where details of other team members are also present.
2. The employees can discuss therir issues on the company chat.
3. Data of a new team member can be imported via CSV file or can be manually added. Data of the existing team members can be shared in CSV format.
4. The app is built using MVVM architecture, android architecture components, material layout, etc.




---
### Technologies Used 📱

| Android Studio | Flutter | Firebase |
|:--------------:|:-------:|:--------:|
|![Android Studio Logo](https://techcrunch.com/wp-content/uploads/2017/02/android-studio-logo.png?w=730&crop=1)|![Kotlin Logo](https://w7.pngwing.com/pngs/314/161/png-transparent-kotlin-android-software-development-anonymous-function-programming-language-android-blue-angle-text-thumbnail.png)|![Firebase Logo](https://www.technisys.com/wp-content/uploads/2021/06/firebase_logo-1.png)|

# [Model-View-ViewModel (ie MVVM)](https://github.com/ahmedeltaher/Android-MVVM-architecture)

Model-View-ViewModel (ie MVVM) is a template of a client application architecture, proposed by John Gossman as an alternative to MVC and MVP patterns when using Data Binding technology. Its concept is to separate data presentation logic from business logic by moving it into particular class for a clear distinction.
You can also check [**MVP**](https://github.com/ahmedeltaher/Android-MVP-Architecture)

**MVVM Best Pratice:**
- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes to it.
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.

### Screenshots  📸
| Welcome Screen | Employee Login | Employee Register | Home Screen |
|:--------------:|:-------:|:-------:|:-------:|
|![Icon](https://user-images.githubusercontent.com/74851399/198937803-eb01f067-cb09-472f-b284-d113ba1010bc.png)|![Login](https://user-images.githubusercontent.com/74851399/198937842-55db5b74-9930-45e6-b9f5-de98a3f93828.png)|![Register](https://user-images.githubusercontent.com/74851399/198937845-bfad2c44-4dcd-4c38-aff3-21e7c76031fa.png)|![Home Screen](https://user-images.githubusercontent.com/74851399/198937848-6a139d99-fb07-4565-ba0e-4dbd0c62650c.png)|

| Navigation | Add Employee Screen | Global Chat |
|:--------------:|:-------:|:-------:|
|![Navigation](https://user-images.githubusercontent.com/74851399/198937849-622fd219-6e80-42d5-b207-a5dd94c8dc83.png)|![Add Employee](https://user-images.githubusercontent.com/74851399/198937855-b9d0fe80-6710-4c60-93a2-c93daa9fcdeb.png)|![Global Chat](https://user-images.githubusercontent.com/74851399/198937859-e285c1b5-81bd-4fd7-96c7-2b14b2272de9.png)|
---

