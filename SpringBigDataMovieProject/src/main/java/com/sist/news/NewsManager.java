package com.sist.news;
/*
 *    BigData
 *        API => XML,JSON
 *    JAXB (XML=class)
 *     = class => xml
 *     = xml => class 
 *     <rss>
 *        <channel>
 *            <item>
 *                <title>aaa</title>
 *                <description>aaa</description>
 *                <link>aa</link>
 *                <media:thumb url="bbb">aaa<media:thumb>
 *            </item>
 *              class Item
 *                 {
 *                     @XmlElement
 *                     String title;
 *                     @XmlElement
 *                     String description;
 *                     @XmlElement
 *                     String link;
 *                     @XmlElement(name="media:thumb")
 *                     String mediathumb;
 *                     @XmlAttribute
 *                     String url;
 *                     
 *                     
 *                 }
 *            <item>
 *                <title></title>
 *                <description></description>
 *                <link></link>
 *            </item>
 *         </channel>
 *       </rss>
 */
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import java.net.*;
@Component
public class NewsManager {
   public List<Item> naverNewsData(String data)
   {
	   List<Item> list=
			     new ArrayList<Item>();
	   try
	   {
		   URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="
				   +URLEncoder.encode(data, "UTF-8"));
		   // %EB%8C%80%EC%84%A0
		   //  decode() ==> setCharaterEncoding()
		   // server.xml => 63 URIEncoding="UTF-8"
		   JAXBContext jc=JAXBContext.newInstance(Rss.class);
		   // new => interface ,추상클래스 
		   // <br/> <br></br> <c:forEach var="vo" items="${list}">
		    // <input type=text value="Hello Java">
		   Unmarshaller un=jc.createUnmarshaller();
		   Rss rss=(Rss)un.unmarshal(url);
		   List<Item> temp=rss.getChannel().getItem();
		   for(int i=0;i<5;i++)
		   {
			   Item item=temp.get(i);
			   list.add(item);
		   }
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   return list;
   }
}











