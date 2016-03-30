package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * ����һ��������
 */
public class liuService
{
		/**
		 * ��ȡ�������ڵ�����,��Ȼ���ص��Ǽ���,ʵ����ʹ�õ��Ǽ��ϵĳ���
		 */
		public static List getAllDateBettwenTwoDate(String dateStart,String dateEnd)
		{
			//����һ��Ҫ���صļ���
			List<String> list=new ArrayList<String>();
			try 
			{
				//���忪ʼʱ��
				Calendar clStart=Calendar.getInstance();
				//���ÿ�ʼʱ��
				clStart.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart));
				//�������ʱ��
				Calendar clEnd=Calendar.getInstance();
				//���ý���ʱ��
				clEnd.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd));
				//ִ��ѭ��
				while (true) 
				{
					//�����ʼʱ���ڽ���ʱ��֮ǰ,���߿�ʼʱ����ڽ���ʱ��
					if (clStart.before(clEnd) || clStart.equals(clEnd)) 
					{
						//�����������һ��
						list.add(new SimpleDateFormat("yyyy-MM-dd").format(clStart.getTime()));
						//��ʼʱ���һ��
						clStart.add(clStart.DAY_OF_MONTH, 1);
						clStart.set(clStart.DAY_OF_MONTH, clStart.get(clStart.DAY_OF_MONTH));
					} 
					else //��������ѭ��
					{
						break;
					}
					
				}
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			return list;
		}

}
