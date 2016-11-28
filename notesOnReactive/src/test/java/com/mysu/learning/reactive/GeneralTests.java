package com.mysu.learning.reactive;

import org.assertj.core.util.Compatibility;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

/**
 * Created by EdwinT on 16/11/2016.
 */
public class GeneralTests {
    @Test
    public void testOperators() {
        makeBasicFlow()
                .log()
                .map(String::toUpperCase)  //until here nothing happen because there are no observers for the flux
                .subscribe(); //Starts flowing
    }

    @Test
    public void testSubscribers() {
        makeBasicFlow()
                .log()
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        makeBasicFlow()
                .log().map(String::toUpperCase)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.print("Subscribed");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.print(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Finalized");
                    }
                });
    }

    @Test
    public void testBatching(){
        makeBasicFlow()
                .log()
                .map(String::toUpperCase)
                .subscribe(s -> System.out.println("Accepted: " + s), 2);
    }

    private Flux<String> makeBasicFlow() {
        return Flux.just("juan", "pedro", "maria");
    }
}
