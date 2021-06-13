namespace scala com.prime.numbers.thrift

service PrimeNumbersService {
    list<i64> primeNumbersUntil(1: i64 number)

}