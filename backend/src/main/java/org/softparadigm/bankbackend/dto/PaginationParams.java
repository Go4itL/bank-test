package org.softparadigm.bankbackend.dto;

import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

public class PaginationParams {
    private int page;
    private int size;

    private Map<String, String> filters = new HashMap<>();
    private Map<String, Sort.Direction> sorts = new HashMap<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public Map<String, Sort.Direction> getSorts() {
        return sorts;
    }

    public void setSorts(Map<String, Sort.Direction> sorts) {
        this.sorts = sorts;
    }
}
