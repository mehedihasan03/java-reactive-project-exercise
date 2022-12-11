package com.example.dao;

import com.example.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("Processing count : " + i))
                .mapToObj(i -> new Customer(i, "customer " + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing count in Stream : " + i))
                .map(i -> new Customer(i, "Customer " + i));
    }

    public Flux<Customer> getCustomersForHandler() {
        return Flux.range(1,50)
                .doOnNext(i -> System.out.println("Processing count in Stream : " + i))
                .map(i -> new Customer(i, "Customer " + i));
    }

    private static void sleepExecution(int i){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
