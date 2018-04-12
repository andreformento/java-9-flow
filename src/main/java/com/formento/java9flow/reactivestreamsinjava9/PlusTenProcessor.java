package com.formento.java9flow.reactivestreamsinjava9;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class PlusTenProcessor extends SubmissionPublisher<Integer> implements Flow.Subscriber<Integer> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        submit(item + 10);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable error) {
        error.printStackTrace();
        closeExceptionally(error);
    }

    @Override
    public void onComplete() {
        System.out.println("PlusTenProcessor completed");
        close();
    }

}
