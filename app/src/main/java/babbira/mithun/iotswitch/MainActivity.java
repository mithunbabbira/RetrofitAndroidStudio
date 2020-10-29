package babbira.mithun.iotswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private JsonPlaceHolder jsonPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);


        
        //getPost();
        //getComments();
        //createPosts();
        updatePost();




    }

    private void updatePost() {
        Post post = new Post(12, null,"New Text");

        Call<Post>call = jsonPlaceHolder.putPost(5,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {


                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " +response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " +response.code()+"\n";
                content += "ID: " +postResponse.getId()+"\n";
                content += "User ID: " +postResponse.getUserId()+"\n";
                content += "Title: " +postResponse.getTitle()+"\n";
                content += "Text: " +postResponse.getText()+"\n";

                textViewResult.setText(content);




            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private void createPosts() {




        Post post = new Post(45,"NEW TITLE","NEW TEXT");
//        Post post = new Post(23,"New Title","New Text");

//        Call<Post> call = jsonPlaceHolder.createPost(post);

        Map<String,String> fields = new HashMap<>();
        fields.put("userId","25");
        fields.put("title","New TITLE");

        Call<Post> call = jsonPlaceHolder.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " +response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " +response.code()+"\n";
                content += "ID: " +postResponse.getId()+"\n";
                content += "User ID: " +postResponse.getUserId()+"\n";
                content += "Title: " +postResponse.getTitle()+"\n";
                content += "Text: " +postResponse.getText()+"\n";

                textViewResult.setText(content);





            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                textViewResult.setText(t.getMessage());

            }
        });
    }


    private void getPost() {


       // Call<List<Post>> call = jsonPlaceHolder.getPosts(); // everything will be fetched check the interface
//        Call<List<Post>> call = jsonPlaceHolder.getPosts(4); // get only specific id


        // Call<List<Post>> call = jsonPlaceHolder.getPosts(4,"null","null"); // even this works
        //Call<List<Post>> call = jsonPlaceHolder.getPosts(4,"id","des");
//        Call<List<Post>> call = jsonPlaceHolder.getPosts(new Integer[]{1,5,6},"id","des");// fixed parameters

        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        Call<List<Post>> call = jsonPlaceHolder.getPosts(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " +response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post: posts){
                    String content = "";
                    content += "ID: " +post.getId()+"\n";
                    content += "User ID: " +post.getUserId()+"\n";
                    content += "Title: " +post.getTitle()+"\n";
                    content += "Text: " +post.getText()+"\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                textViewResult.setText(t.getMessage());

            }
        });



    }

    private void getComments() {


        //Call<List<Commet>> call = jsonPlaceHolder.getComments();// without id , check the interface
        Call<List<Commet>> call = jsonPlaceHolder.getComments(3);

        call.enqueue(new Callback<List<Commet>>() {
            @Override
            public void onResponse(Call<List<Commet>> call, Response<List<Commet>> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " +response.code());
                    return;
                }
                List<Commet> commets = response.body();

                for( Commet commet :commets){
                    String content = "" ;
                    content += "Post ID: " + commet.getPostId()+"\n";
                    content += "Name : " + commet.getName()+"\n";
                    content += "Email: " + commet.getEmail()+"\n";
                    content += "Text: " + commet.getText()+"\n";

                    textViewResult.append(content);
                }



            }

            @Override
            public void onFailure(Call<List<Commet>> call, Throwable t) {

                textViewResult.setText(t.getMessage());

            }
        });
    }
}

