package me.archerding.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.archerding.demo.net.ApiManager;
import me.archerding.demo.net.Weather;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button action;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        action = (Button) findViewById(R.id.action);
        result = (TextView) findViewById(R.id.result);
        action.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ApiManager.getInstance().getTestApi().getWeather("西安")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Weather weather) {
                        if (weather != null) {
                            result.append(weather.toString());
                        }
                    }
                });
    }
}
