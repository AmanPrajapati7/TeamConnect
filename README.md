# [Model-View-ViewModel (ie MVVM)](https://github.com/ahmedeltaher/Android-MVVM-architecture)

[![kotlin](https://img.shields.io/badge/Kotlin-1.4.xxx-brightgreen.svg)](https://kotlinlang.org/)  [![coroutines](https://img.shields.io/badge/coroutines-asynchronous-red.svg)](https://kotlinlang.org/docs/reference/coroutines-overview.html)[![Junit5](https://img.shields.io/badge/Junit5-testing-yellowgreen.svg)](https://junit.org/junit5/)   [![Espresso](https://img.shields.io/badge/Espresso-testing-lightgrey.svg)](https://developer.android.com/training/testing/espresso/) [![Kotlin-Android-Extensions ](https://img.shields.io/badge/Kotlin--Android--Extensions-plugin-red.svg)](https://kotlinlang.org/docs/tutorials/android-plugin.html) [![MVVM ](https://img.shields.io/badge/Clean--Code-MVVM-brightgreen.svg)](https://github.com/googlesamples/android-architecture)  ![MVP ](https://img.shields.io/badge/Clean--Code-MVP-brightgreen.svg)
[![Build Status](https://app.bitrise.io/app/b7eabce000fac983/status.svg?token=i6oJjdA4ZD4wM6NDA5cB7g&branch=master)](https://app.bitrise.io/app/b7eabce000fac983)![MVVM3](https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)

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

| Welcome Screen | Resident Login | OTP Authentication | Scan Documents |
|:--------------:|:-------:|:-------:|:-------:|
|!(https://user-images.githubusercontent.com/61295782/190618891-2cee1e45-821d-4fe1-95ed-a61e68c042b4.jpeg)|![Resident Login Screenshot](https://i.ibb.co/GFHRCZ5/Screenshot-20211031-184201.jpg)|![WhatsApp Image 2021-10-31 at 7 14 31 PM](https://user-images.githubusercontent.com/61295782/190618962-72286da0-f66f-4f17-9e42-968667168530.jpeg)|![Scan Documents Screenshot](https://i.ibb.co/zhLxdnW/Screenshot-20211031-184449-01-01.jpg)|

| Confirm Address and Verify GPS | Edit Address Form | Face Capture | Recipt Generation|
|:--------------:|:-------:|:-------:|:-------:|
|![Confirm Address and Verify GPS Screenshot](https://i.ibb.co/9mDPtFr/Screenshot-20211031-184604-01-01.jpg)|![Edit Address Form Screenshot](https://i.ibb.co/WvfN1sv/Screenshot-20211031-184618-01.jpg)|![WhatsApp Image 2021-11-13 at 4 16 35 PM](https://user-images.githubusercontent.com/61295782/190619175-c4480dce-43d8-4106-8ef0-9b37700dfb20.jpeg)|![WhatsApp Image 2021-11-28 at 10 54 09 PM](https://user-images.githubusercontent.com/61295782/190619195-ce6aec71-4207-4f39-b97f-1ce4db70d208.jpeg)|
---

