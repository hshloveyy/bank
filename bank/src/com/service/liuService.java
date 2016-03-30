package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 算是一个工具类
 */
public class liuService
{
		/**
		 * 获取两个日期的天数,虽然返回的是集合,实际上使用的是集合的长度
		 */
		public static List getAllDateBettwenTwoDate(String dateStart,String dateEnd)
		{
			//定义一个要返回的集合
			List<String> list=new ArrayList<String>();
			try 
			{
				//定义开始时间
				Calendar clStart=Calendar.getInstance();
				//设置开始时间
				clStart.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart));
				//定义结束时间
				Calendar clEnd=Calendar.getInstance();
				//设置结束时间
				clEnd.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd));
				//执行循环
				while (true) 
				{
					//如果开始时间在结束时间之前,或者开始时间等于结束时间
					if (clStart.before(clEnd) || clStart.equals(clEnd)) 
					{
						//往集合中添加一天
						list.add(new SimpleDateFormat("yyyy-MM-dd").format(clStart.getTime()));
						//开始时间加一天
						clStart.add(clStart.DAY_OF_MONTH, 1);
						clStart.set(clStart.DAY_OF_MONTH, clStart.get(clStart.DAY_OF_MONTH));
					} 
					else //否则跳出循环
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
