package com.mateus.urlshortener.mapper;

import com.mateus.urlshortener.domain.UrlModel;
import com.mateus.urlshortener.dto.UrlDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UrlMapper {
    UrlDto toUrlDto(UrlModel urlModel);
    UrlModel toUrlModel(UrlDto urlDto);
}
