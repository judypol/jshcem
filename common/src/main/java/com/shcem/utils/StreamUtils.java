/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.shcem.utils;

import java.io.*;

/**
 * 
 * @author
 * @mail
 * 
 */
public class StreamUtils {

	final static int BUFFER_SIZE = 4096;
	/**
	 * 将InputStream转换成String,默认utf-8
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String inputStream2String(InputStream in) {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		String string = null;
		int count = 0;
		try {
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}

		data = null;
		try {
			string = new String(outStream.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 将InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String inputStream2String(InputStream in, String encoding) {
		String string = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		try {
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}

		data = null;
		try {
			string = new String(outStream.toByteArray(), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 将String转换成InputStream,UTF-8
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream string2InputStream(String in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("UTF-8"));
		return is;
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static byte[] string2byte(String in) {
		byte[] bytes = null;
		try {
			bytes = inputStream2Byte(string2InputStream(in));
		} catch (IOException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 将InputStream转换成byte数组
	 * 
	 * @param in
	 *            InputStream
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] inputStream2Byte(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return outStream.toByteArray();
	}

	/**
	 * 将byte数组转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream byte2InputStream(byte[] in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String byte2String(byte[] in) {

		String result = null;
		InputStream is = null;
		try {
			is = byte2InputStream(in);
			result = inputStream2String(is, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String GetString(String in) {

		String is = null;
		try {
			is = byte2String(string2byte(in));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	// InputStream 转换成byte[]
	public byte[] getBytes(InputStream is) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[BUFFER_SIZE];
		int len = 0;

		while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
			baos.write(b, 0, len);
		}

		baos.flush();

		byte[] bytes = baos.toByteArray();

		System.out.println(new String(bytes));

		return bytes;
	}
	/**
	 * 根据文件路径创建文件输入流处理
	 * 以字节为单位（非 unicode ）
	 *
	 * @param filepath
	 * @return
	 */
	public static FileInputStream getFileInputStream(String filepath) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}
	/**
	 * 根据文件对象创建文件输入流处理
	 * 以字节为单位（非 unicode ）
	 * @param file
	 * @return
	 */
	public static FileInputStream getFileInputStream(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}
	/**
	  * 根据文件对象创建文件输出流处理
	 * 以字节为单位（非 unicode ）
	 * @param file
	 * @param append true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return
	 */
	public static FileOutputStream getFileOutputStream(File file, boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file,append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}
	/**
	 * 根据文件路径创建文件输出流处理
	 * 以字节为单位（非 unicode ）
	 * @param filepath
	 * @param append true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return
	 */
	public static FileOutputStream getFileOutputStream(String filepath,boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filepath,append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}

	public static File getFile(String filepath) {
		return new File(filepath);
	}

	/**
	 * 将inputStream转为outputStream
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream inputStream2OutputStream(InputStream in) throws Exception{
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			swapStream.write(ch);
		}
		return swapStream;
	}

	/**
	 *将outputstream转换为inputStream
	 * @param os
	 * @return
	 */
	public static ByteArrayInputStream outputStream2InputStream(ByteArrayOutputStream os){
		ByteArrayInputStream swapStream = new ByteArrayInputStream(os.toByteArray());
		return swapStream;
	}
}
