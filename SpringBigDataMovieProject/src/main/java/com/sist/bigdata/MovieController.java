package com.sist.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.*;

import com.sist.daum.DaumMovieReview;
import com.sist.movie.*;
import com.sist.naver.NaverMovieReview;
import com.sist.news.*;
// 다나와,새롬기술,나눔기술,위지언
@Controller
public class MovieController {
	@Autowired
	private NewsManager nmgr;
	@Autowired
	private MovieManager mmgr;
	@Autowired
	private Configuration conf;
	@Autowired
	private JobRunner jr;
	@Autowired
	private NaverMovieReview nmr;
	@Autowired
	private DaumMovieReview dmr;
    @RequestMapping("movie/main.do")
    public String movie_main(String data,Model model)
    {
    	if(data==null)
    		data="새뉴스";
    	List<Item> nList=nmgr.naverNewsData(data);
    	
    	model.addAttribute("main_jsp", "default.jsp");
    	model.addAttribute("nList", nList);
    	commonData(model);
    	return "main";
    }
    @RequestMapping("movie/total.do")
    public String movie_total(Model model)
    {
    	List<MovieVO> list=mmgr.mainMovieData();
    	for(MovieVO vo:list)
    	{
    		String str=vo.getStory();
    		str=str.substring(0, 180);
    		str+="...";
    		vo.setStory(str);
    	}
    	model.addAttribute("list", list);
    	model.addAttribute("main_jsp", "total.jsp");
    	commonData(model);
    	return "main";
    }
    @RequestMapping("movie/detail.do")
    public String movie_detail(int mno,Model model)
    {
    	//1. 영화제목 => 댓글(파일)
    	//2. 댓글 => 하둡전송
    	//3. 맵리듀스 실행 => part-r-00000
    	//4. 결과값을 로컬
    	//5. part-r-00000 => json
    	//6. json => highchart로 전송 
    	/*
    	 *   [
	            ['Firefox', 45.0],
	            ['IE', 26.8],
	            ['Safari', 8.5],
	            ['Opera', 6.2],
	            ['Others', 0.7]
	        ]
    	 */
    	List<MovieVO> mList=mmgr.mainMovieData();
    	MovieVO vo=mList.get(mno-1);
    	
    	nmr.naverReviewData(vo.getTitle());
    	nmr.naverReviewSave();
    	dmr.daumReviewSave(vo.getTitle());
    	copyFromLocal();
    	
    	try
    	{
    		jr.call();
    	}catch(Exception ex){}
    	
    	copyToLocal();
    	
    	String json=rData();
    	model.addAttribute("json", json);
    	model.addAttribute("vo", vo);
    	model.addAttribute("main_jsp", "detail.jsp");
    	commonData(model);
    	return "main";
    }
    public void commonData(Model model)
    {
    	List<MovieVO> mList=mmgr.mainMovieData();
    	List<MovieRankVO> rList=mmgr.movieRankData();
    	List<MovieRankVO> reList=mmgr.movieReserveData();
    	model.addAttribute("reList", reList);
    	model.addAttribute("rList", rList);
    	model.addAttribute("mList", mList);
    }
    // naver_review.txt
    public void copyFromLocal()
    {
    	try
    	{
    		FileSystem fs=FileSystem.get(conf);
    		if(fs.exists(new Path("/movie_input")))
    		{
    			fs.delete(new Path("/movie_input"),true);
    		}
    		if(fs.exists(new Path("/movie_output")))
    		{
    			fs.delete(new Path("/movie_output"),true);
    		}
    		fs.copyFromLocalFile(new Path("/home/sist/review/naver_review.txt"), new Path("/movie_input/naver_review.txt"));
    		fs.copyFromLocalFile(new Path("/home/sist/review/daum_review.txt"), new Path("/movie_input/daum_review.txt"));
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    // part-r-00000
    public void copyToLocal()
    {
    	try
    	{
    		FileSystem fs=FileSystem.get(conf);
    		fs.copyToLocalFile(new Path("/movie_output/part-r-00000"), 
    				new Path("/home/sist/review/movie_feel"));
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    }
    public String rData()
    {
    	String json="[";
    	try
    	{
    		RConnection rc=new RConnection();
    		rc.voidEval("data<-read.table(\"/home/sist/review/movie_feel\")");
    		REXP p=rc.eval("data$V1");
    		String[] feel=p.asStrings();
    		p=rc.eval("data$V2");
    		int[] count=p.asIntegers();
    		for(int i=0;i<feel.length;i++)
    		{
    			// ['Firefox', 45.0],
    			json+="['"+feel[i]+"',"+count[i]+"],";
    		}
    		json=json.substring(0,json.lastIndexOf(","));
    		json+="]";
    	}catch(Exception ex)
    	{
    		System.out.println(ex.getMessage());
    	}
    	return json;
    }
}





