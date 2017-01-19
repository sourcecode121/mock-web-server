package com.example.mockwebserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);

        MainApplication mainApplication = (MainApplication) getApplication();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mainApplication.getBaseUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(OkHttp.getInstance(
                        mainApplication.getSSLSocketFactory(),
                        mainApplication.getX509TrustManager()
                ))
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        service.getUser("sourcecode121").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    textView.setText(String.valueOf(user.public_repos));
                    Log.d("MainActivity", String.valueOf(user.public_repos));
                }
                else {
                    textView.setText(String.valueOf(response.code()));
                    Log.d("Response code", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                textView.setText(t.getClass().getSimpleName());
                t.printStackTrace();
            }
        });
    }
}
