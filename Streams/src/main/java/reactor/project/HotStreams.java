package reactor.project;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotStreams {
    public static void main(String[] args) {
        //Creating a ConnectableFlux
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    while(true) {
                        fluxSink.next(System.currentTimeMillis());
                    }
                })
                .publish();
        publish.subscribe(System.out::println);
        publish.connect();
    }
}
