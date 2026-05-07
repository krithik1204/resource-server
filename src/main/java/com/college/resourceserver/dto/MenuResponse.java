package com.college.resourceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
    
    private Long id;
    private String name;
    private String path;
    private String icon;
    private String role;
    private Long parentId;
    private List<MenuResponse> children;
}
