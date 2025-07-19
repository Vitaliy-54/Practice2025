package RestApiNews.mapper;

import RestApiNews.dto.NewsCategoriesDto;
import RestApiNews.entity.NewsCategories;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-19T22:23:01+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class NewsCategoriesMapperImpl implements NewsCategoriesMapper {

    @Override
    public NewsCategoriesDto toDto(NewsCategories entity) {
        if ( entity == null ) {
            return null;
        }

        NewsCategoriesDto newsCategoriesDto = new NewsCategoriesDto();

        newsCategoriesDto.setNews( mapNewsToWrapper( entity.getNews() ) );
        newsCategoriesDto.setCategories( mapCategoriesToWrapper( entity.getCategories() ) );
        newsCategoriesDto.setId( entity.getId() );

        return newsCategoriesDto;
    }
}
