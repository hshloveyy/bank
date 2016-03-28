package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class liuService
{
		public static List getAllDateBettwenTwoDate(String dateStart,String dateEnd)
		{
			List<String> list=new ArrayList<String>();
			try 
			{
				Calendar clStart=Calendar.getInstance();
				clStart.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart));
				Calendar clEnd=Calendar.getInstance();
				clEnd.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd));
				while (true) 
				{
					if (clStart.before(clEnd) || clStart.equals(clEnd)) 
					{
						list.add(new SimpleDateFormat("yyyy-MM-dd").format(clStart.getTime()));
						clStart.add(clStart.DAY_OF_MONTH, 1);
						clStart.set(clStart.DAY_OF_MONTH, clStart.get(clStart.DAY_OF_MONTH));
					} 
					else 
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
