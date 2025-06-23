package com.practice.controller.admin;

import com.practice.compilation.dto.CompilationCreateDto;
import com.practice.compilation.dto.CompilationMapper;
import com.practice.compilation.dto.CompilationResponseDto;
import com.practice.compilation.model.Compilation;
import com.practice.compilation.service.CompilationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
public class AdminCompilationController {
    private final CompilationService compilationService;
    private final CompilationMapper compilationMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CompilationResponseDto create(@RequestBody @Valid CompilationCreateDto compilationCreate) {
        Compilation compilation = compilationMapper.fromCreate(compilationCreate);
        return compilationMapper.toResponse(compilationService.create(compilation));
    }
}
