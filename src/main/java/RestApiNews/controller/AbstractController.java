package RestApiNews.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import RestApiNews.entity.AbstractEntity;
import RestApiNews.service.Service;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractController<T extends AbstractEntity, D> {

    protected final Service<T> service;

    public AbstractController(Service<T> service) {
        this.service = service;
    }

    // Метод, который возвращает маппер для преобразования между Entity и DTO
    protected abstract D toDto(T entity);
    protected abstract T toEntity(D dto);

    @GetMapping
    public ResponseEntity<List<D>> getAll() {
        List<T> entities = service.read();
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<D> dtos = entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable Long id) {
        T entity = service.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDto(entity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto) {
        T entity = toEntity(dto);
        service.save(entity);
        return new ResponseEntity<>(toDto(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable Long id, @RequestBody D dto) {
        T existingEntity = service.read(id);
        if (existingEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        T updatedEntity = toEntity(dto);
        updatedEntity.setId(id); // сохраняем ID
        service.edit(updatedEntity);
        return new ResponseEntity<>(toDto(updatedEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        T entity = service.read(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}