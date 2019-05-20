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
package org.yellowsneakers.business.system.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.yellowsneakers.business.system.model.SystemModel;
import org.yellowsneakers.business.system.service.ISystemService;
import org.yellowsneakers.core.bean.BeanKits;
import org.yellowsneakers.generic.utils.CommonConstants;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 
 * @author tang
 *
 * @param <M>
 * @param <T>
 */
public class SystemServiceImpl<M extends BaseMapper<T>, T extends SystemModel> extends ServiceImpl<M, T> implements ISystemService<T> {

    private Class<T> modelClass;

    @SuppressWarnings("unchecked")
    public SystemServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        this.modelClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[1];
    }
    
	@Override
	public boolean deleteLogic(List<Integer> ids) {
        List<T> entityList = new ArrayList<>();
        ids.forEach(id->{
            T entity = BeanKits.newInstance(modelClass);
            entity.setId(id);
            entity.setStatus(CommonConstants.BUSY_ORDER_STATUS_DRAFT);
            entityList.add(entity);
        });
        return super.updateBatchById(entityList);
	}

}
