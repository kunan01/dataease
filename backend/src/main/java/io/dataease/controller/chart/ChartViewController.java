package io.dataease.controller.chart;

import io.dataease.base.domain.ChartViewWithBLOBs;
import io.dataease.commons.utils.AuthUtils;
import io.dataease.controller.request.chart.ChartExtRequest;
import io.dataease.controller.request.chart.ChartViewRequest;
import io.dataease.controller.request.dataset.DataSetTableRequest;
import io.dataease.dto.chart.ChartViewDTO;
import io.dataease.dto.dataset.DataSetTableDTO;
import io.dataease.service.chart.ChartViewService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author gin
 * @Date 2021/3/1 1:17 下午
 */
@RestController
@RequestMapping("/chart/view")
public class ChartViewController {
    @Resource
    private ChartViewService chartViewService;

    @PostMapping("/save")
    public ChartViewWithBLOBs save(@RequestBody ChartViewWithBLOBs chartViewWithBLOBs) {
        return chartViewService.save(chartViewWithBLOBs);
    }

    @PostMapping("/list")
    public List<ChartViewDTO> list(@RequestBody ChartViewRequest chartViewRequest) {
        return chartViewService.list(chartViewRequest);
    }

    @PostMapping("/listAndGroup")
    public List<ChartViewDTO> listAndGroup(@RequestBody ChartViewRequest chartViewRequest) {
        return chartViewService.listAndGroup(chartViewRequest);
    }

    @PostMapping("/get/{id}")
    public ChartViewWithBLOBs get(@PathVariable String id) {
        return chartViewService.get(id);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        chartViewService.delete(id);
    }

    @PostMapping("/getData/{id}")
    public ChartViewDTO getData(@PathVariable String id, @RequestBody ChartExtRequest requestList) throws Exception {
        return chartViewService.getData(id, requestList);
    }

    @PostMapping("chartDetail/{id}")
    public Map<String, Object> chartDetail(@PathVariable String id) {
        return chartViewService.getChartDetail(id);
    }

    @PostMapping("chartCopy/{id}")
    public String chartCopy(@PathVariable String id) {
        return chartViewService.chartCopy(id);
    }

    @GetMapping("searchAdviceSceneId/{panelId}")
    public String searchAdviceSceneId(@PathVariable String panelId) {
        return chartViewService.searchAdviceSceneId(panelId);
    }

    @PostMapping("/getOneWithPermission/{id}")
    public ChartViewDTO getOneWithPermission(@PathVariable String id, @RequestBody ChartExtRequest requestList) throws Exception {
        //如果能获取用户 则添加对应的权限
        ChartViewDTO dto = chartViewService.getData(id, requestList);
        if (dto != null && AuthUtils.getUser() != null) {
            ChartViewDTO permissionDto = chartViewService.getOneWithPermission(dto.getId());
            dto.setPrivileges(permissionDto.getPrivileges());
        }
        return dto;
    }


    @PostMapping("search")
    public List<ChartViewDTO> search(@RequestBody ChartViewRequest chartViewRequest) {
        return chartViewService.search(chartViewRequest);
    }
}
