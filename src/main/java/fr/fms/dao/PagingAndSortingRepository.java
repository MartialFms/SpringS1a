package fr.fms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
	
	static Pageable ofSize(int pageSize) {
		return PageRequest.of(0, pageSize);
	}
	
	/*
	Pageable firstPageWithFiveElements = PageRequest.of(0, 5);

	Pageable secondPageWithFiveElements = PageRequest.of(1, 5);

	Pageable thirdPageWithFiveElements = PageRequest.of(2, 5);
	
	//Page<Article> findAll(Pageable pageable);
	Page<Article> articles = articleRepository.findAll(firstPageWithFiveElements);
	*/
}

