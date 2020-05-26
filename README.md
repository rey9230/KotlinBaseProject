# KotlinBaseProject

![](https://img.shields.io/github/last-commit/i30mb1/KotlinBaseProject?color=red&style=for-the-badge)
![](https://img.shields.io/github/repo-size/i30mb1/KotlinBaseProject?color=red&style=for-the-badge)

A base code which I almost use in every project I work on!
It shows how to use ViewModels and Room together with Coroutines & Dagger by Clean Architecture in Kotlin!
This way you don`t need to repeat the same code in different parts of your project over & over.

    
### FAQ
---
#### 🤔 - What if i need to update library version?

☝️ - Before update you should read what new was added, or it can brake your functionality (for android libraries [link](https://developer.android.com/jetpack/androidx/versions/all-channel)) 

#### 🤔 - Where i can make fast and cool icon?

☝️ - [here](https://android-material-icon-generator.bitdroid.de/)

#### 🤔 - Where i can make fast and cool template for Google Market?

☝️ - [here](https://www.appstorescreenshot.com/)

#### 🤔 - Where i can make get flat badges for my github repository?

☝️ - [here](https://shields.io/)

#### 🤔 - Where can i suit up my gist code?

☝️ - [here](https://carbon.now.sh)

---

### [CLI GITHUB](https://cli.github.com/)
CLI - is an extension to command-line Git that helps you do everyday GitHub tasks without ever leaving the terminal.
[readeMore](https://medium.com/better-programming/the-official-github-cli-is-here-9fb7276e2e15)

### [Ktlint]((https://ktlint.github.io/))
We can improve Check&Fix Kotlin code style via using ktlint (it's works because file `ktlint` exist in android root directory)

Command for Windows : java -jar ktlint -F

### Gradle Kotlin DSL (Domain-specific language)
for gradle i am using [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) that provide me some benefits :
[help link](https://caster.io/lessons/gradle-dependency-management-using-kotlin-and-buildsrc-for-buildgradle-autocomplete-in-android-studio)
* statically typed & type-safe DSL
* first-class functions, extension methods
* IDE auto-compete and content assist
* it’s easier to start doing more complicated things
* the errors are detected by the compiler
* imports added automatically

### Custom Lint Rule
Default set of lint rules is limited and in some situations we could benefit from creating custom rules that identify issues that are specific to our projects. [Help link](https://www.youtube.com/watch?v=jCmJWOkjbM0)

### Testing
<details><summary>groups</summary><p>

![Testing](test_pyramid.png)
</p></details>

If we want to mock (with library `mockitokotlin2`) final/private classes (when we writing unit tests) we should add special [file](https://github.com/mockito/mockito/wiki/What's-new-in-Mockito-2#mock-the-unmockable-opt-in-mocking-of-final-classesmethods) to our project 

### Dagger 2 with ViewModel that have SavedState

i'm using the best DI scenario for ViewModel that i have ever seen in my life (took that from this [video](https://youtu.be/9fn5s8_CYJI?list=LLMBNl1baSJfDak1Lo2VVVZQ))
 and slightly modifying by adding [AssistedInjection](https://github.com/square/AssistedInject) 
 for supporting SavedStateModule (When providing a custom ViewModelProvider.Factory instance, 
 you can enable usage of SavedStateHandle by extending AbstractSavedStateViewModelFactory)

### Architecture
<details><summary>cool picture</summary><p>

![arch](architecture.png)</p></details>

- **Fragment/Activity/View** 
  - >Request data from viewModel
  - >Observe viewModel LiveData for response
 
- **Adapters/Interactor/UseCase/Domain**
  - > Interactor - object that manage group similar UseCases
  - > UseCase - one action that can be performed

### About Theme

* [Material Components](https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md#4-change-your-app-theme-to-inherit-from-a-material-components-theme)
* [Text Sizes](https://material.io/develop/android/theming/typography/)
* [MaterialAlertDialog](https://github.com/material-components/material-components-android/blob/master/docs/components/Dialog.md)
* [Tinting](https://github.com/android/graphics-samples)
* for every standard component exist special attribute in theme so we can override it in our theme for personalization 
<details><summary>attributes&example</summary><p>

![componentsStyle](component_styles.png) 
![code](component_style_in_action.png)</p></details>
<details><summary>about elevation</summary><p>

![code](elevation.png)</p></details>

* each view component belongs to a specific group 
    * ```shapeAppearanceSmallComponent```
    * ```shapeAppearanceMediumComponent```
    * ```shapeAppearanceLargeComponent```
<details><summary>groups</summary><p>

![shape group](shape_appearance_component.png)</p></details>

we can build our own shape appearance by following these [attributes](https://material.io/develop/android/theming/shape/)
* if we want to change theme from light to night 
    * ```AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)``` - For light Theme
    * ```AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)``` - For dark Theme
    * ```AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)```
    * ```AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)```

### LINKS

* [Android Unique ID](https://proandroiddev.com/how-to-generate-android-unique-id-38362794e1a8)
* git help [(rus)](https://github.com/nicothin/web-development/tree/master/git)
* git help2 [(rus)](https://github.com/k88hudson/git-flight-rules/blob/master/README_ru.md#%D0%A0%D0%B5%D0%BF%D0%BE%D0%B7%D0%B8%D1%82%D0%BE%D1%80%D0%B8%D0%B8) [(eng)](https://github.com/k88hudson/git-flight-rules/blob/master/README.md)
<details><summary>vim cheatSheet</summary><p>

![code](vim.png)</p></details>
* reduce complexity for svg [svgomg](https://jakearchibald.github.io/svgomg/) 
* [VD (Vector Drawable)](https://www.youtube.com/watch?v=fgbl34me3kk)
* Animations 
    * [shared element animation](https://medium.com/redmadrobot-mobile/hidden-mistakes-with-shared-element-transitions-65d79831c63)
    * [rotation animation](https://blog.usejournal.com/animate-image-rotation-like-a-pro-c08d2bd986c6)
    * [suspeding animations](https://medium.com/androiddevelopers/suspending-over-views-19de9ebd7020)
    * [animation pro level](https://proandroiddev.com/complex-ui-animation-on-android-8f7a46f4aec4?gi=549fab234e8c)
    * [wtf](https://medium.com/androiddevelopers/playing-with-paths-3fbc679a6f77)
    * [TransitionAnimation](https://medium.com/@andkulikov/animate-all-the-things-transitions-in-android-914af5477d50)
* Reverse engineering 
    * [main tools](https://www.andreafortuna.org/2019/07/18/reverse-engineering-and-penetration-testing-on-android-apps-my-own-list-of-tools/)