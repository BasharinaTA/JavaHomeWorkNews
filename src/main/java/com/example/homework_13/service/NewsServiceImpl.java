package com.example.homework_13.service;

import com.example.homework_13.model.News;
import com.example.homework_13.model.User;
import com.example.homework_13.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<News> getByDate(Date date) {
        return newsRepository.findAllByDateOrderByDateDesc(date);
    }

    @Override
    public List<News> getByUser(User user) {
        return newsRepository.findAllByUserOrderByDateDesc(user);
    }

    @Override
    public News add(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        Optional<News> newsToUpdateOptional = newsRepository.findById(news.getId());
        if (newsToUpdateOptional.isPresent()) {
            News newsToUpdate = newsToUpdateOptional.get();
            newsToUpdate.setDate(new Date());
            newsToUpdate.setHeader(news.getHeader());
            newsToUpdate.setText(news.getText());
            return newsRepository.save(newsToUpdate);
        }
        return null;
    }

}
