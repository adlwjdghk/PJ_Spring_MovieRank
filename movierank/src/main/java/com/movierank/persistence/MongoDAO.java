package com.movierank.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

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
	}
	
	public void dropCol() {
		log.info(">>>>>>>>>> Collection Drop");
		mongoOper.dropCollection("movieDTO");
	}
	
	public List<MovieDTO> movieList(){
		log.info(">>>>>>>>>> 영화랭킹정보 MongoDB에 저장");
		// List<MovieDTO> list = mongoOper.find(query, entityClass);
		
		return null;
	}
}
