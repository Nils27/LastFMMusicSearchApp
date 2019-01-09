[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)



# Last Fm - Artist Search App

An App to allow Users to search for Artists and see their bios.


Future Updates:

The json data data could benefit from Paging Library (https://developer.android.com/reference/android/arch/paging/DataSource)

An error screen or snackbox will be used to notify users of various errors/warnings (if they have no internet connection, search was not valid, etc)

Many more Unit tests will be added to help fish out potential issues for users and ensure the various classes all work as expected

A cleaner user interface, with animations.

Remake this in Kotlin


## Set Up - API_KEY needed for this app to work

* 1 - Open this project in Android Studio
* 2 - Sign up on the LastFM webiste - https://www.last.fm/home
* 3 - Register for your Api Key - https://www.last.fm/api/account/create
* 4 - In Gradle.Properties (Project Poperties) add the key with this line: LastFM_APIKEY="YOUR API KEY"
* 5 - The app should now work


## Libraries Used

* DataBinding 
* Glide
* ViewModel
* LiveData
* RecylerView
* Retrofit



## License

Copyright (C) 2018 Nilesh Patel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.