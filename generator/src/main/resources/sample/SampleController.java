package cn.exrick.xboot.modules.web.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cn.exrick.xboot.common.utils.PageUtil;
import cn.exrick.xboot.common.utils.ResultUtil;
import cn.exrick.xboot.common.vo.PageVo;
import cn.exrick.xboot.common.vo.Result;
import cn.exrick.xboot.modules.base.mpbase.controller.BaseController;
import cn.exrick.xboot.modules.base.mpbase.BaseService;
import cn.exrick.xboot.modules.web.entity.##ClassEntityName#;
import cn.exrick.xboot.modules.web.service.##ClassServiceName#;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/xboot/##className#")
public class ##ClassNameSuffix# extends BaseController<##ClassEntityName#, String> {
	@Autowired
	HttpServletRequest request;
	@Autowired
	##ClassServiceName# service;
	@Override
	public BaseService<##ClassEntityName#, String> getService() {
		return service;
	}

	// get list by query and page
	@RequestMapping(value = "/getByCondition", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "分页获取")
	public Result<Page<##ClassEntityName#>> getByConditionPage(PageVo page, ##ClassEntityName# bean) {
		QueryWrapper<##ClassEntityName#> qw = new QueryWrapper<>();
		##PaginationQueryList#
		Page<##ClassEntityName#> data = service.findAll(PageUtil.initMpPage(page), qw);
		return new ResultUtil<Page<##ClassEntityName#>>().setData(data);
	}

}
