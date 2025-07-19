package RestApiNews.controller;

import RestApiNews.dto.CategoriesDto;
import RestApiNews.entity.Categories;
import RestApiNews.mapper.CategoriesMapper;
import RestApiNews.service.CategoriesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController extends AbstractController<Categories, CategoriesDto> {

    private final CategoriesMapper categoriesMapper;

    public CategoriesController(CategoriesService categoriesService, CategoriesMapper categoriesMapper) {
        super(categoriesService);
        this.categoriesMapper = categoriesMapper;
    }

    @Override
    protected CategoriesDto toDto(Categories entity) {
        return categoriesMapper.toDto(entity);
    }

    @Override
    protected Categories toEntity(CategoriesDto dto) {
        return categoriesMapper.toEntity(dto);
    }
}