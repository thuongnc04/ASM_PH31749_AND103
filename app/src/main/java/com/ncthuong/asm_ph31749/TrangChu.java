package com.ncthuong.asm_ph31749;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrangChu extends AppCompatActivity {

    ListView listView;

    List<SinhvienModel> list;

    SinhvienAdapter sinhvienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        listView = findViewById(R.id.lvCustomListView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<SinhvienModel>> call = apiService.getSinhviens();

        call.enqueue(new Callback<List<SinhvienModel>>() {
            @Override
            public void onResponse(Call<List<SinhvienModel>> call, Response<List<SinhvienModel>> response) {
                if (response.isSuccessful()) {
                    list = response.body();

                    sinhvienAdapter = new SinhvienAdapter(getApplicationContext(), list);

                    listView.setAdapter(sinhvienAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<SinhvienModel>> call, Throwable t) {

            }
        });


    }
}