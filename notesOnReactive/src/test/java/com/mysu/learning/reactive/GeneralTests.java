package com.mysu.learning.reactive;

import org.assertj.core.util.Compatibility;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

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
                .subscribe(GeneralTests::printOut);

        makeBasicFlow()
                .log().map(String::toUpperCase)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        printOut("Subscribed");
                    }

                    @Override
                    public void onNext(String s) {
                        printOut(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        printOut("Finalized");
                    }
                });
    }

    @Test
    public void testBatching() {
        makeBasicFlow()
                .log()
                .map(String::toUpperCase)
                .subscribe(s -> printOut("Accepted: " + s), 2);
    }

    @Test
    public void testSchedulers() throws InterruptedException {
        //one thread per flow
        makeBasicFlow()
                .log()
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.parallel())
                .subscribe(s -> printOut("Thread: " + Thread.currentThread().getId() + ", Received: " + s));

        Thread mainThread = Thread.currentThread();
        printOut("Main thread: " + mainThread.getId());

        //one thread per request
        makeBasicFlow()
                .log()
                .flatMap(value -> Mono.just(value.toUpperCase()).subscribeOn(Schedulers.parallel()))
                .subscribe(x -> printOut(x.toString()));


        mainThread.sleep(500);
    }

    private static void printOut(String x) {
        System.out.println(x);
    }

    private Flux<String> makeBasicFlow() {
        return Flux.just("juan", "pedro", "maria");
    }
}
