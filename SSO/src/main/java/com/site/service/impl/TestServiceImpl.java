package com.site.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.ResTestDto;
import com.site.mapper.TestMapper;
import com.site.model.Test;
import com.site.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<ResTestDto> test() {
		logger.info("start test!");
		List<ResTestDto> lists = new ArrayList<ResTestDto>();
		List<Test> list = testMapper.getTests();
		for (Test supplier : list) {
			ResTestDto res = new ResTestDto();
			BeanUtils.copyProperties(supplier, res);
			lists.add(res);
		}
		return lists;
	}

}
