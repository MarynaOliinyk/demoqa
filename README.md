**Install, configure and run Selenoid**
-
https://aerokube.com/selenoid/latest/

Set Selenoid parameters in root pom.xml

**Install Lombok plugin**
-
https://projectlombok.org/setup/intellij

**Install Allure report tool**
-
https://docs.qameta.io/allure/

**Project settings**
-
Set 'java-gen' folder (api-tests > target > java-gen) as a source (in Project Structure > Modules)

**Capabilities**
-
All browser capabilities can be configured in root pom.xml

**Run tests**
-
Change parameters in root pom.xml and run

**mvn clean test**

or pass them

**mvn -Dskip-module=(api-tests) -Dapp= -Duser= -Dpwd= -Dcode= -Dsecret= -Dsuite= -DparallelTesting=false -Dbrowser=chrome clean test**

For instance:

Note: skip api-tests module if you want to run only GUI tests

**Useful links**
-
Selenide

https://selenide.gitbooks.io/user-guide/content/en/

AssertJ

http://www.vogella.com/tutorials/AssertJ/article.html
http://www.baeldung.com/introduction-to-assertj

Lombok

http://www.baeldung.com/intro-to-project-lombok


Retrofit

https://square.github.io/retrofit/


