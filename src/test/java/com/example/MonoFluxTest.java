package com.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> monoString = Mono.just("Mehedi Hasan")
                .then(Mono.error(new RuntimeException(" An error happened")))
                .log();
        monoString.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxStr = Flux.just("Spring", "Boot", "Reactive", "Mehedi")
                .concatWithValues("Hasan")
                .concatWith(Flux.error(new RuntimeException("An error happened on Flux")))
                .concatWithValues("cloud")
                .log();
        fluxStr.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }
}
