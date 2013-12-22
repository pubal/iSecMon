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
//import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	//private Menu optionsMenu;
	
	public static final String SANS_infocon_url = 
			"http://isc.sans.edu/images/status.gif";
	public static final String IBM_alertcon_url = 
			"http://www-03.ibm.com/security/iss/img/alertcon.gif";
	public static final String Dell_CSI_url = 
			"http://www.secureworks.com/assets/image_store/other-jpegs/Cyber_Security_Index.jpg";
	ImageView imgViewSANS;
	ImageView imgViewIBM;
	ImageView imgViewDELL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgViewSANS = (ImageView)findViewById(R.id.SANSinfocon);
		ImageLoadTaskSANS taskSANS = new ImageLoadTaskSANS();
		taskSANS.execute(new String[] { SANS_infocon_url });
		ImageLoadTaskIBM taskIBM = new ImageLoadTaskIBM();
		imgViewIBM = (ImageView)findViewById(R.id.IBMalertcon);
		taskIBM.execute(new String[] { IBM_alertcon_url });
		ImageLoadTaskDELL taskDELL = new ImageLoadTaskDELL();
		imgViewDELL = (ImageView)findViewById(R.id.DellCSI);
		taskDELL.execute(new String[] { Dell_CSI_url });
	}	
	
	@Override
	protected void onResume() {
		super.onResume();
		this.onCreate(null);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//this.optionsMenu = menu;
		//optionsMenu = menu;
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		//this.optionsMenu = menu;
	    //MenuInflater inflater = getMenuInflater();
	    //inflater.inflate(R.menu.main, menu);
	    //return true;
	    //return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
			case R.id.action_refresh:
				//this.onCreate(null);
				//setRefreshActionButtonState(true);
				this.recreate();
				//setRefreshActionButtonState(false);
				/*
				//imgViewSANS = (ImageView)findViewById(R.id.SANSinfocon);
				ImageLoadTaskSANS taskSANS = new ImageLoadTaskSANS();
				taskSANS.execute(new String[] { SANS_infocon_url });
				
				ImageLoadTaskIBM taskIBM = new ImageLoadTaskIBM();
				//imgViewIBM = (ImageView)findViewById(R.id.IBMalertcon);
				taskIBM.execute(new String[] { IBM_alertcon_url });
				
				ImageLoadTaskDELL taskDELL = new ImageLoadTaskDELL();
				//imgViewDELL = (ImageView)findViewById(R.id.DellCSI);
				taskDELL.execute(new String[] { Dell_CSI_url });
				*/
				
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	/*
	public void setRefreshActionButtonState(final boolean refreshing) {
	    if (optionsMenu != null) {
	        final MenuItem refreshItem = optionsMenu
	            .findItem(R.id.action_refresh);
	        if (refreshItem != null) {
	            if (refreshing) {
	                refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
	            } else {
	                refreshItem.setActionView(null);
	            }
	        }
	    }
	}
	*/

	public void gotoInfocon(View view){
		String infoconURL = "http://isc.sans.org/";
		Intent infocon = new Intent(Intent.ACTION_VIEW);
		infocon.setData(Uri.parse(infoconURL));
		startActivity(infocon);
	}
	
	public void gotoAlertcon(View view){
		String alertURL = "http://securityintelligence.com/";
		Intent alertcon = new Intent(Intent.ACTION_VIEW);
		alertcon.setData(Uri.parse(alertURL));
		startActivity(alertcon);
	}
	
	public void gotoDellCSI(View view){
		String alertURL = "http://www.secureworks.com/cyber-threat-intelligence/cyber-security-index/";
		Intent alertcon = new Intent(Intent.ACTION_VIEW);
		alertcon.setData(Uri.parse(alertURL));
		startActivity(alertcon);
	}
	
	private class ImageLoadTaskSANS extends AsyncTask<String, Void, Bitmap>{
		
		protected Bitmap doInBackground(String... url){
			Bitmap bmp = null;
			bmp = getBitmap(url[0]);
			
			return bmp; 
		}
		
		protected void onPostExecute(Bitmap result){
			imgViewSANS.setImageBitmap(result);
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
	
	private class ImageLoadTaskIBM extends AsyncTask<String, Void, Bitmap>{
		
		protected Bitmap doInBackground(String... url){
			Bitmap bmp = null;
			bmp = getBitmap(url[0]);
			
			return bmp; 
		}
		
		protected void onPostExecute(Bitmap result){
			imgViewIBM.setImageBitmap(result);
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
	
private class ImageLoadTaskDELL extends AsyncTask<String, Void, Bitmap>{
		
		protected Bitmap doInBackground(String... url){
			Bitmap bmp = null;
			bmp = getBitmap(url[0]);
			
			return bmp; 
		}
		
		protected void onPostExecute(Bitmap result){
			imgViewDELL.setImageBitmap(result);
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



