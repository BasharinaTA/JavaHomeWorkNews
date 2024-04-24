package com.example.homework_13.service;

import com.example.homework_13.model.News;
import com.example.homework_13.model.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface NewsService {
    List<News> getAll();

    List<News> getByHeader(String header);

    List<News> getByText(String text);

    List<News> getByDate(Date date);

    List<News> getByUser(User user);

    News add(News news);

    News update(News news);
}
