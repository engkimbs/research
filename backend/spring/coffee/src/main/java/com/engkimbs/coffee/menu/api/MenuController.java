package com.engkimbs.coffee.menu.api;

import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.menu.query.MenuData;
import com.engkimbs.coffee.menu.query.MenuDataSummary;
import com.engkimbs.coffee.menu.query.MenuQueryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuQueryService menuQueryService;

    public MenuController(MenuQueryService menuQueryService) {
        this.menuQueryService = menuQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long id) {
        MenuData menuData = menuQueryService.getMenuDataByMenuId(MenuId.of(id));
        return ResponseEntity.ok().body(MenuResponse.of(menuData));
    }

    @GetMapping("/best-menus")
    public ResponseEntity<Result<List<MenuDataSummary>>> getTop3BestMenusInLast7Days() {
        List<MenuDataSummary> menuDataSummaryList = menuQueryService.getTopNBestMenuListInLastMDays(3, 7);
        return ResponseEntity.ok().body(new Result<>(menuDataSummaryList, menuDataSummaryList.size()));
    }

    @Getter
    @Setter
    static class Result<T> {
        private T data;
        private int count;

        public Result(T data, int count) {
            this.data = data;
            this.count = count;
        }
    }
}