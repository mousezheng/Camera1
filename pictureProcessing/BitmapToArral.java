package com.pictureProcessing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapToArral {
		int imagePixel[][];
		public BitmapToArral(byte[] oldImageData) {
			// TODO Auto-generated constructor stub
			Bitmap newImageData = BitmapFactory.decodeByteArray(oldImageData, 0, oldImageData.length); 
			int[] pixels = new int[newImageData.getWidth()*newImageData.getHeight()];//�������е����ص����飬ͼƬ�����
			imagePixel = new int[newImageData.getWidth()][newImageData.getHeight()];
			newImageData.getPixels(pixels,0,newImageData.getWidth(),0,0,newImageData.getWidth(),newImageData.getHeight());
			
//			***�����±�Խ��
	         for(int i = 0; i < pixels.length; i++){
	        	 int width = i/newImageData.getWidth();
	        	 int height = i%newImageData.getWidth();
	             imagePixel[width][height] = pixels[i];
	         }
		}
		
		
		public int[][] getImagePixel(){
			return imagePixel;
		}
		
}
