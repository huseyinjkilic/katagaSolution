This project solution for kata19 word chain problem.
http://codekata.com/kata/kata19-word-chains/
Java 8 and spring boot is used in this solution. Rest web service is written for user input. 
It takes two argument. Initial word and final word.

Image below show argument usage.

![Alt text](https://raw.githubusercontent.com/huseyinjkilic/katagaSolution/master/screenshots/catToDog.png "Cat to dog rest example")

There are two possible solution for this problem breadth-first search and dijkstra's algorithm.
Breadth-first easier for me so I choose that one.

5 test case written with Spring mockMVC.
Source code under src/test/java/katangaSolution

Runnable jar file created. It is under this directory => output files/kataga_solution-0.1.0.jar

Here is a screenshot maven compile & test case results
![Alt text](https://raw.githubusercontent.com/huseyinjkilic/katagaSolution/master/screenshots/kataga%20maven%20build%20screen.png "Maven compile resutl")
There is also console output file under this directory => output files/outputOfMavenPackage.txt

For run jar file you should have java 8 in your computer.

Here is screenshot for example of usage jar file. There is a high resolution version of screenshot if you click the image.
![Alt text](https://raw.githubusercontent.com/huseyinjkilic/katagaSolution/master/screenshots/Runing%20jar%20file.png "Runnable jar usage")

It should be 10 second to initialize tomcat server.

Here is second example of usage for ruby to code 

![Alt text](https://raw.githubusercontent.com/huseyinjkilic/katagaSolution/master/screenshots/rubyToCode.png "Ruby to code rest example")
