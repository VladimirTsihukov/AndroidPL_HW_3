package com.example.androidpl_hw_3.task_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpl_hw_3.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivityTask1 extends AppCompatActivity {

    private static final String TAG = "MainActivityTask_1";
    private MyPresenterTask1 myPresenterTask;
    private Observable observable;
    private Disposable disposable;

    @BindView(R.id.text_result)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task_1);

        ButterKnife.bind(this);
        myPresenterTask = new MyPresenterTask1();
        observable = myPresenterTask.startThread();
    }

    @SuppressLint("SetTextI18n")
    public void onClickStart(View view) {
        disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(second -> {
                    Log.d(TAG, "OnNext: " + second + "sec");
                    textView.setText(second + " sec");
                }, throwable -> {
                    Log.e(TAG, "OnError: " + Thread.currentThread().getName());
                }, () -> {
                    Log.d(TAG, "OnComplete: " + "время закончилось");
                    textView.setText("Время закончилось!");
                }
        );
    }

    public void onClickStop(View view) {
        disposable.dispose();
        Log.d(TAG, "onClickStop: таймер прерван!");
        textView.setText("Таймер прерван!");
    }
}
