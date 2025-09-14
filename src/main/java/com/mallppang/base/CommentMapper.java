package com.mallppang.base;

import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
	protected void entityToDTO(BaseComment entity, CommentDTO dto) {
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreateDate(entity.getCreateDate());
        dto.setDelFlag(entity.isDelFlag());
    }

    protected void dtoToEntity(CommentDTO dto, BaseComment entity) {
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setCreateDate(dto.getCreateDate());
        entity.setDelFlag(dto.isDelFlag());
    }
}
