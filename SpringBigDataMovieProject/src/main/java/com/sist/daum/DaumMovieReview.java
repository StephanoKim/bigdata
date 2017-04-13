package com.sist.daum;

import org.springframework.stereotype.Component;
// 7b429affa32c43e1adf62ad1eebb6928
// https://apis.daum.net/search/blog
import java.io.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.net.*;
@Component
public class DaumMovieReview {
   public String daumReviewData(int page,String title)
   {
	   String review="";
	   try
	   {
		   String key="f815bcb1f8f679fb46040eb6003cb51e";
		   String strUrl="https://apis.daum.net/search/blog?output=xml&result=20&pageno="
		                +page+"&q="+URLEncoder.encode(title, "UTF-8")+"&apikey="+key;
		   URL url=new URL(strUrl);
		   JAXBContext jc=JAXBContext.newInstance(Channel.class);
		   Unmarshaller un=jc.createUnmarshaller();
		   Channel channel=(Channel)un.unmarshal(url);
		   List<Item> list=channel.getItem();
		   for(Item item:list)
		   {
			   System.out.println(item.getDescription());
			   review+=item.getDescription()+"\n";
		   }
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   return review;
   }
   public void daumReviewSave(String title)
   {
	   try
	   {
		   String data="";
		   for(int i=1;i<=3;i++)
		   {
			   data+=daumReviewData(i, title);
		   }
		   
		   FileWriter fw=new FileWriter("/home/sist/review/daum_review.txt");
		   fw.write(data);
		   fw.close();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
   }
}








