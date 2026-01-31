package com.semihkurucay.mapper;

import org.mapstruct.Mapper;

import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.entity.User;

@Mapper(componentModel = "spring")
public interface IUserMapper {

	public DtoUser toDto(User user);
}
