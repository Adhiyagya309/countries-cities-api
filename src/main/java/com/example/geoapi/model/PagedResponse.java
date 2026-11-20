package com.example.geoapi.model;

import java.util.List;

public record PagedResponse<T>(
        List<T> items,
        long totalRecords,
        int currentPage,
        int pageSize,
        int totalPages
) {
}
