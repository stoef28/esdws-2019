# Project Title

One Paragraph of project description goes here

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The project uses Angular 8, Spring Boot 2 with Java 11 and Gradle 5. Additionally, you need GIT and a Github Account to be able to use this project. <br/>
Node version 10.16.3 and NPM version 6.9.0, get it e.g. here: <a>https://nodejs.org/en/download/</a> <br />
Java: Oracle JDK 11 or OpenJDK 11, get it e.g. here: https://jdk.java.net/archive/ <br /><br />
Also, set environment variables JAVA_HOME and NODE_HOME to the respective installation location. 
<br />
on my Windows PC, they are located at <br />
```
C:\Program Files\Java\jdk-11    
C:\Program Files\nodejs
```
<br/>
also, add %JAVA_HOME%\bin and %NODE_HOME% to your path variables if you are on a Windows PC
<br />
todo: how to set it up on MAC and Linux
<br />
My current IDE is IntelliJ v2019.2.2. You can download it here: <a href="https://www.jetbrains.com/idea/download/#section=windows">IntelliJ</a>. Make sure you download the <b>Ultimate Edition</b>.
 
### Installing
The project can be found on <a href="https://github.com/ozihler/esdws-2019">Github</a>
```
1. Open IntelliJ, close any open project.
2. Select "Check out from Version Control > Git" 
3. paste the URL (e.g. https://github.com/ozihler/esdws-2019.git for HTTPS) and press "Clone". You may optionally have to type in your Github credentials here.
4. If everything went alright, Gradle will build the project.
5. IntelliJ may show a little window in the right corner, which says "Frameworks Detected - Angular CLI framework is detected". Press "Configure" and select Angular CLI > angular.json
6. Go to the Terminal, switch to the frontend folder (cd frontend), and enter npm install. This will download all the required frontend dependencies
7. In the same command, type "npm run ng serve". This will run the frontend server. Open a browser and navigate to http://localhost:4200 to see the frontend page.
8. Run "Application" from the run menu. This will run the backend Spring Boot Server at http://localhost:5000.
9. Once both frontend and backend run, you can try the library yourself.¨
10. For the course important are the backend tests. Go to backend/src/test, right-click on the folder and click "Run tests".
11. If the tests are green, you are ready for the workshop.
11.a) if gradle is used to compile and run the tests, go to File > Settings > Build, Execution, Deployment > Build Tools > Gradle, and under "Build and run", select IntelliJ for both "Build and run using: " and "Run tests using:" 
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

