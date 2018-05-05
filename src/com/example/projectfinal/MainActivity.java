package com.example.projectfinal;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



import android.R.string;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private EditText e1;
	private EditText e2;
	private TextView t1;
	private static final String TAG_SUCCESS = "success";
	Context c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		
		ImageView img=(ImageView)findViewById(R.id.imageView1);
		Drawable myDrawable = getResources().getDrawable(R.drawable.logo);
		img.setImageDrawable(myDrawable);
		//t1=(TextView)findViewById(R.id.txtView1);
		c=this;
		
		Button t=(Button)findViewById(R.id.btn);
		t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
					new loginuser().execute();
					
						
				
			}
		});
		
		
		Button t1=(Button)findViewById(R.id.button1);
		t1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), signup.class);
				startActivity(i);
					
					
						
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private class loginuser extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			String user = e1.getText().toString();
			String password = e2.getText().toString();
			

			// Building Parameters
			List<NameValuePair> paramss = new ArrayList<NameValuePair>();
			paramss.add(new BasicNameValuePair("user", user));
			paramss.add(new BasicNameValuePair("pass", password));


			// getting JSON Object
			// Note that create product url accepts POST method
			
			json_parser j=new json_parser();
			
			JSONObject json = j.makeHttpRequest("http://projectsmdnu.host22.com/login.php","GET", paramss);
			
						
			// check log cat fro response
			Log.d("Create Response", json.toString());
			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					return("success");
					
				} else {
					return("failure");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		
		protected void onPostExecute(String message) {
			// dismiss the dialog after getting all products
			if(message=="success"){
				//Toast.makeText(c,"login success",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getApplicationContext(),Main.class);
				startActivity(i);
			}
			else Toast.makeText(c,"login fail",Toast.LENGTH_SHORT).show();
			
		}
		
			
		
	}

	

	
}
