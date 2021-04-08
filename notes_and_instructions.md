#STAGE 1 : Development ->

Building the App :
1. make sure micronaut is installed

2. Lombok may be required to reduce boilerplate code

3. After application code is successfully written and tested, the next step is to containerize the solution



#STAGE 1B : Containerisation ->

4. In the terminal issue these commands :

    $ ./gradlew assemble

    Then make sure you create the file : "Dockerfile" at the root of the project.

    Then issue this command :

    $ docker build -t pirategameapp .

        the above command may take a while in the case of first time.

        Sample Response : {

          Emmanuels-MacBook-Pro:PirateGameApp emap$ docker build -t pirategameapp .
          Sending build context to Docker daemon  84.34MB
          Step 1/4 : FROM openjdk:8-jdk-alpine
          8-jdk-alpine: Pulling from library/openjdk
          e7c96db7181b: Pull complete
          f910a506b6cb: Pull complete
          c2274a1a0e27: Pull complete
          Digest: sha256:94792824df2df33402f201713f932b58cb9de94a0cd524164a0f2283343547b3
          Status: Downloaded newer image for openjdk:8-jdk-alpine
           ---> a3562aa0b991
          Step 2/4 : VOLUME /tmp
           ---> Running in 8b7923772252
          Removing intermediate container 8b7923772252
           ---> 92407cfc376a
          Step 3/4 : ADD build/libs/PirateGameApp-0.1.jar app.jar
           ---> 100ed981a991
          Step 4/4 : ENTRYPOINT ["java","-jar","/app.jar"]
           ---> Running in 39d5a26dfbfe
          Removing intermediate container 39d5a26dfbfe
           ---> 1bc28ef8a6cf
          Successfully built 1bc28ef8a6cf
          Successfully tagged pirategameapp:latest


        }


    After a successful docker building process and downloading of all files needed,
    we can then issue this command :


    $ docker run -p8001:8001 pirategameapp

     When the command below was issued we had the error that follows it and next is how it 
     was resolved : 
     
      #COMMAND : 
           $ docker run pirategameapp
           
      #ERROR : 
            Exception in thread "main" java.lang.NoClassDefFoundError: io/micronaut/runtime/Micronaut
                    at PirateGameApp.Application.main(Application.java:8)
            Caused by: java.lang.ClassNotFoundException: io.micronaut.runtime.Micronaut
                    at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
                    at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
                    at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
                    at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
                    ... 1 more

      #RESOLVING / SOLUTION : 
            The solution was basically to review all classes, folders, dependencies and
            manifest files to make sure everything was in it rightful place.
            
            After a careful review of the  "/build/libs" folder and the jar files that
            were successfully generated from the "./gradlew assemble" command which was
            then followed by the "docker build -t pirategameapp ." command; I had wrongfully
            linked to the inappropriate file.
            Futher investigations into the "/build/libs" folder showed that there were 
            three jar files and two of them weighed less than 32KB and one weighed more than
            13MB. It became obvious fast that I had linked to one of the 31KB files.

            The issue was fixed by doing the lines below in the "Dockerfile"
            
                FROM openjdk:8-jdk-alpine
                VOLUME /tmp
                ADD build/libs/PirateGameApp-0.1-all.jar app.jar
                ENTRYPOINT ["java","-jar","/app.jar"]
            
            as compared to this below ; prior : 
            
                FROM openjdk:8-jdk-alpine
                VOLUME /tmp
                ADD build/libs/PirateGameApp-0.1-runner.jar app.jar
                ENTRYPOINT ["java","-jar","/app.jar"]
                
                OR : 
                
                FROM openjdk:8-jdk-alpine
                VOLUME /tmp
                ADD build/libs/PirateGameApp-0.1.jar app.jar
                ENTRYPOINT ["java","-jar","/app.jar"]

      you can check on docker images by using the command :

        $ docker image ls


      The command below would show all containers running active or otherwise;
        $ docker ps -a   


      We can use the command :

      $ docker container stop {containerID}


      $ docker container start {containerID}




#STAGE 2 : Running + Testing

Instructions on how to run :

1. Confirm that port number setted in the application.yml file is available


2. navigate to the root of the projects folder in (a) terminal

3. Issue this command in the terminal
./gradlew run
