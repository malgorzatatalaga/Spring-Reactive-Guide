import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Flux<Integer> justFlux = Flux.just(1, 2, 3, 4); //static stream of four elements
        Mono<Integer> justMono = Mono.just(1);

        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(elements::add);
    }
}