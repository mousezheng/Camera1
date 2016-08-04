package com.mycamera;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;


import com.mycamera.R;

import com.pictureProcessing.*;

//import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements SurfaceHolder.Callback{

	private Camera mCamera;
	private SurfaceView msurfaceView;
	private SurfaceHolder mHodler;
	Button mButton;
	Bundle bundle = null; // 声明一个Bundle对象，用来存储数据  
	static int imageGray[][];
	static specialColor mSpecialColor;
	static imerode mImerode;
	static imfll mImfll;
	byte[] imageData;
	
     
//	  private Camera.PictureCallback mPictureCallback = 
//			new Camera.PictureCallback() {		
//				@Override
//				public void onPictureTaken(byte[] date, Camera camera) {
//					// TODO Auto-generated method stub
//					
//					 // date 保存所有拍照数据
//					
//					 
//				        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间  
//				        String filename = format.format(date) + ".jpg";  
//				        File fileFolder = new File(Environment.getExternalStorageDirectory()  
//				                + "/temp/");  
//				        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录  
//				            fileFolder.mkdir();  
//				        }  
//				        File jpgFile = new File(fileFolder, filename);  
//				        FileOutputStream outputStream = null;
//						try {
//							outputStream = new FileOutputStream(jpgFile);
//						} catch (FileNotFoundException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} // 文件输出流  
//				        try {
//							outputStream.write(date);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} // 写入sd卡中  
//				        try {
//							outputStream.close();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} // 关闭输出流  
//					
//					 
//					File tempFile = new File("/sdacrd/temp.png");
//					try {
//						FileOutputStream fos = new FileOutputStream(tempFile);
//						fos.write(date);
//						fos.close();
//						Log.i("tag", "图片已经保存");
//						Toast.makeText(null, "图片已保存到SD卡的temp目录下", 1).show();
//					} catch (FileNotFoundException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//						Log.i("tag", "出错了");
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			}
//			};
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		msurfaceView = (SurfaceView) findViewById(R.id.scrollView1);
		mHodler = msurfaceView.getHolder();
		mHodler.addCallback(this);
		msurfaceView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				mCamera.autoFocus(null);
			}
		});
		mButton = (Button) findViewById(R.id.button1);
		
		
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	/*
	 * 保存图片
	 */
	 final class MyPictureCallback implements PictureCallback {  
		  
        @Override  
        public void onPictureTaken(byte[] data, Camera camera) {  
            try { 
            	imageData = data;
            	camera.startPreview();
            	Bitmap bm0 = BitmapFactory.decodeByteArray(data, 0, data.length);
                Matrix m = new Matrix();
                m.setRotate(90,(float) bm0.getWidth() / 2, (float) bm0.getHeight() / 2);
                final Bitmap bm = Bitmap.createBitmap(bm0, 0, 0, bm0.getWidth(), bm0.getHeight(), m, true);
                data = Bitmap2Bytes(bm);
                
                bundle = new Bundle(); 
                bundle.putByteArray("bytes", data); //将图片字节数据保存在bundle当中，实现数据交换  
                saveToSDCard(data); // 保存图片到sd卡中  
                Toast.makeText(getApplicationContext(), "图片保存至SD卡temp目录下",  
                        Toast.LENGTH_SHORT).show();  
 //               camera.startPreview(); // 拍完照后，重新开始预览  
               
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
	 /*
	  * Bitmap ----->byte[]
	  * 
	  */
	 public byte[] Bitmap2Bytes(Bitmap bm) {
		         ByteArrayOutputStream baos = new ByteArrayOutputStream();
		         bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		         return baos.toByteArray();
		     }
		
    /** 
     * 将拍下来的照片存放在SD卡中 
     * @param data   
     * @throws IOException 
     */  
    public static void saveToSDCard(byte[] data) throws IOException {  
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间  
        String filename = format.format(date) + ".jpg";  
        File fileFolder = new File(Environment.getExternalStorageDirectory()  
                + "/temp/");  
        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"temp"的目录  
            fileFolder.mkdir();  
        }  
        File jpgFile = new File(fileFolder, filename);  
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流  
        outputStream.write(data); // 写入sd卡中  
        outputStream.close(); // 关闭输出流  
    }  
    
	//*******初始化相机,配置相机的参数
    //点击拍照
	public void capture(View view) {
//		//点击后使按钮消失
//		view.setVisibility(View.VISIBLE);
		//创建一个设置参数的对象
		Camera.Parameters parameters = mCamera.getParameters();
		//****设置拍的照片的格式
		parameters.setPictureFormat(ImageFormat.JPEG);
		//****设置图片大小
		parameters.setPreviewSize(1920,1080);
		parameters.setPictureSize(1920,1080);
		//拍照质量
		parameters.setJpegQuality(100);
		//*****设置为自动对焦
		/*
		 * mCamera.setParameters(parameters);
		 * 
		 * 太尼玛重要了，，，什么意思泥
		 * 就是把以上设置的属性实现吧，
		 * 也就是没它上面设置几乎百搭
		 */
		mCamera.setParameters(parameters);
		parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		
		mCamera.autoFocus(new Camera.AutoFocusCallback() {
			
			@Override
			public void onAutoFocus(boolean success, Camera camera) {
				// TODO Auto-generated method stub
				if (success) {
					mCamera.takePicture(null, null, new MyPictureCallback());
				}
			}
		});
		
	}
	
	public void capture2(View view) throws IOException {
		//mButton.performClick();
		mButton = (Button) findViewById(R.id.buttonNull);
		Toast.makeText(getApplicationContext(), "开始识别了",  
                Toast.LENGTH_SHORT).show();  
		mSpecialColor = new specialColor(imageData); 						//(1)
		imageGray =  mSpecialColor.getImageGray();
		mImerode = new imerode(imageGray,5);      		  	//(2)
		imageGray = mImerode.getImageGray();
		mImfll = new imfll(imageGray,40);					//(3)
		imageGray = mImfll.getImageGray();
		bwlable mBwlable = new bwlable(imageGray);			//(4)
		Toast.makeText(getApplicationContext(), mBwlable.getCounter()+"",  
                Toast.LENGTH_SHORT).show();  
		
	}
	
	
	//android 程序加载
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if (mCamera == null) {
			mCamera = getCamera();
			if (mHodler != null) {
				setStartPreView(mCamera, mHodler);
			}
		}
//		Timer timer = new Timer();  
//	    final Handler handler = new Handler(){  
//	   
//	        public void handleMessage(Message msg) {  
//	            switch (msg.what) {      
//	            case 1:
//	           
//					 mButton.performClick();
//					 break;
//				       
//	              
//	            }
//	                 
//	            super.handleMessage(msg);  
//	        }  
//	           
//	    };
//	    TimerTask task = new TimerTask(){  
//	   
//	        public void run() {  
//	            Message message = new Message();      
//	            message.what = 1;      
//	            handler.sendMessage(message);    
//	        }  
//	           
//	    }; 
//	    timer.schedule(task,0, 7*1000);
	}
	
	@Override//android程序关闭
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		releaseCamera();
	}
	
	
	
	//******得到一个相机对象
	private Camera getCamera() {
		Camera camera = null ;
		camera = android.hardware.Camera.open();
		return camera;
		
	}
	
	//******将拍摄的内容实时传递给scrollView
	@SuppressWarnings("unused")
	private void setStartPreView(Camera camera,SurfaceHolder hodler){
		try {
			camera.setPreviewDisplay(hodler);
			camera.setDisplayOrientation(90);
			camera.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * android中Camera是设备独享的
	 */
	private void releaseCamera(){
		if (mCamera != null) {
			
			mCamera.setPreviewCallback(null);
			//****将相机回调制空
			mCamera.stopPreview();
			//***取消相机的去印功能
			mCamera.release();
			//释放相机资源
			mCamera = null;
		}
		
		//******释放相机资源
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder hoder, int format, int width, int height) {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		setStartPreView(mCamera, mHodler);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		setStartPreView(mCamera, mHodler);
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		releaseCamera();
		//****释放相机资源
	}
	
//	public byte[] tranSpose(byte[] data){
//		
//		byte tempInt;
//		for (int i = 0; i < data.length - i; i++) {
//			tempInt = data[i];
//			data[i] = data[data.length - i];
//			data[data.length - i] = tempInt;
//			
//		}
//		return data;
//	}
		
	
	
	
	

//	//拍照
//
//	Intent getImageByCamera = new Intent("Android.media.action.IMAGE_CAPTURE"); 
//
//	ContentValues values = new ContentValues();  
//	       values.put(MediaStore.Images.Media.DISPLAY_NAME, String.valueOf(System.currentTimeMillis())+".jpg");  
//	       values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");  
//	       uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);  
//	       getImageByCamera.putExtra(MediaStore.Images.Media.ORIENTATION, 0);  
//	       getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//	startActivityForResult(getImageByCamera,REQUEST_CAMERA);

	//获取拍照的图片
	

	
	
	
	
	
	 
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	

}
