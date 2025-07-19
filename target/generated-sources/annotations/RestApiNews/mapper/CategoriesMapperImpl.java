package RestApiNews.mapper;

import RestApiNews.dto.CategoriesDto;
import RestApiNews.entity.Categories;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-19T20:35:24+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CategoriesMapperImpl implements CategoriesMapper {

    @Override
    public CategoriesDto toDto(Categories entity) {
        if ( entity == null ) {
            return null;
        }

        CategoriesDto categoriesDto = new CategoriesDto();

        categoriesDto.setId( entity.getId() );
        categoriesDto.setName( entity.getName() );

        return categoriesDto;
    }

    @Override
    public Categories toEntity(CategoriesDto dto) {
        if ( dto == null ) {
            return null;
        }

        Categories categories = new Categories();

        categories.setId( dto.getId() );
        categories.setName( dto.getName() );

        return categories;
    }
}
