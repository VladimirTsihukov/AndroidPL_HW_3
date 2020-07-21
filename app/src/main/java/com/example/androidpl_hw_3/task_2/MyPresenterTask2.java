package com.example.androidpl_hw_3.task_2;

import android.util.Log;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class MyPresenterTask2 {

    private static final String TAG = "MyPresenterTask2";

    public Single<String> getStartThread() {
        Single<String> single = Single.create((SingleOnSubscribe<String>) emitter -> {
            String result = "RxJava (Single)";
            Log.d(TAG, "getStartThread: " + Thread.currentThread().getName());
            emitter.onSuccess(result);
        }).subscribeOn(Schedulers.io());
        return single;
    }
}
