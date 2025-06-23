package com.practice.compilation.service;

import com.practice.common.exception.ConflictException;
import com.practice.compilation.model.Compilation;
import com.practice.compilation.repository.CompilationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CompilationService {
    private final CompilationRepository compilationRepository;

    public Compilation create(Compilation compilation) {
        if (compilation.getTitle().isBlank()) {
            throw new ConflictException("Заголовок не может быть пустым");
        }

        if (compilation.getPinned() == null) {
            compilation.setPinned(false);
        }

        return compilationRepository.save(compilation);
    }
}
