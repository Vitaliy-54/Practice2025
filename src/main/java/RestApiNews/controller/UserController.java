package RestApiNews.controller;

import RestApiNews.dto.UserDto;
import RestApiNews.entity.User;
import RestApiNews.mapper.UserMapper;
import RestApiNews.security.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserDto> userDtos = users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        if (userService.findByUsername(userDto.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        User user = userMapper.toEntity(userDto);
        userService.saveUser(user);
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
