package reactor.project;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class ReactorLifecycleMethods {
    /*
    We are creating the Flux object with data from a list and then calling various lifecycle methods,
    like doOnComplete(), doOnNext(), doOnSubscribe(), doOnError(), and doOnTerminate() in a chain. Finally,
    we call the subscribe() method, which does not consume the events, but all lifecycle methods will be executed
    as appropriate events are triggered.
     */
    public static void main(String[] args) {
        List<String> designationList = Arrays.asList(
                "Jr Consultant", "Associate Consultant", "Consultant",
                "Sr Consultant", "Principal Consultant");
        Flux<String> designationFlux = Flux.fromIterable(designationList);

        designationFlux.doOnComplete( //Once all the data is received by the Subscriber, this method will be called
                        () -> System.out.println("Operation Completed ..!!"))
                .doOnNext( //This method will listen to the value event coming from the producer
                        value -> System.out.println("value in onNext() ->" + value))
                .doOnSubscribe(subscription -> {
                    /*
                    Used to plug Subscription.
                    It can control the backpressure by defining how many more elements are required with
                    a subscription.request() call.
                     */
                    System.out.println("Fetching the values ...!!");
                    for (int index = 0; index < 6; index++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subscription.request(1);
                    }
                })
                .doOnError( //If any errors occur, this method will be executed
                        throwable -> {
                            System.out.println("Opps, Something went wrong ..!! "
                                    + throwable.getMessage());
                        })
                .doFinally(
                        /*
                        This will be called on stream closures due to error, cancellation,
                        or successful completion of events
                         */
                        (signalType ->
                                System.out.println("Shutting down the operation, Bye ..!! "
                                        + signalType.name())))
                .subscribe();
    }
}
