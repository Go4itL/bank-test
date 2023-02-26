package org.softparadigm.bankbackend.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDto<DTO> {
    private long size;
    private List<DTO> content = new ArrayList<>();

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<DTO> getContent() {
        return content;
    }

    public void setContent(List<DTO> content) {
        this.content = content;
    }
}
