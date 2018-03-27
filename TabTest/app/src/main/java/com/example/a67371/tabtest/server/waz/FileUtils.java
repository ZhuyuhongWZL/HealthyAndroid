package com.example.a67371.tabtest.server.waz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class FileUtils {
	
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/Photo_LJ/";

	public static void saveBitmap(Bitmap bm, String picName) {
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			System.out.println("createSDDir:" + dir.getAbsolutePath());
			System.out.println("createSDDir:" + dir.mkdir());
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}
	
	public static void delFile(String fileName){
		File file = new File(SDPATH + fileName);
		if(file.isFile()){
			file.delete();
        }
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;
		
		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); 
			else if (file.isDirectory())
				deleteDir(); 
		}
		dir.delete();
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}
	/**
	 * 使用系统时间获取文件名
	 * 
	 * @param fileExtension
	 *            文件后缀名
	 * @return 文件名
	 */
	public static String getFileNameForSystemTime(String fileExtension) {
		if (fileExtension.length() < 1 || !fileExtension.startsWith(".")) {
			return "";
		}
		Calendar cl = Calendar.getInstance();
		cl.setTime(Calendar.getInstance().getTime());
		long milliseconds = cl.getTimeInMillis();
		return String.valueOf(milliseconds) + fileExtension;
	}
	 /** 
     * 根据路径获取图片资源（已缩放） 
     * @param url 图片存储路径 
     * @param width 缩放的宽度 
     * @param height 缩放的高度 
     * @return 
     */  
    public static Bitmap getBitmapFromUrl(String url, double width, double height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false  
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        // 防止OOM发生  
        options.inJustDecodeBounds = false;  
        int mWidth = bitmap.getWidth();  
        int mHeight = bitmap.getHeight();  
        Matrix matrix = new Matrix();
        float scaleWidth = 1;  
        float scaleHeight = 1;  
//        try {  
//            ExifInterface exif = new ExifInterface(url);  
//            String model = exif.getAttribute(ExifInterface.TAG_ORIENTATION);  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
        // 按照固定宽高进行缩放  
        // 这里希望知道照片是横屏拍摄还是竖屏拍摄  
        // 因为两种方式宽高不同，缩放效果就会不同  
        // 这里用了比较笨的方式  
        if(mWidth <= mHeight) {  
            scaleWidth = (float) (width/mWidth);  
            scaleHeight = (float) (height/mHeight);  
        } else {  
            scaleWidth = (float) (height/mWidth);  
            scaleHeight = (float) (width/mHeight);  
        }  
//        matrix.postRotate(90); /* 翻转90度 */  
        // 按照固定大小对图片进行缩放  
        matrix.postScale(scaleWidth, scaleHeight);  
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight, matrix, true);
        // 用完了记得回收  
        bitmap.recycle();  
        return newBitmap;  
    }  
    /**
	 * 保存图片至sd卡中
	 * @param mBitmap
	 */
	public static void setPicToView(Context context, Bitmap mBitmap, String pathName) {
		File iconDir = getIconDir(context);
		String absolutePath = iconDir.getAbsolutePath();
		FileOutputStream b = null;
		File file = new File(absolutePath);
		file.mkdirs();// 创建文件夹
		String fileName = absolutePath + pathName;// 图片名字
		try {
			b = new FileOutputStream(fileName);
			mBitmap.compress(Bitmap.CompressFormat.JPEG, 70, b);// 把数据写入文件

		} catch (FileNotFoundException e) {
			throw new RuntimeException("file not found exception");// 异常提示
		} finally {
			try {
				// 关闭流
				b.flush();
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static final String CACHE="log";
	public static final String ICON="icon";
	public static final String ROOT="Isale";
	
	/**
	 * 获取图片缓存路径
	 * @return
	 */
	public static File getIconDir(Context context){
		return getDir(context,ICON);
	}
	/**
	 * 获取json缓存路径
	 * @param str
	 * @return
	 */
	public static File getDir(Context context, String str){
		StringBuilder path=new StringBuilder();
		if (isSDAvailable()) {
			path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
			path.append(File.separator);//‘/’
			path.append(ROOT);//
			path.append(File.separator);//‘/’
			path.append(str);//			
		}else{
			File filesDir = context.getCacheDir();
			path.append(filesDir.getAbsolutePath());//
			path.append(File.separator);
			path.append(str);
		}
		File file=new File(path.toString());
		if (!file.exists()||!file.isDirectory()) {
			file.mkdirs();//创建文件
		}
		return file;
	}
	/*
	 * 判断sd卡是否存在
	 */
	private static boolean isSDAvailable() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		}else{
			return false;
		}		
	}
	public static File getCacheDir(Context context){
		return getDir(context,CACHE);
	}
}
