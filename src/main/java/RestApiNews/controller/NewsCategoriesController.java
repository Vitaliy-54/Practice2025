package RestApiNews.controller;

import RestApiNews.dto.NewsCategoriesDto;
import RestApiNews.entity.Categories;
import RestApiNews.entity.News;
import RestApiNews.entity.NewsCategories;
import RestApiNews.service.CategoriesService;
import RestApiNews.service.NewsCategoriesService;
import RestApiNews.service.NewsService;
import RestApiNews.mapper.NewsCategoriesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/newscategories")
public class NewsCategoriesController {

    @Autowired
    private NewsCategoriesService newsCategoriesService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private NewsCategoriesMapper newsCategoriesMapper;

    @GetMapping
    public ResponseEntity<List<NewsCategoriesDto>> getAll() {
        List<NewsCategories> entities = newsCategoriesService.read();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<NewsCategoriesDto> dtos = entities.stream()
                .map(newsCategoriesMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsCategoriesDto> getById(@PathVariable Long id) {
        NewsCategories entity = newsCategoriesService.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newsCategoriesMapper.toDto(entity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NewsCategoriesDto> create(@RequestBody NewsCategoriesDto dto) {
        if (dto.getNews() == null || dto.getNews().getId() == null ||
                dto.getCategories() == null || dto.getCategories().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        News news = newsService.read(dto.getNews().getId());
        Categories categories = categoriesService.read(dto.getCategories().getId());

        if (news == null || categories == null) {
            return ResponseEntity.notFound().build();
        }

        if (newsCategoriesService.existsByNewsAndCategories(news, categories)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        NewsCategories entity = new NewsCategories();
        entity.setNews(news);
        entity.setCategories(categories);
        newsCategoriesService.save(entity);

        return new ResponseEntity<>(newsCategoriesMapper.toDto(entity), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<NewsCategoriesDto> update(@PathVariable Long id, @RequestBody NewsCategoriesDto dto) {
        NewsCategories existingEntity = newsCategoriesService.read(id);
        if (existingEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (dto.getNews() == null || dto.getNews().getId() == null ||
                dto.getCategories() == null || dto.getCategories().getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        News news = newsService.read(dto.getNews().getId());
        Categories categories = categoriesService.read(dto.getCategories().getId());

        if (news == null || categories == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingEntity.setNews(news);
        existingEntity.setCategories(categories);

        newsCategoriesService.edit(existingEntity);

        return new ResponseEntity<>(newsCategoriesMapper.toDto(existingEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        NewsCategories entity = newsCategoriesService.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newsCategoriesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/CategoriesForNews/{id}")
    public ResponseEntity<List<Categories>> categoriesForNews(@PathVariable("id") Long id) {
        News news = newsService.read(id);
        if (news != null) {
            List<Categories> categories = news.getNewsCategories().stream()
                    .map(NewsCategories::getCategories)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}