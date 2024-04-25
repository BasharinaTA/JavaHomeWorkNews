package com.example.homework_13.repository;

import com.example.homework_13.model.News;
import com.example.homework_13.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByOrderByDateDesc();

    List<News> findAllByUserOrderByDateDesc(User user);

    List<News> findAllByHeaderContainingIgnoreCaseOrderByDateDesc(String header);

    List<News> findAllByTextContainingIgnoreCaseOrderByDateDesc(String text);

    @Query("FROM News WHERE to_char(date, 'yyyy-MM-dd') = :date")
    List<News> findDate(String date);

}
