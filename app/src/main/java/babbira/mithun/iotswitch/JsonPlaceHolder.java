package babbira.mithun.iotswitch;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolder {

// this will return everything
//    @GET("posts")
//    Call<List<Post>> getPosts();

//    @GET("posts")
//    Call<List<Post>> getPosts(@Query("userId") int userId);

//    @GET("posts")
//    Call<List<Post>> getPosts(
//            @Query("userId") int userId,
//            @Query("_sort")String sort,
//            @Query("_order") String order);


    // array of users
//    @GET("posts")
//    Call<List<Post>> getPosts(
//            @Query("userId") Integer[] userId,
//            @Query("_sort")String sort,
//            @Query("_order") String order);


    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String , String> parameters);



    //// without id , check the main
//    @GET("posts/1/comments")
//    Call<List<Commet>> getComments();
    @GET("posts/{id}/comments")
    Call<List<Commet>> getComments(@Path("id")int postId);





    @POST("posts")
    Call<Post> createPost(@Body Post post);


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title")String title,
            @Field("body")String text);


    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String,String>fields);


    @PUT("post/{id}")
    Call<Post> putPost(@Path("id") int id ,@Body Post post);


    @PATCH("post/{id}")
    Call<Post> patchPost(@Path("id") int id ,@Body Post post);


}
