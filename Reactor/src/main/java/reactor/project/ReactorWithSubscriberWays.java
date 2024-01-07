package reactor.project;

import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class ReactorWithSubscriberWays {
    public static void main(String[] args) {
        List<String> monthList = Arrays.asList(
                "January","February","March","April","May");

        Flux<String> months = Flux.fromIterable(monthList);
        System.out.println("1) No events is consumed.");
        months.subscribe();
        System.out.println("2) Only value event is consumed.");
        months.subscribe(month->System.out.println("->"+month));

        System.out.println("3) Value and Error (total 2) events are handled.");
        months.subscribe(month->System.out.println("-->"+month),
                e->e.printStackTrace());

        System.out.println("4) Value, Error and Completion (total 3) events are subscribed.");
        months.subscribe(month->System.out.println("--->"+month),
                e->e.printStackTrace(),
                ()->System.out.println("Finished at THIRD PLACE.. !!"));

        /* 5) Value, Error, Completion and Subscription (total 4) events are subscribed */
        months.subscribe(month->System.out.println("---->"+month),
                e->e.printStackTrace(),
                ()->System.out.println("Finished at FOURTH PLACE ..!!"),
                s -> {System.out.println("Subscribed :");
                    s.request(5L);});
    }
}
