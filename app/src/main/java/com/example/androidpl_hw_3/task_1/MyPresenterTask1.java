package com.example.androidpl_hw_3.task_1;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class MyPresenterTask1 {

    private static final String TAG = "MyPresenterTask_1";

    Observable<Integer> startThread() {

        Observable<Integer> observable = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            try {
                for (int i = 0; i < 6; i++) {
                    emitter.onNext(i);
                    Log.d(TAG, Thread.currentThread().getName() + ":second:  " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                Log.e(TAG, "Поток приостоновлен!");
            }

            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
        return observable;
    }
}
