package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传文件
 * @ClassName upload
 */
public class upload extends ActionSupport
{
	//设置文件读取大小
	private static final int BUFFER_SIZE = 16 * 1024;
	//上传的问题件
	private File fujian;
	private String fujianFileName;
	private String fujianContentType;
	
	/**
	 * 文件上传
	 * @return String 
	 */
	public String upload()
	{
		//获取时间戳，并且拼接在文件名前面组成新的文件名
		String newFujianName=new Date().getTime()+fujianFileName.substring(fujianFileName.indexOf("."));
		//获取当前类文件的绝对路径，再拼接上文件名
		String dstPath = ServletActionContext.getServletContext().getRealPath("upload")+ "\\" + newFujianName;
		//实例化一个文件，路径是上面拼接起来的路径
		File dstFile = new File(dstPath);
		//将上传的附件复制到新的路径
		copy(this.getFujian(),dstFile);
		Map request=(Map)ServletActionContext.getContext().get("request");
		//将原文件名、新文件名以及文件路径返回到页面
		request.put("newFujianName", newFujianName);
		request.put("oldFujianName", fujianFileName);
		request.put("fujianPath", "/upload"+ "/" + newFujianName);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * 把第一个文件复制到第二个文件
	 */
	private static void copy(File src, File dst) 
    {
		//定义文件输入流
        InputStream in = null;
        //定义文件输出流
        OutputStream out = null;
        try 
        {
        	//实例化一个缓冲输入流，并读取源文件到缓冲流
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            //实例化一个缓冲输出流，并读取目标文件到缓冲流
            out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
            //初始化一个字节数组，定义读文件时每次读取的长度或者说每次读取的大小
            byte[] buffer = new byte[BUFFER_SIZE];
            //每次读取的长度
            int len = 0;
            //当读取出来的长度大于0，说明读取出来有数据，则将读取出来的数据输出到目标文件中
            while ((len = in.read(buffer)) > 0) 
            {
                out.write(buffer, 0, len);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        finally
        {//文件流操作完成之后，将输入流、输出流关闭
            if (null != in) 
            {
                try 
                {
                    in.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            if (null != out) 
            {
                try 
                {
                    out.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }

	//获取上传的附件文件
	public File getFujian()
	{
		return fujian;
	}

	//设置要上传的附件
	public void setFujian(File fujian)
	{
		this.fujian = fujian;
	}

	//获取上传附件的文件类型
	public String getFujianContentType()
	{
		return fujianContentType;
	}

	//设置上传附件的文件类型
	public void setFujianContentType(String fujianContentType)
	{
		this.fujianContentType = fujianContentType;
	}

	//获取上传附件的文件名
	public String getFujianFileName()
	{
		return fujianFileName;
	}

	//设置上传附件的文件名
	public void setFujianFileName(String fujianFileName)
	{
		this.fujianFileName = fujianFileName;
	}

}
