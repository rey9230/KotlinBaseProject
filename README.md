# KotlinBaseProject

A base code which I almost use in every project I work on!
It shows how to use ViewModels and Room together with Coroutines & Koin by Clean Architecture in Kotlin!
This way you don`t need to repeat the same code in different parts of your project over & over.

### Ktlint
Check&Fix Kotlin code style.

Windows : java -jar ktlint

### Gradle Kotlin DSL (Domain-specific language) benefits
* statically typed & type-safe DSL
* first-class functions, extension methods
* IDE auto-compete and content assist
* it’s easier to start doing more complicated things
* the errors are detected by the compiler
* imports added automatically

### Custom Lint Rule
Default set of lint rules is limited and in some situations we could benefit from creating custom rules that identify issues that are specific to our projects.

### Testing
![Testing](test_pyramid.png)
#So lets get more specific. In android there is 3 kind of tests:

* Unit testing: tests that validate your app’s behavior one class at a time
* Integration testing: tests that validate either interactions between levels of the stack within a module, or interactions between related modules
* End — to end tests: tests that validate user journeys spanning multiple modules of your app