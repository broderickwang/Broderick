package com.ttb.broderick.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.ttb.broderick.R;
import com.ttb.broderick.interfaces.GitHubService;
import com.ttb.broderick.loader.GlideLoader;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HttpClicentActiviy extends AppCompatActivity
								/*implements Picker.PickListener*/{

	private static int REQUEST_CODE = 0;
	private static String TAG = "HTTP REQUEST Retrofit";
	@Bind(R.id.upload)
	Button upload;

	GitHubService service;
	Retrofit retrofit;
	ArrayList<String> photos = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_clicent_activiy);
		ButterKnife.bind(this);
		retrofit = new Retrofit.Builder()
				/*.baseUrl("https://api.github.com/")*/
				.baseUrl("http://192.168.9.45:8080/ServletDemo/")
				.build();
		service = retrofit.create(GitHubService.class);
		init();
	}

	private void init() {
//		Call<ResponseBody> repos = service.listRepos("octocat");
//		Call<ResponseBody> repos = service.listRepos1("servlet");
		Call<ResponseBody> repos = service.example("test", "occupation");
		//同步调用
		Response<ResponseBody> data;
		/*try { https://api.github.com/users/octocat/repos
			data =  repos.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		192.168.9.45:8080/ServletDemo/servlet/TestServlet
		*/
		JSONArray js = new JSONArray();
		//异步调用
		repos.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				ResponseBody rb = response.body();
				try {
					JSONArray jarray = new JSONArray(rb.string());
					if (jarray != null) {
						for (int i = 0; i < jarray.length(); i++) {
							JSONObject jo = (JSONObject) jarray.get(i);
							Log.i("object", "onResponse: " + i + " = " + jo.toString());
						}
					}
					Log.i("INFORMATION:", "onResponse: " + rb.string());
					Toast.makeText(HttpClicentActiviy.this, rb.string(), Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.e("ERROR--", "onFailure: ", t);
			}
		});
	}

	@OnClick(R.id.upload)
	public void onClick() {
		photopick();
	}
	private void photopick(){
		/*new Picker.Builder(getBaseContext(),this)
				.build()
				.startActivity();*/
		ImageConfig imageConfig
				= new ImageConfig.Builder(HttpClicentActiviy.this,new GlideLoader())
				.steepToolBarColor(getResources().getColor(R.color.blue))
				.titleBgColor(getResources().getColor(R.color.blue))
				.titleSubmitTextColor(getResources().getColor(R.color.white))
				.titleTextColor(getResources().getColor(R.color.white))
				// 开启单选   （默认为多选）
				.singleSelect()
				// 开启拍照功能 （默认关闭）
				.showCamera()
				// 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
				.filePath("/ImageSelector/Pictures")
				.build();


		ImageSelector.open( imageConfig);   // 开启图片选择器
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

			// Get Image Path List
			photos =
					data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

			for (String path : photos) {
				Log.i("ImagePathList", path);
			}
			if(photos.size() > 0){
				File file = new File(photos.get(0));
				RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
				MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", "icon.jpg", photoRequestBody);
				Call<ResponseBody> repos = service.uploadPic(photo, RequestBody.create(null, "abc"), RequestBody.create(null, "123"));

				repos.enqueue(new Callback<ResponseBody>() {
					@Override
					public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
						ResponseBody rb = response.body();
						System.out.print("successful");
					}

					@Override
					public void onFailure(Call<ResponseBody> call, Throwable t) {
						Log.e(TAG, "onFailure: ", t);
					}
				});
			}
		}
	}


	/*@Override
	public void onPickedSuccessfully(String[] paths) {
		for(String path : paths){
			photos.add(path);
		}
	}

	@Override
	public void onCancel() {

	}*/
}
