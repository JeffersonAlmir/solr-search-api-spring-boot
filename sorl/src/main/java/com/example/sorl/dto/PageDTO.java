package com.example.sorl.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageDTO<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last,
        boolean first,
        boolean hasNext,
        boolean hasPrevious
) {
    public PageDTO(Page<T> page){
        this(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}
