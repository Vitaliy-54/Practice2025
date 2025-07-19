package RestApiNews.mapper;

import org.mapstruct.*;
import RestApiNews.dto.NewsDto;
import RestApiNews.entity.News;
import RestApiNews.entity.NewsCategories;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(target = "categories", expression = "java(mapCategories(entity))")
    NewsDto toDto(News entity);

    @InheritInverseConfiguration
    @Mapping(target = "newsCategories", ignore = true)
    News toEntity(NewsDto dto);

    // helper method
    default List<String> mapCategories(News entity) {
        if (entity.getNewsCategories() == null) {
            return null;
        }
        return entity.getNewsCategories()
                .stream()
                .map(nc -> nc.getCategories().getName())
                .collect(Collectors.toList());
    }
}