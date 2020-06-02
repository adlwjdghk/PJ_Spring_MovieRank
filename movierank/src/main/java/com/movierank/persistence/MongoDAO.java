package com.movierank.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.movierank.domain.MovieDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MongoDAO {
	@Autowired
	private MongoOperations mongoOper;
	// Oracle의 session같은 존재
	
	public void save(MovieDTO mDto) {
		log.info(">>>>>>>>>> 영화랭킹정보 MongoDB에 저장");
		mongoOper.save(mDto);
		// mongoDB의 값을 넣는 두개의 명령문 차이
		// insert id 값이 중복되면 에러를 띄움 , 안넣어줌
		// save는 id 값이 중복되면 업그레이드를 해줌 , 최신값으로 overwrite를 해버림
	}
	
	public void dropCol() {
		log.info(">>>>>>>>>> Collection Drop");
		mongoOper.dropCollection("movie");
		// movie 라는 이름의 컬렉션을 통째로 지우라
	}
	
	public List<MovieDTO> movieList(){
		// List<MovieDTO> list = mongoOper.find(query, entityClass);
		log.info(">>>>>>> 영화평점정보 MongoDB에서 SELECT");
		Criteria cri = new Criteria();
		Query query = new Query(cri);
		List<MovieDTO> list = mongoOper.find(query, MovieDTO.class,"movie");
		for(MovieDTO one: list) {
			log.info(one.toString());
		}
		return list;
	}
}
