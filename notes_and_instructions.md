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
