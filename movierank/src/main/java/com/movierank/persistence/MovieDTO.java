package com.movierank.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MovieDTO {

	private int rank;			
	private String movie;
	private String imgsrc;
	private String type;
	private String opendate;
	private String bookingrate;
	private String runtime;
	private String dirctor;
	private String actor;
	private String navercode;
	private Double naverscore;
	private String daumcode;
	private Double daumscore;
	private Double score;
	/* 순위 
	 * 영화제목 
	 * 포스터이미지 
	 * 영화장르 
	 * 개봉일 
	 * 예매율 
	 * 상영시간 
	 * 감독 
	 * 출연자 
	 * 네이버 영화코드 
	 * 네이버 평점 
	 * 다음영화코드 
	 * 다음평점 
	 * 네이버+다음평점의 평균 */
	
	public MovieDTO(int rank, String movie, String imgsrc, String type, String opendate, String bookingrate,
			String runtime, String dirctor, String actor, String navercode, Double naverscore, String daumcode,
			Double daumscore) {
		super();
		this.rank = rank;
		this.movie = movie;
		this.imgsrc = imgsrc;
		this.type = type;
		this.opendate = opendate;
		this.bookingrate = bookingrate;
		this.runtime = runtime;
		this.dirctor = dirctor;
		this.actor = actor;
		this.navercode = navercode;
		this.naverscore = naverscore;
		this.daumcode = daumcode;
		this.daumscore = daumscore;
	}
	
	
}
