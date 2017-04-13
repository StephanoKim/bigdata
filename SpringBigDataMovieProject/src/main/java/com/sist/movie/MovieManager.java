package com.sist.movie;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
@Component
/*
 *  <div class="box-contents">
                        <a href="/movies/detail-view/?midx=79526">
                            <strong class="title">아빠는 딸</strong>
                        </a>

      <span class="thumb-image">
                                <img src="http://img.cgv.co.kr/Movie/Thumbnail/Poster/000079/79526/79526_185.jpg" alt="아빠는 딸 포스터" onerror="errorImage(this)"/>
                                <span class="ico-grade grade-12">12세 이상</span>
                            </span>

       <div class="box-image" >
                        <strong class="rank">No.2</strong>	
                        <a href="/movies/detail-view/?midx=79466">
                            <span class="thumb-image">
                                <img src="http://img.cgv.co.kr/Movie/Thumbnail/Poster/000079/79466/79466_185.jpg" alt="미녀와 야수 포스터" onerror="errorImage(this)"/>
                                <span class="ico-grade grade-all">전체</span>
                            </span>

                        </a>
                        <span class="screentype">

                                <a class="threeD" href="#" title="3D 상세정보 바로가기" data-regioncode="3D">3D</a>

                        </span>
                    </div>
 */
public class MovieManager {
	public List<String> movieSiteData()
	{
		List<String> list=new ArrayList<String>();
		try
		{
			Document doc=Jsoup.connect("http://www.cgv.co.kr/movies/?ft=0").get();
			Elements sElem=doc.select("div.box-contents a");
			int j=0;
			for(int i=0;i<7;i++)
			{
				Element s=sElem.get(j);
				System.out.println(s.attr("href"));
				list.add(s.attr("href"));
				j+=2;
			}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}
	public List<MovieVO> mainMovieData()
	{
		List<String> temp=movieSiteData();
		List<MovieVO> list=new ArrayList<MovieVO>();
		try
		{
			int no=0;
			for(String site:temp)
			{
				Document doc=Jsoup.connect("http://www.cgv.co.kr"+site).get();
				/*
				 *  class="sect-base-movie">
    <h3><strong>분노의 질주: 더 익스트림</strong>

    div class="sect-story-movie"
				 */
				Element tElem=doc.select("div.sect-base-movie strong").first();
				Element pElem=doc.select("div.box-image img").first();
				Element sElem=doc.select("div.sect-story-movie").first();

				Element gElem=doc.select("div.spec dt").get(2);
				Element aElem=doc.select("div.spec dd.on").first();
				/*
				 *  <div class="spec">
            <dl>
                <dt>감독 :&nbsp;</dt>
                <dd>


                        <a href="/movies/persons/?pidx=11908">빌 콘돈</a>                    

                </dd>
				 */
				Element dElem=doc.select("div.spec dd a").first();
				Element regElem=doc.select("dd.on").get(2);
				/*
				 *  <div class="score"> 
            <strong class="percent">예매율&nbsp;<span>4.0%</span>
				 */
				Element rElem=doc.select("div.score strong.percent span").first();


				MovieVO vo=new MovieVO();
				vo.setMno(no+1);
				vo.setTitle(tElem.text());
				vo.setPoster(pElem.attr("src"));
				vo.setStory(sElem.text());
				vo.setGenre(gElem.html());
				vo.setDirector(dElem.text());
				vo.setActor(aElem.text());
				vo.setRegdate(regElem.text());
				vo.setReserve(rElem.text());
				list.add(vo);
				no++;
			}
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		return list;
	}
	public List<MovieRankVO> movieRankData()
	{
		List<MovieRankVO> list=new ArrayList<MovieRankVO>();
		try {
			Document doc=Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rmovie.nhn").get();
			Elements tElem=doc.select("td.title div.tit3 a");
			Elements iElem=doc.select("td.ac img");
			Elements cElem=doc.select("td.range");
			int j=1;
			for(int i=0;i<10;i++)
			{
				Element t=tElem.get(i);
				Element img=iElem.get(j);
				Element c=cElem.get(i);
				
				MovieRankVO vo=new MovieRankVO();
				vo.setRank(i+1);
				vo.setTitle(t.text());
				vo.setImage(img.attr("src"));
				vo.setIncrement(Integer.parseInt(c.text()));
				list.add(vo);
				j+=2;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
	public List<MovieRankVO> movieReserveData()
	{
		List<MovieRankVO> list=new ArrayList<MovieRankVO>();
		try {
			Document doc=Jsoup.connect("http://movie.naver.com/movie/sdb/rank/rreserve.nhn").get();
			Elements tElem=doc.select("td.title div.tit4 a");
			Elements rElem=doc.select("td.reserve_per");
			int j=1;
			for(int i=0;i<10;i++)
			{
				Element t=tElem.get(i);
				Element r=rElem.get(i);
				
				MovieRankVO vo=new MovieRankVO();
				vo.setRank(i+1);
				vo.setTitle(t.text());
				vo.setScore(r.text());
				list.add(vo);
				j+=2;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return list;
	}
}
