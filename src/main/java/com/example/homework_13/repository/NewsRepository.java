package com.example.homework_13.repository;

import com.example.homework_13.model.News;
import com.example.homework_13.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findAllByOrderByDateDesc();
    List<News> findAllByUserOrderByDateDesc(User user);

    List<News> findAllByHeaderContainingIgnoreCaseOrderByDateDesc(String header);

    List<News> findAllByTextContainingIgnoreCaseOrderByDateDesc(String text);

    List<News> findAllByDateOrderByDateDesc(Date date);
}
