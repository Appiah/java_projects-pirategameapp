# Java pirate 2D game solver interview task

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
