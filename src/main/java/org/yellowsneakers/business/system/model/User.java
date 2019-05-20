/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yellowsneakers.business.system.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  登录用户实体类
 *
 * @author tang
 * @since 2018-04-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blade_user")
public class User extends SystemModel {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String name;
    /**
     * 真名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
    @ApiModelProperty(value = "手机")
    private String phone;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 角色id
     */
    @TableField("role_id")
    private String roleId;
    /**
     * 部门id
     */
    @TableField("dept_id")
    private String deptId;
    
    @TableField(exist=false)
    private String sexName;
    @TableField(exist=false)
    private String roleName;
    @TableField(exist=false)
    private String deptName;
    @TableField(exist=false)
    private String newPassword;
}
