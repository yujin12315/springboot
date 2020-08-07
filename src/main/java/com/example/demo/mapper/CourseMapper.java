package com.example.demo.mapper;

import com.example.demo.entity.Drools;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<Drools> getList(@Param("drlId") String drlId);

    List<String> getInfo(@Param("drlId") String drlId);


}
