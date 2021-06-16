#Prime Numbers

This small project involves two service that work together to compute all the prime numbers up to a given natural positive number.
The techonologies that integrate this project are:
- Scala - main language;
- Finatra - HTTP server and API definition;
- Finagle - RPC system;
- Thrift -  Interface definition language for services;

As a disclaimer, I can say that I haven't used any of these technologies before and I have only some Scala experience. 
Given that, I tried to keep it simple. 

## Proxy Service

The Proxy service exposes an HTTP endpoint that given N, a positive natural number, returns a list of all the prime numbers until that given number.
The responsibility of this service is to receive, parse, validate the input and return the answer from a second service that performs the prime numbers computation.

To accomplish that, I use [Finatra](https://twitter.github.io/finatra/) a services framework. 
The main reason behind choosing this framework was the ease of use, and the velocity in which you can have an API up and running. 
Also, this framework uses Finagle under the hood, and since I use it in the Prime Number Service I thought it would be easier to get familiarised with all these pieces.

The flow of this service starts in the `ProxyServer` class that configures the HTTP server that we want to spin up. 
There we configure modules that the controllers might use and also declare which controller are exposed. 
Additionally, we configure the `BadInputExceptionMapper` that maps all `CaseClassFieldMappingException` exceptions (these are thrown when the input is invalid)
to a 400 Bad Request response.

In this server we have only one controller, the `PrimeNumberController`. 
This controller exposes a GET endpoint as such `/prime/:number`, and does an RPC to a `PrimeNumbersService` to obtain the list of prime numbers.
Depending on that service's answer the controller can return the resulting primes numbers list, or a 500 Internal Server Error.
This last case can be triggered by any problem related with the `PrimeNumbersService` communication and its inception is a `ServiceFailureException`.

### How to run

To spin up this server you have to run:
```
sbt "runMain proxy.ProxyServerMain"
```

The endpoint will be available at `localhost:8888/prime/:number`.

## Prime Number Service

The Prime Number Service has the sole function of exposing the actual `PrimeNumbersService`, which exposes `primeNumbersUntil` the function. 
Overall, it receives RPCs and executes the mentioned function to get the primes numbers up to a given number.

To implement this service I decided to use [Finagle](https://twitter.github.io/finagle/) since it seemed a fairly used system 
and so it would be easier to find answers to possible problems. Also, it is written Scala and its interoperability with the language seem to be an asset.
The IDL chosen to define the service interface was [Thrift](https://thrift.apache.org/), mainly because it was well integrated with Finagle.

This service has two components, the `PrimeNumbersServer` and the `PrimeNumbersServiceImpl`.
The `PrimeNumbersServer` starts up a Thrift server based on the `PrimeNumbersServiceImpl`. 
The `PrimeNumbersServiceImpl`, as the name, implies implements the `PrimeNumbersService` with the actual prime numbers computation algorithm.
I chose the [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) algorithm because it has a better performance, 
time complexity, than the more direct algorithm that iterates to all the numbers and test them until specified input number.

### How to run

To spin up this service you have to run:
```
sbt "runMain prime.numbers.PrimeNumbersServer"
```

The service will be available at `localhost:8080`.

## To Improve

There are some things that could be improved in this project, besides the more common add more tests, 
maybe improve configurability and better error handling.

### Unnecessary computation for repetitive requests

The Prime Number Service will compute the numbers each time that it is called. This is not sustainable. 
The service can receive multiple requests for the same number, and it will fully calculate the same result each time.
Given this, a possible way to improve this would be to implement a cache strategy that maybe could be employed at the entrance of the service.
This would save computation time and improve the service availability.
Something to investigate could be the feasibility of using Finagle's Filters for that.

### Connection between Proxy and Prime Number Service

Currently, the `ClientsFactory`'s `primeNumbersServiceClient` method returns a connection to the Prime Number Service 
and to be used by the `PrimeNumberController`. The service address can be configured through an environmental variable, 
and its value defaults to localhost.
However, in a multiple service context it could be a good idea to have a better service discovery strategy.
For what I could tell Zookeeper could help with that and Finagle seems to integrate well with it.
