package RestApiNews.controller;

import javax.validation.Valid;
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

    protected abstract D toDto(T entity);
    protected abstract T toEntity(D dto);

    @GetMapping
    public ResponseEntity<List<D>> getAll() {
        List<T> entities = service.read();
        if (entities.isEmpty()) {
            throw new IllegalStateException("Список объектов пуст");
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
            throw new IllegalArgumentException("Объект с ID " + id + " не найден");
        }
        return new ResponseEntity<>(toDto(entity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody @Valid D dto) {
        T entity = toEntity(dto);
        service.save(entity);
        return new ResponseEntity<>(toDto(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable Long id, @RequestBody @Valid D dto) {
        T existing = service.read(id);
        if (existing == null) {
            throw new IllegalArgumentException("Невозможно обновить: объект с ID " + id + " не найден");
        }

        T updated = toEntity(dto);
        updated.setId(id);
        service.edit(updated);
        return new ResponseEntity<>(toDto(updated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        T entity = service.read(id);
        if (entity == null) {
            throw new IllegalArgumentException("Удаление невозможно: объект с ID " + id + " не найден");
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
