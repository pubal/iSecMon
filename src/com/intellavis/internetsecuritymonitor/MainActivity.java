package com.intellavis.internetsecuritymonitor;


import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	public static final String SANS_infocon_url = 
			"http://isc.sans.edu/images/status.gif";
	ImageView imgView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgView = (ImageView)findViewById(R.id.SANSinfocon);
		ImageLoadTask task = new ImageLoadTask();
		task.execute(new String[] { SANS_infocon_url });
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void gotoInfocon(View view){
		String infoconURL = "http://isc.sans.org/";
		Intent infocon = new Intent(Intent.ACTION_VIEW);
		infocon.setData(Uri.parse(infoconURL));
		startActivity(infocon);
	}
	
	private class ImageLoadTask extends AsyncTask<String, Void, Bitmap>{
		
		protected Bitmap doInBackground(String... url){
			Bitmap bmp = null;
			bmp = getBitmap(url[0]);
			
			return bmp; 
		}
		
		protected void onPostExecute(Bitmap result){
			imgView.setImageBitmap(result);
		}
		
		private Bitmap getBitmap(String url)
		{
			try
			{
			Bitmap bmp = null;
			HttpClient client = new DefaultHttpClient();
			URI imageURI = new URI(url);
			HttpGet req = new HttpGet();
			req.setURI(imageURI);
			HttpResponse response = client.execute(req);
			bmp = BitmapFactory.decodeStream(response.getEntity().getContent());
			return bmp;
			}catch (Exception e){
				System.out.println("Exc="+e);
				return null;
			}
		}
		
	}
	
	
}



