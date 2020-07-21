package com.example.androidpl_hw_3.task_2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidpl_hw_3.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivityTask2 extends AppCompatActivity {

    private static final String TAG = "MainActivityTask2";
    @BindView(R.id.text_view_result)
    TextView textView;

    private Disposable disposable;
    private MyPresenterTask2 myPresenter;
    private Single<String> single;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task2);

        ButterKnife.bind(this);

        myPresenter = new MyPresenterTask2();
        single = myPresenter.getStartThread();
    }

    @OnClick({R.id.btn_start})
    public void onClickButton(View view) {
        disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(result -> {
                    textView.setText(result);
                    Log.d(TAG, "onClickButtonStart: result - " + result);
                }, throwable -> {
                    Log.e(TAG, "onClickButtonStart: " + throwable);
                }
        );
    }
}
