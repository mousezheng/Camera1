package com.pictureProcessing;



/*
 * ͼ��ĸ�ʴ����
 * 
 * �㷨���˼·��
 * 		
 * 	��1������һ����ֵ��ͼ��
 * 	��2���Զ�ֵ��ͼ�����ɨ�裬�԰�ɫ����߽���и�ʴ�㷨˼·Ϊ��
 * 			
 * 			��һ����ɫ������Χ����һ���������ϵĺ�ɫ��������ɫʱ������Χ����һ�������ĺ�ɫͼ����� &�����������ɺ�ɫ
 * 			�Ӷ�ʵ�ָ�ʴ����
 *  ��3������������
 *  ��4������Ӧͼ�����
 */

public class imerode {

	
	int[][] newImageGray;
	public imerode(int[][] imageGray,int Circle) {
		// TODO Auto-generated constructor stub
//		grayImage = ImageIO.read(new File(string));
//		imerodeImage = new BufferedImage(grayImage.getWidth(),
//										 grayImage.getHeight(),
//										 grayImage.getType());
//		
//		//ʹ��ͬһ�ڴ�
//		imerodeImage = grayImage;
		
//		for (int i = 0; i < grayImage.getWidth(); i++) {
//			for (int j = 0; j < grayImage.getHeight(); j++) {
//				imerodeImage.setRGB(i, j, grayImage.getRGB(i, j));
//			}
//		}
		newImageGray = new int[imageGray.length][imageGray[0].length];
		for (int i = 0; i < imageGray.length; i++) {
		for (int j = 0; j < imageGray[0].length; j++) {
			newImageGray[i][j] = imageGray[i][j];
		}
	}
		int[][] origin = getCircle(Circle);
		imerodeImage(origin,imageGray,Circle);
		
		
//		BufferedImage testImage = new BufferedImage(origin.length,
//													origin[0].length,
//													grayImage.getType());
//		for (int j = 0; j < origin.length; j++) {
//			for (int j2 = 0; j2 < origin[0].length; j2++) {
//				testImage.setRGB(j, j2, origin[j][j2]);
//			}
//		}
//		g.drawImage(testImage, 0, 0, 300, 300, null);	
		//��ͼ
//		g.drawImage(grayImage, 0, 0, 380, 400,null);
//	    g.drawImage(imerodeImage,400,0, 380,400,null);
		
	}
	private int[][] getCircle(int i) {
		// TODO Auto-generated method stub
		int Circle[][];
		Circle = new int[i][i];
		
		for (int j = 0; j < Circle.length; j++) {
			for (int j2 = 0; j2 < Circle[0].length; j2++) {
				if (Math.abs((i/2-j)*(i/2-j)+(i/2-j2)*(i/2-j2)) <= (i/2)*(i/2)) {
					Circle[j][j2] = 0;
				}
				else Circle[j][j2] = 1;
			}
		}
		return Circle;
	}
	
	private void imerodeImage(int[][] origin, int[][] imageGray, int k) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < imageGray.length; i++) {
			for (int j = 0; j < imageGray[0].length; j++) {
//				int boundary = 0xffaa0000;
				if (i <= k || j <= k 
					|| i >= imageGray.length-k
					|| j >= imageGray[0].length-k) 
					newImageGray[i][j] = 0;
				else if (imageGray[i][j] == 1
						&& (   imageGray[i+1][j] == 0
							|| imageGray[i-1][j] == 0
							|| imageGray[i][j+1] == 0
							|| imageGray[i][j-1] == 0)) {
						
						for (int l = 0; l < origin.length; l++) {
							for (int l2 = 0; l2 < origin[0].length; l2++) {
	//							imerodeImage.setRGB(i-origin.length/2, j-origin[0].length/2,
	//									grayImage.getRGB(i-origin.length/2, j-origin[0].length/2) 
	//									& origin[l][l2]);
								//��origin�Ƚϴ�ʱ�����ܳ��ֶԱ߽�����Ԫ�صķ���
								if (origin[l][l2] == 0) {
									newImageGray[i-l+origin.length/2][j-l2+origin[0].length/2] = 0;
	//								imerodeImage.setRGB(i-l+origin.length/2, j-l2+origin[0].length/2, 0xff000000);
								}
								
							}
						}
						
					}
				
			}//for (int j = 0; j < imerodeImage.getHeight(); j++)
		}//for (int i = 0; i < imerodeImage.getWidth(); i++) 
		 
	}

	public int[][] getImageGray(){
		return newImageGray;
	}
}
