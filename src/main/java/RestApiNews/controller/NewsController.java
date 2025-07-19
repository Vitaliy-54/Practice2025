package RestApiNews.controller;

import RestApiNews.dto.NewsDto;
import RestApiNews.entity.News;
import RestApiNews.mapper.NewsMapper;
import RestApiNews.service.NewsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController extends AbstractController<News, NewsDto> {

    private final NewsMapper newsMapper;

    private final NewsService newsService;

    public NewsController(NewsService newsService, NewsMapper newsMapper) {
        super(newsService);
        this.newsService = newsService;
        this.newsMapper = newsMapper;
    }

    @Override
    protected NewsDto toDto(News entity) {
        return newsMapper.toDto(entity);
    }

    @Override
    protected News toEntity(NewsDto dto) {
        return newsMapper.toEntity(dto);
    }

    @GetMapping("/displayWordcount/{id}")
    public ResponseEntity<Long> wordcountNewsID(@PathVariable Long id) {
        News news = newsService.read(id);
        if (news != null) {
            Long wordCount = 0L;
            if (news.getContent() != null) {
                wordCount = (long) news.getContent().split("\\s+").length;
            }
            return new ResponseEntity<>(wordCount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}