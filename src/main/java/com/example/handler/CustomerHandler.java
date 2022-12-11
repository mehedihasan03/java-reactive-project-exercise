package com.example.handler;

import com.example.dao.CustomerDao;
import com.example.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao dao;

    public Mono<ServerResponse> loadAllCustomers(ServerRequest request) {
        Flux<Customer> customerList = dao.getCustomersForHandler();
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request) {
        int customerId = Integer.valueOf(request.pathVariable("input"));
        Mono<Customer> monoCustomer = dao.getCustomersForHandler()
//                .filter(customer -> customer.getId() == customerId).take(1).single();
                .filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok()
                .body(monoCustomer, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){

        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + " : " + dto.getName());
        return ServerResponse.ok()
                .body(saveResponse, String.class);

    }
}
