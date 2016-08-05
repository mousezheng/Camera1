package com.pictureProcessing;



public class imfll {
	
	int[][] newImageGray;
   
	public imfll(int[][] grayImage, int i) {
		// TODO Auto-generated constructor stub
//		int[][] imageColor ;
		//imageColor = new int[][];
		newImageGray = new int[grayImage.length][grayImage[0].length];
		imfillImage(i,grayImage);
		
//		try {
//			 grayImage = ImageIO.read(new File(imFill));
//			
//			 //创建缓冲区
//			imfillImage = new BufferedImage(grayImage.getWidth(),
//											grayImage.getHeight(),
//											grayImage.getType());
			//像素矩阵
//			imageColor = new int[grayImage.getWidth()][grayImage.getHeight()];
			
//			for (int i = 0; i < grayImage.getWidth(); i++) {
//				for (int j = 0; j < grayImage.getHeight(); j++) {
//					imageColor[i][j] = grayImage.getRGB(i , j);
//				}
//			}
			
			
			//将像素矩阵赋值到缓冲区相应位置
//			for (int i = 0; i < grayImage.getWidth(); i++) {
//				for (int j = 0; j < grayImage.getHeight(); j++) {
//					imfillImage.setRGB(i, j, imageColor[i][j]);			
//				}
//			}
//			
//			graphics.drawImage(grayImage, 0, 0, 380, 400,null);
//		    graphics.drawImage(imfillImage,400,0, 380,400,null);
//		    //生成文件
//		    File mFile = new File("C:/test/imfill.jpg");
//		    ImageIO.write(imfillImage, "jpg", mFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		
	}

	
	/*
	 * 核心算法
	 * 
	 * 思路：  根据四连通启发到
	 * 	
	 * 	判断该像素上下左右四个方向，一定距离上是否都存在白色区域
	 * 	如果四个方向都存在，返回此处为白色
	 *	
	 * 
	 * 
	 */
	private void imfillImage(int distance, int[][] imageColor) {
		// TODO Auto-generated method stub
//		int[][] newImageColor;
//		newImageColor = imageColor;
		int counter;
		for (int i = distance; i < imageColor.length - distance; i++) {
			for (int j = distance; j < imageColor[1].length - distance; j++) {
				counter = 0;			
//					for (int k = 0; k < distance; k++) {						
//						if (  imageColor[i-k][j] > 0xffee0000
//							&& imageColor[i][j-k] > 0xffee0000
//							&& imageColor[i][j+k] > 0xffee0000
//							&& imageColor[i+k][j] > 0xffee0000) 
//						{
//							newImageColor[i][j] = 0xffffffff;
//							break;
//						}		
				//上
				for (int k1 = 0; k1 < distance; k1++) {
					if (imageColor[i-k1][j] == 1) 
					{
						counter++;
						break;
					}
				}
				
				//左
				for (int k1 = 0; k1 < distance; k1++) {
					if (imageColor[i][j-k1] == 1) 
					{
						counter++;
						break;
					}
				}	
				//下
				for (int k1 = 0; k1 < distance; k1++) {
					if (imageColor[i+k1][j] == 1) 
					{
						counter++;
						break;
					}
				}	
				//右
				for (int k1 = 0; k1 < distance; k1++) {
					if (imageColor[i][j+k1] == 1) 
					{
						counter++;
						break;
					}
				}
				if (counter == 4)  newImageGray[i][j] = 1;
//				}//if (imageColor[i][j] == 0xff000000)	
			}//for (int j = 0; j < imageColor[1].length; j++)
		}//for (int i = 0; i < imageColor.length; i++)
		
		imageColor = null;
		
		
	}

	public int[][] getImageGray(){
		return newImageGray;
	}
	
}
