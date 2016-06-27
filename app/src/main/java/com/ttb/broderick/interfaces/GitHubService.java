package com.ttb.broderick.interfaces;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Kevin on 16/6/22.
 */
public interface GitHubService {
	@GET("users/{user}/repos")
	Call<ResponseBody> listRepos(@Path("user") String user);

	//发送post请求，field是发送的字段
	@FormUrlEncoded
	@POST("servlet/TestServlet")
	Call<ResponseBody> example(@Field("name")String name,
	                           @Field("occupation")String occupation);

	@GET("ServletDemo/{user}/TestServlet")
	Call<ResponseBody> listRepos1(@Path("user") String user);

	//上传文件
	@Multipart
	@POST("")
	Call<ResponseBody> upload(@Part("file") MultipartBody.Part file);

	//单文件上传@Multipart
	@Multipart
	@POST("servlet/UploadPicServlet")
	Call<ResponseBody> uploadPic(@Part MultipartBody.Part photo, @Part("username") RequestBody username, @Part("password") RequestBody password);

}
