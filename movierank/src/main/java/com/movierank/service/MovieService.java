package com.movierank.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movierank.persistence.MongoDAO;
import com.movierank.persistence.MovieDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieService {
	
	@Autowired
	MongoDAO mongoDao;
	
	public void ticketRank() throws IOException {
		// 비즈니스로직: 네이버, 다음 영화정보 크롤링 및 DB에 저장
		
		// 1~20위까지 영화제목과 네이버, 다음 영화코드값을 저장하는 리스트
		List<MovieDTO> rankList = new ArrayList<>();
		String naverRankUrl = "https://movie.naver.com/movie/running/current.nhn?order=reserve";
		String daumRankUrl = "http://ticket2.movie.daum.net/Movie/MovieRankList.aspx";
		
		// 네이버 영화 실시간 예매 순위 1위~10위까지 수집
		Document naverDoc = Jsoup.connect(naverRankUrl).get();
		Elements naverList = naverDoc.select("dt.tit a");
		Elements naverDetailMovie = naverDoc.select(".lst_detail_t1 > li");
		Elements naverImg = naverDoc.select("div.thumb a img");
		Elements scoreList = naverDoc.select("dl.lst_dsc");
		// 다음 영화 실시간 예매 순위 1위 ~10위까지 수집
		Document daumDoc = Jsoup.connect(daumRankUrl).get();
		Elements daumList = daumDoc.select(".tit_join a");
		
		// DB에 있는 기존 영화정보데이터를 삭제!
		mongoDao.dropCol();
		// 원래는 이렇게 하면 안됨
		
		// 1~10위까지 영화정보 추출
		for (int i = 0; i < 10; i++) {
			// 순위, 영화제목, 포스터이미지, 네이버영화코드
			int rank = i+1; // 순위
			String movie = naverList.get(i).text(); // 영화제목
			String naverHref = naverList.get(i).attr("href");
			String navercode = naverHref.substring(naverHref.lastIndexOf("=")+1); // 영화 href 주소에서 Naver 고유 영화코드 추출
			String imgsrc = naverImg.get(i).attr("src"); // 포스터이미지
			Double naverscore = Double.parseDouble(scoreList.get(i).select("span.num").first().text());  // 네이버 평점
			String bookingrate = scoreList.get(i).select("span.num").last().text();  // 네이버 예매율
			
			Elements info1 = naverDetailMovie.get(i).select(".link_txt");
			Elements info2 = naverDetailMovie.get(i).select("dd dd");
			String strInfo2 = info2.get(2).text();
			int bIndex1 = strInfo2.indexOf("|")+1;
			int eIndex1 = strInfo2.indexOf("분")+1;
			int bIndex2 = strInfo2.lastIndexOf("|")+2;
			int eIndex2 = strInfo2.lastIndexOf("개봉")-1;
			String type = info1.get(0).text();
			String runtime = strInfo2.substring(bIndex1, eIndex1);
			String opendate = strInfo2.substring(bIndex2, eIndex2);
			String director = info1.get(1).text();
			String actor = info1.get(2).text();
			
			// 다음영화코드
			String daumHref = daumList.get(i).attr("href");
			Document detailMovie = Jsoup.connect(daumHref).get();
			String daumHrefDeep = detailMovie.select(".wrap_pbtn a").attr("href");
			String daumcode = daumHrefDeep.substring(daumHrefDeep.lastIndexOf("=")+1);
			String daumPoint = detailMovie.select(".subject_movie em.emph_grade").text();
			Double daumscore = 0.0;
			if(!daumPoint.isEmpty()) {
				daumscore = Double.parseDouble(daumPoint); // 다음 평점
			}
			
			/*
			 * double a = mDto.getDaumscore(); 
			 * double b = mDto.getNaverscore(); 
			 * double c = ( a + b )/2; 
			 * Double score = (Math.round((c*100)/100.0);
			 * 내가 작성해본 코드
			 */
			
			DecimalFormat fmt = new DecimalFormat("#.##");
			String fmtVal = fmt.format((daumscore + naverscore)/2);
			Double score = Double.parseDouble(fmtVal);
			MovieDTO mDto = new MovieDTO(rank, movie, imgsrc, type, opendate, bookingrate, runtime, director, actor, navercode, naverscore, daumcode, daumscore, score);
			
			log.info("<><><><><><>MOVIE: "+ mDto.toString());
			rankList.add(mDto);
			
			// DB에서 실시간영화예매순위 1~10위까지의 데이터를 저장\
			mongoDao.save(mDto);
		}
	}

	public List<MovieDTO> movieList() {
		// DB에 저장되어 있는 실시간영화예매순위 정보를 
		// 가져와서 View단으로 전송
		return mongoDao.movieList();
	}
	
}
