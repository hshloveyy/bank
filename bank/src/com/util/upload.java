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
 * �ϴ��ļ�
 * @ClassName upload
 */
public class upload extends ActionSupport
{
	//�����ļ���ȡ��С
	private static final int BUFFER_SIZE = 16 * 1024;
	//�ϴ��������
	private File fujian;
	private String fujianFileName;
	private String fujianContentType;
	
	/**
	 * �ļ��ϴ�
	 * @return String 
	 */
	public String upload()
	{
		//��ȡʱ���������ƴ�����ļ���ǰ������µ��ļ���
		String newFujianName=new Date().getTime()+fujianFileName.substring(fujianFileName.indexOf("."));
		//��ȡ��ǰ���ļ��ľ���·������ƴ�����ļ���
		String dstPath = ServletActionContext.getServletContext().getRealPath("upload")+ "\\" + newFujianName;
		//ʵ����һ���ļ���·��������ƴ��������·��
		File dstFile = new File(dstPath);
		//���ϴ��ĸ������Ƶ��µ�·��
		copy(this.getFujian(),dstFile);
		Map request=(Map)ServletActionContext.getContext().get("request");
		//��ԭ�ļ��������ļ����Լ��ļ�·�����ص�ҳ��
		request.put("newFujianName", newFujianName);
		request.put("oldFujianName", fujianFileName);
		request.put("fujianPath", "/upload"+ "/" + newFujianName);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * �ѵ�һ���ļ����Ƶ��ڶ����ļ�
	 */
	private static void copy(File src, File dst) 
    {
		//�����ļ�������
        InputStream in = null;
        //�����ļ������
        OutputStream out = null;
        try 
        {
        	//ʵ����һ������������������ȡԴ�ļ���������
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            //ʵ����һ�����������������ȡĿ���ļ���������
            out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
            //��ʼ��һ���ֽ����飬������ļ�ʱÿ�ζ�ȡ�ĳ��Ȼ���˵ÿ�ζ�ȡ�Ĵ�С
            byte[] buffer = new byte[BUFFER_SIZE];
            //ÿ�ζ�ȡ�ĳ���
            int len = 0;
            //����ȡ�����ĳ��ȴ���0��˵����ȡ���������ݣ��򽫶�ȡ���������������Ŀ���ļ���
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
        {//�ļ����������֮�󣬽���������������ر�
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

	//��ȡ�ϴ��ĸ����ļ�
	public File getFujian()
	{
		return fujian;
	}

	//����Ҫ�ϴ��ĸ���
	public void setFujian(File fujian)
	{
		this.fujian = fujian;
	}

	//��ȡ�ϴ��������ļ�����
	public String getFujianContentType()
	{
		return fujianContentType;
	}

	//�����ϴ��������ļ�����
	public void setFujianContentType(String fujianContentType)
	{
		this.fujianContentType = fujianContentType;
	}

	//��ȡ�ϴ��������ļ���
	public String getFujianFileName()
	{
		return fujianFileName;
	}

	//�����ϴ��������ļ���
	public void setFujianFileName(String fujianFileName)
	{
		this.fujianFileName = fujianFileName;
	}

}
