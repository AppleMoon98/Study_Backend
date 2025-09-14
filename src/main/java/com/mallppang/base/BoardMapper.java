package com.mallppang.base;

import org.springframework.stereotype.Component;

@Component
public class BoardMapper {
	
    protected void entityToDTO(BaseBoard entity, BaseDTO dto) {
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreateDate(entity.getCreateDate());
        dto.setDelFlag(entity.isDelFlag());
    }

    protected void dtoToEntity(BaseDTO dto, BaseBoard entity) {
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setCreateDate(dto.getCreateDate());
        entity.setDelFlag(dto.isDelFlag());
    }
}

