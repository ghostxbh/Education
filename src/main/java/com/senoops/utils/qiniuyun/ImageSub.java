package com.senoops.utils.qiniuyun;

public class ImageSub {

	public static String subImage(String imageUrl){
		String[] split = imageUrl.split(".com/");
		return split[1];		
	}
	
	public static void main(String[] args) {
		String url = "http://collegeimage.uzykj.com/5e530697abb9465f850735e680353e49_20181114.jpg";
		System.out.println(subImage(url));
	}
}
