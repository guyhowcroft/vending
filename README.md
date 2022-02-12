# Vending Machine

## Overview
This project emulates the coin function of a vending machine. 

Accepted coins are set in the applicaiton properties and are denominated in pence: 1,2,5,10,20,50,100,200

The vending machine has a vending float which tracks the coins in the machine and a total float which is the total of the change in the machine.

Using the REST endpoints a user can initialise the vending machine to a given float, register more coins, and request change.


## Running the project

There are several ways to run the service

#### Running through IDE:

- Right click on the VendingApplication class and choose to run or debug


#### Using maven to run the service from the cmd line:

Go to project's root directory.

```bash
mvn spring-boot:run
``` 

#### Build a jar and run that:

In the project root directory run "mvn package"

This will generate a jar in the target directory called "vending-0.0.1-SNAPSHOT.jar"

Run the following command:

```bash
java -jar target/vending-0.0.1-SNAPSHOT.jar
``` 
## How to use

With the service running send a PUT request to:

Initialise - initialise vending machine to a known state. Set up the initial float by sending a integer list as the body of the request:

E.g - [20,20,20,20,10,5,2]
```bash
http://localhost:8080/initialise
``` 

Register - Register coins that have been deposited by user. Sending a integer list as the body of the request:

E.g - [5,2,2]
```bash
http://localhost:8080/register
``` 
Change - Produce a list of coins as change. Send an Integer in the path:

E.g - http://localhost:8080/change/60
```bash
http://localhost:8080/change/{Enter change amount}
``` 

## Test Harness
To test the main function, calculate change, add more tests to the ChangeServiceIMplTest.java file in the testParameters and run the tests.

E.g

```bash
{
    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
        put(100, 1);
        put(50, 1);
        put(20, 5);
        put(5, 0);
        put(2, 100);
    }},
    76, Optional.of(Arrays.asList(50,20,2,2,2))
}
               
```
- 'put' the desired coins in to the float (making sure they are in numerical order descending)

- set the amount of change that is required

- Set the List of expected coins - Optional.of(Arrays.asList(50,20,2,2,2))








