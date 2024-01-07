package reactor.tutorial;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */

public class FluxInstances {
    public static void main(String[] args) {
        //Creating an empty Flux
        Flux<String> emptyFlux = Flux.empty();

        //Creating a Flux that contains 2 values without using a collection
        Flux<String> fooBar = Flux.just("foo", "bar");

        //Creating a Flux from a List that contains 2 values
        List<String> list = Arrays.asList("foo", "bar");
        Flux<String> fromList = Flux.fromIterable(list);

        //Creating a Flux that emits an IllegalStateException
        Flux.error(new IllegalStateException());

        //Creating a Flux that emits increasing values from 0 to 9 each 100ms
        Flux.interval(Duration.ofMillis(100)).take(10);
    }
}
