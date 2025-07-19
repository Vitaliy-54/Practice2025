package RestApiNews.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import RestApiNews.dto.UserDto;
import RestApiNews.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);
}


