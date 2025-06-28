package com.practice.compilation.service;

import com.practice.common.exception.ConflictException;
import com.practice.common.exception.NotFoundException;
import com.practice.compilation.model.Compilation;
import com.practice.compilation.repository.CompilationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Compilation update(Integer compId, Compilation updCompilation) {
        if (compId == null || compId == 0) {
            throw new ConflictException("id подборки не может быть null или 0");
        }

        Compilation compilation = compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException("Подборка с id: " + compId + " не найден"));

        compilation.setTitle(updCompilation.getTitle());
        compilation.setPinned(updCompilation.getPinned());
        compilation.setEvents(updCompilation.getEvents());

        return compilationRepository.save(compilation);
    }
}
