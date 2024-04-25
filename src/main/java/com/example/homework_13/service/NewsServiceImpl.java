package com.example.homework_13.service;

import com.example.homework_13.model.News;
import com.example.homework_13.model.User;
import com.example.homework_13.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private NewsRepository newsRepository;

    @Override
    public List<News> getAll() {
        return newsRepository.findAllByOrderByDateDesc();
    }

    @Override
    public List<News> getByHeader(String header) {
        return newsRepository.findAllByHeaderContainingIgnoreCaseOrderByDateDesc(header);
    }

    @Override
    public List<News> getByText(String name) {
        return newsRepository.findAllByTextContainingIgnoreCaseOrderByDateDesc(name);
    }

    @Override
    public List<News> getByDate(String date) {
        return newsRepository.findDate(date);
    }

    @Override
    public List<News> getByUser(User user) {
        return newsRepository.findAllByUserOrderByDateDesc(user);
    }

    @Override
    public News add(News news) {
        return newsRepository.save(news);
    }
}
