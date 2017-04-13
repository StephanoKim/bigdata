package com.sist.news;
import java.util.*;

import javax.xml.bind.annotation.XmlElement;
/*
 *   Collection : 저장(add) , 수정(set) , 삭제(remove) , 검색(get) 
 *      List           Set     Map ==> interface
 *       ==========
 *       0 aaa
 *       ==========
 *       1 bbb
 *       ==========
 *       2 ccc
 *       ==========
 *       3 aaa
 *       ==========
 *       ArrayList,Vector,LinkedList(CObList)
 *       1) 순서를 가지고 있다 
 *       2) 중복을 허용
 *       
 *       Set 
 *       ===============
 *       sss ddd kkk
 *        aaa bbb
 *       ===============
 *       1)순서가 없다
 *       2)중복을 허용하지 않는다 
 *       TreeSet , HashSet
 *       
 *       Map => request,response... name=aaa
 *       ==============
 *       aaa a
 *       ==============
 *       bbb b
 *       ==============
 *       1) key,value
 *       2) key 중복이 없다 
 *       
 *       
 */
public class Channel {
     private List<Item> item=new ArrayList<Item>();

	 public List<Item> getItem() {
		return item;
	 }
     @XmlElement
	 public void setItem(List<Item> item) {
		this.item = item;
	 }
    
}








