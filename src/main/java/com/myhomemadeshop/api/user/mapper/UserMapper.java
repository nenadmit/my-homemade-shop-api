package com.myhomemadeshop.api.user.mapper;

import com.myhomemadeshop.api.infrastructure.mapper.EntityMapper;
import com.myhomemadeshop.api.company.domain.entity.CompanyActivity;
import com.myhomemadeshop.api.user.domain.User;
import com.myhomemadeshop.api.user.web.dtos.UserRequestDto;
import com.myhomemadeshop.api.user.web.dtos.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserRequestDto, UserResponseDto> {

  default Set<CompanyActivity> mapActivityNameToActivity(Set<String> activities) {
    return activities.stream().map(activity -> new CompanyActivity()).collect(Collectors.toSet());
  }

    default Set<String> mapActivityToActivityName(Set<CompanyActivity> activities) {
        return activities.stream().map(activity -> activity.getName()).collect(Collectors.toSet());
    }
}
