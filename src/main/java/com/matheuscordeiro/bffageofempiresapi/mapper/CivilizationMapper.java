package com.matheuscordeiro.bffageofempiresapi.mapper;

import com.matheuscordeiro.bffageofempiresapi.clients.response.CivilizationClientResponse;
import com.matheuscordeiro.bffageofempiresapi.dtos.response.CivilizationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CivilizationMapper {
    CivilizationResponse toCivilizationResponse(CivilizationClientResponse civilizationClientResponse);
}
