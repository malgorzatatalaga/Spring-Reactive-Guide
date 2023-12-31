package main.java;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ////static stream of elements
        Flux<Integer> justFlux = Flux.just(1, 2, 3, 4);
        Mono<Integer> justMono = Mono.just(1);

        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(elements::add);
        //In a reactive approach, events are pushed to the subscribers as they come in.

        //Backpressure
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(new Subscriber<>() {
                    private Subscription s;
                    int onNextAmount;
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }
                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }
                    @Override
                    public void onError(Throwable t) {}
                    @Override
                    public void onComplete() {}
                });

        //Mapping Data in a Stream
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .subscribe(elements::add);
        System.out.println(elements);


    }
}