namespace scala com.prime.numbers.thrift

service PrimeNumbersService {
    list<i32> primeNumbersUntil(1: i32 number)

}