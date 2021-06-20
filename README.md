# Vita

[![Maven Central](https://img.shields.io/maven-central/v/com.androidisland.arch/vita.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.androidisland.arch%22%20AND%20a:%22vita%22)
[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)](http://www.apache.org/licenses/LICENSE-2.0)


 <p align="center">
  <img width="600" src="images/vita_typo.png">
</p>


**Vita** is a light and simple library that helps you to share ViewModel between fragments and even activities! Also you can create ViewModels that are available all over the application! Cool, right!?

As we know we need a LifeCycleOwner (e.g Fragment or FragmentActivity) to create ViewModels, when the owner is at the end of its lifecycle the ViewModel will be cleared as well, Sometimes you need to share the ViewModel between multiple owners, By default we can only share ViewModel of an activity between its fragments for now, nothing more...

## What Vita does:
- Creates ViewModels with **Single Owner**:
 This is the default ViewModel behavior that already has, The ViewModels created in this way are only available to the owner.
 
 <br/>
 <p align="center">
  <img src="images/single_owner_diagram.png">
</p>

- Creates ViewModels with **Multiple Owners**:
 The ViewModels are shared between multiple owners and stay alive while at least one owner is alive
 
 <br/>
 <p align="center">
  <img src="images/multiple_owner_diagram.png">
</p>

- Creates ViewModels with **No Owner**:
 The ViewModels have no owner, they are available in the application scope and stay alive until the user closes the application
 
 <br/>
 <p align="center">
  <img src="images/no_owner_diagram.png">
</p>

## Gradle setup

Make sure your project includes jcenter in its repositories and add this to build.gradle in app module
  

```gradle
dependencies {
    implementation("com.androidisland.arch:vita:$latest_version")
}
```

## How to use

First start **Vita** in application class:

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startVita()
    }
}
```

There is an extension value named **vita** that gives you access to a singleton object of Vita everywhere, Just pass your desired VitaOwner and get the ViewModel you want:

```kotlin
val myViewModel = vita.with(VitaOwner.Multiple(this)).getViewModel<MyViewModel>()
```

Also you can pass a function as factory like this:

```kotlin
val myViewModelWithFactory = vita.with(VitaOwner.Multiple(this)).getViewModel() {
    MyViewModelWithFactory(initData)
}
```

### ⭐️ Don't forget to give it a star if you liked it!

## License

    Copyright 2019 Farshad Tahmasbi
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.    
