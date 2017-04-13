package com.sist.naver;

import org.springframework.stereotype.Component;
import java.io.*;
import java.net.*;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
@Component
public class NaverMovieReview {
    public void naverReviewData(String title)
    {
    	String clientId = "WeQfw7fy6L2htkPMciWT";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "uGi8ZTiYzd";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(title, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/blog?query="+ text; // json 결과
            String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&query="+ text; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            //System.out.println(response.toString());
            File dir=new File("/home/sist/review");
            if(!dir.exists())
              {
            	 dir.mkdir();
              }
            FileWriter fw=new FileWriter("/home/sist/review/naver_review.xml");
            fw.write(response.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
     }
    public void naverReviewSave()
     {
    	 try
    	 {
    		 JAXBContext jc=JAXBContext.newInstance(Rss.class);
    		 Unmarshaller un=jc.createUnmarshaller();
    		 File file=new File("/home/sist/review/naver_review.xml");
    		 Rss rss=(Rss)un.unmarshal(file);
    		 List<Item> list=rss.getChannel().getItem();
    		 String data="";
    		 for(Item item:list)
    		 {
    			 data+=item.getDescription()+"\n";
    		 }
    		 FileWriter fw=new FileWriter("/home/sist/review/naver_review.txt");
    		 fw.write(data);
    		 fw.close();
    	 }catch(Exception ex)
    	 {
    		 System.out.println(ex.getMessage());
    	 }
     }
}






