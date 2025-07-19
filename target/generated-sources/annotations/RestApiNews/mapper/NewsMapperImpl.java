package RestApiNews.mapper;

import RestApiNews.dto.NewsDto;
import RestApiNews.entity.News;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-19T22:23:02+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDto toDto(News entity) {
        if ( entity == null ) {
            return null;
        }

        NewsDto newsDto = new NewsDto();

        newsDto.setId( entity.getId() );
        newsDto.setTitle( entity.getTitle() );
        newsDto.setContent( entity.getContent() );
        newsDto.setPublishDate( entity.getPublishDate() );

        newsDto.setCategories( mapCategories(entity) );

        return newsDto;
    }

    @Override
    public News toEntity(NewsDto dto) {
        if ( dto == null ) {
            return null;
        }

        News news = new News();

        news.setId( dto.getId() );
        news.setTitle( dto.getTitle() );
        news.setContent( dto.getContent() );
        news.setPublishDate( dto.getPublishDate() );

        return news;
    }
}
