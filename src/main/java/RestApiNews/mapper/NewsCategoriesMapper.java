package RestApiNews.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import RestApiNews.dto.NewsCategoriesDto;
import RestApiNews.entity.NewsCategories;
import RestApiNews.entity.News;
import RestApiNews.entity.Categories;

@Mapper(componentModel = "spring")
public interface NewsCategoriesMapper {

    @Mapping(source = "news", target = "news", qualifiedByName = "mapNewsToWrapper")
    @Mapping(source = "categories", target = "categories", qualifiedByName = "mapCategoriesToWrapper")
    NewsCategoriesDto toDto(NewsCategories entity);

    // Вспомогательные методы для маппинга вложенных объектов

    @Named("mapNewsToWrapper")
    default NewsCategoriesDto.NewsIdWrapper mapNewsToWrapper(News news) {
        if (news == null) {
            return null;
        }
        NewsCategoriesDto.NewsIdWrapper wrapper = new NewsCategoriesDto.NewsIdWrapper();
        wrapper.setId(news.getId());
        return wrapper;
    }

    @Named("mapCategoriesToWrapper")
    default NewsCategoriesDto.CategoriesIdWrapper mapCategoriesToWrapper(Categories categories) {
        if (categories == null) {
            return null;
        }
        NewsCategoriesDto.CategoriesIdWrapper wrapper = new NewsCategoriesDto.CategoriesIdWrapper();
        wrapper.setId(categories.getId());
        return wrapper;
    }

    // toEntity маппинг можно реализовать вручную или добавить при необходимости
}
