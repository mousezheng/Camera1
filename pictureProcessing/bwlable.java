package com.pictureProcessing;



/*
 * 		ͼ�����ͨ�������㣬
 * 		*ʹ�õݹ��㷨�����ȴ���һ����ά�ĵ����飬���ڴ�Ŷ����Ƶ�ͼ�����ݣ�
 * 		�����ɫΪ1����ɫΪ0�� ��forѭ��ȥ����ͼ�񣬺���ɨ�裬����ͼ���Ѿ�ʹ�ø�ʴ�㷨ȥ����ϸС�����ʺͣ�
 * 		�򵥵ķ����˰�ɫ���򣬶�����ͨ������б��Ϊ n
 * 		����߼�Ϊ��
 * 			ʹ�õݹ��㷨��������Χ�ĸ����з��ʣ�������ͨ��ֱ����Χû��1��������ͨ�Ŀ��Ա��Ϊͬһ�����֡�
 * 			ʹ�õݹ��㷨��������Χ�˸����з��ʣ�������ͨ��ֱ����Χû��1��������ͨ�Ŀ��Ա��Ϊͬһ�����֡�
 * 
 * 
 */
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;

//import javax.swing.JFrame;
//import javax.swing.JPanel;

public class bwlable {
	
	static int image[][];
//	BufferedImage grayImage;
//	BufferedImage colorImage;
	static int counter=1;
	
	public bwlable(int[][] grayImageData) {
		// TODO Auto-generated constructor stub
		
		image = new int[grayImageData.length][grayImageData[0].length];
		for (int i = 1; i < grayImageData.length-1; i++) {
			for (int j = 1; j < grayImageData[0].length-1; j++) {
				image[i][j] = grayImageData[i][j];
			}
		}
		for (int i = 1; i < grayImageData.length-1; i++) {
			for (int j = 1; j < grayImageData[0].length-1; j++) {
				if (image[i][j] == 1) {
					counter++;
					dealBwlabe(i,j);
				}
			}
		}
		//System.out.println(counter-1);
		
//		color();
//		dialog();
//		
	}

	
	private static void dealBwlabe(int i, int j) {
		// TODO Auto-generated method stub
		//��
//		if (image[i-1][j] == 1) {
//			image[i-1][j] = counter;
//			dealBwlabe(i-1, j);
//		}
		//��
		if (image[i][j-1] == 1) {
			image[i][j-1] = counter;
			dealBwlabe(i, j-1);
		}
		//��
		if (image[i+1][j] == 1) {
			image[i+1][j] = counter;
			dealBwlabe(i+1, j);
		}
		//��
		if (image[i][j+1] == 1) {
			image[i][j+1] = counter;
			dealBwlabe(i, j+1);
		}

////����ͨ��Ҫ
//		//����
//		if (image[i-1][j-1] == 1) {
//			image[i-1][j-1] = counter;
//			dealBwlabe(i-1, j-1);
//		}
//		//����
//		if (image[i-1][j+1] == 1) {
//			image[i-1][j+1] = counter;
//			dealBwlabe(i-1, j+1);
//		}
//		//����
//		if (image[i+1][j-1] == 1) {
//			image[i+1][j-1] = counter;
//			dealBwlabe(i+1, j-1);
//		}
//		//����
//		if (image[i+1][j+1] == 1) {
//			image[i+1][j+1] = counter;
//			dealBwlabe(i+1, j+1);
//		}		
		
	}
	
//	private void color(){
//		
//		int color[];
//		color = new int[counter+1];
//		
//		for (int i = 0; i < color.length; i++)  
//			color[i] = (int) (0xff000000+Math.random()*0xffffff);
//		colorImage = new BufferedImage(image.length, image[0].length, 5);
//		
//		for (int i = 0; i < colorImage.getWidth(); i++) 
//			for (int j = 0; j < colorImage.getHeight(); j++) 
//			{	if (image[i][j] > 0) colorImage.setRGB(i, j, color[image[i][j]]);
//				else colorImage.setRGB(i, j, 0xff000000);
//			}
//		
//	}
	
//	public void dialog(){
//		JFrame mFrame = new JFrame();
//		mFrame.setSize(800, 500);
//		mFrame.setVisible(true);
//		
//		mFrame.add(this);
//		//mFrame
//	}
//	@Override
//	public void paint(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paint(g);
//		try {
//			grayImage = ImageIO.read(new File("specialGray.jpg"));
//			
//			g.drawImage(grayImage, 0, 0, 400, 500, null);
//			g.drawImage(colorImage, 402, 0, 400, 500, null);
//			g.setColor(Color.red);
//			Font mFont= g.getFont();
//			g.setFont(new Font(mFont.getFontName(), Font.PLAIN, 20));
//			g.drawString(counter-1+" ", 2, 20);
//			g.drawString("ԭͼ", 100, 400);
//			g.drawString("Ⱦɫ����ͼ", 500, 400);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//	}
	public int getCounter() {
		return counter-1;
	}
}




