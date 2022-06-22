package com.example.mywhetherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView tv;
RecyclerView recyclerView;
List<Post> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://jsonplaceholder.typicode.com/posts
        recyclerView= findViewById(R.id.recyclerView);
        list=new ArrayList<>();


        Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl("https://jsonplaceholder.typicode.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        PostApi api =retrofit.create(PostApi.class);

        Call<List<Post>> call =api.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    tv.setText("code : "+response.code());
                    return;
                }
                List<Post> posts= response.body();
                for(Post post: posts){
//                    String t="";
//                    t+= post.getUserId();
//                    t += post.getId();
//                    t+= post.getTitle();
//                    t+= post.getText();
//                    tv.append(t);
                        list.add(post);
                }

                PutDataIntoRecyclerView(list);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });





        // api using for wheather app
    }

    private void PutDataIntoRecyclerView(List<Post> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter =new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
}