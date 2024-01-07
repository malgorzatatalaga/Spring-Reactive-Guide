package reactor.project;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

public class CustomSubscribers {
    /*
    Reactor framework provides support for defining custom subscribers by extending the
    reactor.core.publisher.BaseSubscriber<T>abstract class. You don't need to implement the Subscribe interface
    of Reactive Streams specification directly. Instead, you need to just extend this class to apply the custom
    implementation as follows:
     */
    static class CustomSubscriber extends BaseSubscriber<String> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("Fetching the values ...!!");
            subscription.request(10);
        }
        @Override
        protected void hookOnNext(String value) {
            System.out.println("Fetchig next value in hookOnNext()-->"+value);
        }
        @Override
        protected void hookOnComplete() {
            System.out.println("Congratulation, Everything is completed successfully ..!!");
        }
        @Override
        protected void hookOnError(Throwable throwable) {
            System.out.println("Opps, Something went wrong ..!! "+throwable.getMessage());
        }
        @Override
        protected void hookOnCancel() {
            System.out.println("Oh !!, Operation has been cancelled ..!! ");
        }
        @Override
        protected void hookFinally(SignalType type) {
            System.out.println("Shutting down the operation, Bye ..!! "+type.name());
        }
    }
}
