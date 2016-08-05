package com.pictureProcessing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;



/*
 	(1)����һ��ͼƬ��jpg�Ѿ����ԣ�
 	(2)����һ��ͼƬ�Ļ�����   grayImage
 	(3)������ͼƬ�������ؽ��д���
 		�߼���
 			��red > testnum || green > testnum || blue > testnum
 			red + green < testnum*1.5  &&   red + blue < testnum*1.5
			&& green + blue < testnum*1.5  &&   red + green + blue < testnum*1.5
 		�ı���߼�����ʵ�ֶ����� RGB �Ķ�ֵ������
 	(4)���ͼƬ
 	
 	���룺
 		RGB ��ɫͼƬ
 	�����
 		����ɫ������Ϊ��ɫ����������Ϊ��ɫ
 	
 	
 */



/*
 * ������ɫ��ȡ
 */
public class specialColor {
	
	int[][] inImage;
	int[][] imageGray;
	Bitmap imageBitmap;
	public specialColor(byte[] imageData)  {
		
		
		// TODO Auto-generated constructor stub
		imageBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
		//BitmapToArral myBitmapToArral = new BitmapToArral(imageData);
		inImage = new int[imageBitmap.getWidth()/5][imageBitmap.getHeight()/5];
		
		for (int i = 0; i < imageBitmap.getWidth(); i++,i++,i++,i++,i++) 
			for (int j = 0; j < imageBitmap.getHeight(); j++,j++,j++,j++,j++) 
				inImage[i/5][j/5] = imageBitmap.getPixel(i, j);
				
			
		
		
		
//		bufferedImage = ImageIO.read(new File(file));
		imageGray = new int[inImage.length][inImage[0].length];
		processPicture();
		
		//�ͷ��ڴ�
		imageBitmap = null;
		inImage = null;
		imageData = null;
	}
	private static int colorToRGB(int red, int green, int blue) {

		int testnum = 170;
		if (red > testnum || green > testnum || blue > testnum)
			if(red + green < testnum*1.5
				&& red + blue < testnum*1.5
				&& green + blue < testnum*1.5
				&& red + green + blue < testnum*2.5)
				
					return 1;
		return 0;
				
//				return 0xffffffff;
//			else return 0xff000000;
//		else return 0xff000000;
				
		//	return 0xff000000;	//��ɫ
		//else return 0xffffffff;	//��ɫ
		
	}
	public void processPicture() {
		
		
	
		for (int i = 0; i < imageGray.length; i++) {
			for (int j = 0; j < imageGray[0].length; j++) {
				final int color = inImage[i][j];
				final int r = (color >> 16) & 0xff;
				final int g = (color >> 8) & 0xff;
				final int b = color & 0xff;
				//int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);;
				//System.out.println(i + " : " + j + " " + gray);
				int newPixel = colorToRGB(r, g, b);
				//grayImage.setRGB(i, j, newPixel);
				imageGray[i][j] = newPixel;
			
				//System.out.print(r+" "+g+" "+b);return;
			}
		}
		
	//	File newFile = new File("C:/test/specialGray.jpg");
	//	ImageIO.write(grayImage, "jpg", newFile);
	//	//Runtime.getRuntime().exec("cmd /c start C:/test");
	//	
	//	graphics.drawImage(bufferedImage, 0, 0, 380, 400,null);
	//    graphics.drawImage(grayImage,400,0, 380,400,null);
	}
	public int[][] getImageGray(){
		return imageGray;
	}
}
