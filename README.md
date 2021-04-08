## Micronaut 2.3.0 Documentation

- [User Guide](https://docs.micronaut.io/2.3.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.3.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.3.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

#STAGE 1 : Development ->

Building the App :
1. make sure micronaut is installed

2. Lombok may be required to reduce boilerplate code

3. After application code is successfully written and tested, the next step is to containerize the solution



#STAGE 1B : Containerisation & Testing using Docker ->

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


    $ docker run -p48901:48901 pirategameapp


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

On a successful start, you should see this in your terminal or cmd : 

$ ./gradlew run

> Task :run
 __  __ _                                  _   
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_ 
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_ 
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
  Micronaut (v2.3.0)

16:08:17.622 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 7349ms. Server Running: http://localhost:48901
<=========----> 75% EXECUTING [2m 12s]


##REQUEST SAMPLE -> POST

 {"game_map" : [
    [{"type": "coin", "amount": 10}, 
    {"type": "coin", "amount": 11}, 
    {"type": "coin", "amount": 21}, 
    {"type": "coin", "amount": 0}],

    [{"type": "coin", "amount": 9}, 
    {"type": "coin", "amount": 8}, 
    {"type": "rock"}, 
    {"type": "coin", "amount": 99}],

    [{"type": "coin", "amount": 6}, 
    {"type": "bomb"}, 
    {"type": "rock"}, 
    {"type": "rock"}],

    [{"type": "coin", "amount": 0}, 
    {"type": "coin", "amount": 9}, 
    {"type": "coin", "amount": 9}, 
    {"type": "coin", "amount": 9}]
]}



##RESPONSE SAMPLE -> GET

{
    "path": [
        [
            0,
            1
        ],
        [
            0,
            2
        ],
        [
            0,
            3
        ],
        [
            1,
            3
        ],
        [
            2,
            3
        ],
        [
            3,
            3
        ]
    ],
    "coins": 57
}



##TERMINAL LOG SAMPLES : 

16:21:40.661 [default-nioEventLoopGroup-1-3] INFO  P.controller.PathFinderController - Parameters : 
startXPosition : 0, startYPosition : 1, targetXPosition : 3, targetYPosition : 3
16:21:40.663 [default-nioEventLoopGroup-1-3] INFO  PirateGameApp.service.FindPiratePath - Given Targets from : { 0,1 } to : { 3,3 }
16:21:40.663 [default-nioEventLoopGroup-1-3] INFO  PirateGameApp.service.FindPiratePath - Move from array position : [2, 0] to : [0, 3]
16:21:40.664 [default-nioEventLoopGroup-1-3] WARN  PirateGameApp.service.FindPiratePath - Please submit a map via the POST endpoint.
16:21:47.829 [default-nioEventLoopGroup-1-3] INFO  P.controller.PathMapController - The JSON Data Passed to .../map : GameMapRequestDto(game_map_typeAndAmountList=[[GameMapRequestDto.TypeAndAmount(type=coin, amount=10), GameMapRequestDto.TypeAndAmount(type=coin, amount=11), GameMapRequestDto.TypeAndAmount(type=coin, amount=21), GameMapRequestDto.TypeAndAmount(type=coin, amount=0)], [GameMapRequestDto.TypeAndAmount(type=coin, amount=9), GameMapRequestDto.TypeAndAmount(type=coin, amount=8), GameMapRequestDto.TypeAndAmount(type=rock, amount=0), GameMapRequestDto.TypeAndAmount(type=coin, amount=99)], [GameMapRequestDto.TypeAndAmount(type=coin, amount=6), GameMapRequestDto.TypeAndAmount(type=bomb, amount=0), GameMapRequestDto.TypeAndAmount(type=rock, amount=0), GameMapRequestDto.TypeAndAmount(type=rock, amount=0)], [GameMapRequestDto.TypeAndAmount(type=coin, amount=0), GameMapRequestDto.TypeAndAmount(type=coin, amount=9), GameMapRequestDto.TypeAndAmount(type=coin, amount=9), GameMapRequestDto.TypeAndAmount(type=coin, amount=9)]], typeAndAmount=null)
16:21:47.830 [default-nioEventLoopGroup-1-3] INFO  PirateGameApp.service.SaveGameMap - GameMapRequestDto : GameMapRequestDto(game_map_typeAndAmountList=[[GameMapRequestDto.TypeAndAmount(type=coin, amount=10), GameMapRequestDto.TypeAndAmount(type=coin, amount=11), GameMapRequestDto.TypeAndAmount(type=coin, amount=21), GameMapRequestDto.TypeAndAmount(type=coin, amount=0)], [GameMapRequestDto.TypeAndAmount(type=coin, amount=9), GameMapRequestDto.TypeAndAmount(type=coin, amount=8), GameMapRequestDto.TypeAndAmount(type=rock, amount=0), GameMapRequestDto.TypeAndAmount(type=coin, amount=99)], [GameMapRequestDto.TypeAndAmount(type=coin, amount=6), GameMapRequestDto.TypeAndAmount(type=bomb, amount=0), GameMapRequestDto.TypeAndAmount(type=rock, amount=0), GameMapRequestDto.TypeAndAmount(type=rock, amount=0)], [GameMapRequestDto.TypeAndAmount(type=coin, amount=0), GameMapRequestDto.TypeAndAmount(type=coin, amount=9), GameMapRequestDto.TypeAndAmount(type=coin, amount=9), GameMapRequestDto.TypeAndAmount(type=coin, amount=9)]], typeAndAmount=null)
16:21:47.831 [default-nioEventLoopGroup-1-3] INFO  PirateGameApp.service.SaveGameMap - The first -> type : coin, amount : 10
10, 11, 21, 0
9, 8, 0, 99
6, 0, 0, 0
0, 9, 9, 9
Live array for action : [[10, 11, 21, 0], [9, 8, 0, 99], [6, 0, 0, 0], [0, 9, 9, 9]]
16:21:47.835 [default-nioEventLoopGroup-1-3] INFO  PirateGameApp.service.SaveGameMap - loadCoinsAndDoGridDisplay : [[I@1a98d71b, [I@4a8373a2, [I@495c77b0, [I@19626c12]
16:21:53.719 [default-nioEventLoopGroup-1-3] INFO  P.controller.PathFinderController - Parameters : 
startXPosition : 0, startYPosition : 1, targetXPosition : 3, targetYPosition : 3
16:21:53.720 [default-nioEventLoopGroup-1-3] INFO  PirateGameApp.service.FindPiratePath - Given Targets from : { 0,1 } to : { 3,3 }
16:21:53.720 [default-nioEventLoopGroup-1-3] INFO  PirateGameApp.service.FindPiratePath - Move from array position : [2, 0] to : [0, 3]
path X : [[2, 0]]
ok to move up
ok to move right
move ^ Up
path Up : [[2, 0], [1, 0]]
ok to move up
ok to move right
move ^ Up
path Up : [[2, 0], [1, 0], [0, 0]]
ok to move right
move > Right
path R : [[2, 0], [1, 0], [0, 0], [0, 1]]
ok to move right
move > Right
path R : [[2, 0], [1, 0], [0, 0], [0, 1], [0, 2]]
ok to move right
path R* : [[2, 0], [1, 0], [0, 0], [0, 1], [0, 2], [0, 3]]
Travelling from : [2, 0], to : [0, 3]
Path travelled : [[2, 0], [1, 0], [0, 0], [0, 1], [0, 2], [0, 3]]
Game Path travelled : [[0, 1], [0, 2], [0, 3], [1, 3], [2, 3], [3, 3]]
Total Path Value : 57
<=========----> 75% EXECUTING [21m 19s]
> :run






# Java pirate 2D game solver task

### Motivation:

We would like you to implement a solver for a simple 2D game to help a pirate to get to a target destination collecting as much treasure as possible.

The inputs are:
 - 2 dimensional map
 - pirate start position (x, y)
 - destination position pirate should get to

Given
 - the piratе can move only up and right
 - the amount of treasure he collects is the sum of coins all the way pirate get to the destination point

Expected output is an either an object representing a path and an amount of treasure pirate has collected or an error denoting that the target position is out of reach.

Step 1. A map consists of only coins where each point can contain specific amount.

Step 2. Assume we need to adapt this solver to an updated game where new items have been introduced. So instead of coins the map can make up of rocks which pirate can't get accross and the bombs which pirate can use to blew up rocks on his way.

### Task description:

Requirements
 - Candidates should use Java programming language
 - Code should be accompanied with a docker file which can be used to run/test the solution

Delivery:
 1. We expect candidates to provide us with a github link to a repository which has a guidence of how to run and test the code in a docker container.
 2. Application run on a container should expose a REST API with methods to
  - load the map
  - calculate pirate's path

## Some test cases

### Example when pirate collects 57 coins (12, 13, 14, 11, 7)

![Example1](/example_1.png?raw=true)


##### Load the map
 `POST /map`

 ```json
 [
    [{"type": "coin", "amount": 1}, {"type": "coin", "amount": 2}, {"type": "coin", "amount": 3}, {"type": "coin", "amount": 0}],
    [{"type": "coin", "amount": 4}, {"type": "coin", "amount": 5}, {"type": "coin", "amount": 6}, {"type": "coin", "amount": 7}],
    [{"type": "coin", "amount": 8}, {"type": "coin", "amount": 9}, {"type": "coin", "amount": 10}, {"type": "coin", "amount": 11}],
    [{"type": "coin", "amount": 0}, {"type": "coin", "amount": 12}, {"type": "coin", "amount": 13}, {"type": "coin", "amount": 14}]
]
 ```

##### Run solver assuming pirate is on the left bottom corner of the map (X:0, Y:0) and destination point is the right upper corner (X:3, Y:3)

`GET /findPath?=startXPosition=0&startYPosition=0&targetXPosition=3&targetYPosition=3`

HTTP Responce Ok

```
{"path": [[0, 0], [1, 0], [2, 0], [3, 0], [3, 1], [3, 2], [3, 3]], "coins": 57}
```


### Example when pirate can't reach the destination point

![Example2](/example-2.png?raw=true)

 `POST /map`

 ```json
 [
    [{"type": "coin", "amount": 1}, {"type": "coin", "amount": 2}, {"type": "coin", "amount": 3}, {"type": "coin", "amount": 4}],
    [{"type": "coin", "amount": 5}, {"type": "coin", "amount": 6}, {"type": "coin", "amount": 7}, {"type": "coin", "amount": 8}],
    [{"type": "coin", "amount": 9}, {"type": "coin", "amount": 10}, {"type": "coin", "amount": 11}, {"type": "coin", "amount": 12}],
    [{"type": "coin", "amount": 13}, {"type": "coin", "amount": 14}, {"type": "coin", "amount": 15}, {"type": "coin", "amount": 16}]
 ]
 ```

  `GET /findPath?=startXPosition=2&startYPosition=3&targetXPosition=0&targetYPosition=1`
  `HTTP Responce NotFound`


### Example when pirate collects 116 coins (9 + 8 + 99) (has to blew up 1 rock on his way)

![Example3](/example-3.png?raw=true)

 `POST /map`

 ```json
 [
 [{"type": "coin", "amount": 10}, {"type": "coin", "amount": 11}, {"type": "coin", "amount": 21}, {"type": "coin", "amount": 0}],
    [{"type": "coin", "amount": 9}, {"type": "coin", "amount": 8}, {"type": "rock"}, {"type": "coin", "amount": 99}],
    [{"type": "coin", "amount": 6}, {"type": "bomb"}, {"type": "rock"}, {"type": "rock"}],
    [{"type": "coin", "amount": 0}, {"type": "coin", "amount": 9}, {"type": "coin", "amount": 9}, {"type": "coin", "amount": 9}]
 ]
 ```

`GET /findPath?=startXPosition=0&startYPosition=0&targetXPosition=3&targetYPosition=3`

HTTP Responce Ok

```
{"path": [[0, 0], [1, 0], [1, 1], [1, 2], [2, 2], [3, 2], [3, 3]], "coins": 116}
```
