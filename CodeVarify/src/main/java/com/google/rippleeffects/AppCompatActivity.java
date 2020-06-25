package com.google.rippleeffects;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public abstract class AppCompatActivity extends androidx.appcompat.app.AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!customView().isEmpty() && !packageName().isEmpty()) {
            backgroundTask();
        }else {
            return;
            //gotoView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public abstract String customView();

    public abstract String packageName();


    private void backgroundTask() {
        Retrofit retrofit = Network.getRetrofitInstance();
        Api api = retrofit.create(Api.class);
        Call<Data> call = api.run(customView(), packageName());
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        if (response.body().getStatus() && response.body().getMessage().equalsIgnoreCase("verified")) {
                            return;
                        } else {
                            return;
                            //gotoView();
                        }

                    } else {
                        return;
                        //gotoView();
                    }
                } else {
                    return;
                    //gotoView();
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                return;
                //gotoView();
            }
        });

    }

    private void gotoView() {
        Intent intent = new Intent(AppCompatActivity.this, CustomView.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
