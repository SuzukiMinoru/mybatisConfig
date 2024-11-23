package com.yz.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;


@Data
@Alias("City")
@TableName("city")
public class City {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("Name")
    private String cityName;

    @TableField("CountryCode")
    private String countryCode;

    @TableField("District")
    private String district;

    @TableField("Population")
    private Integer population;

}
