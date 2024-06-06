package com.mateus.urlshortener.mapper;

import com.mateus.urlshortener.domain.Url;
import com.mateus.urlshortener.dto.UrlDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UrlMapper {
    UrlDto toUrlDto(Url url);
    Url toUrl(UrlDto urlDto);
}
