package RestApiNews.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import RestApiNews.dto.CategoriesDto;
import RestApiNews.entity.Categories;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {

    CategoriesDto toDto(Categories entity);

    @Mapping(target = "newsCategories", ignore = true)
    Categories toEntity(CategoriesDto dto);
}



