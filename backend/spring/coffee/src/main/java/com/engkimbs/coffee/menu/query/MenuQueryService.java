package com.engkimbs.coffee.menu.query;

import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import com.engkimbs.coffee.menu.exception.MenuErrorCode;
import com.engkimbs.coffee.menu.exception.MenuException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuQueryService {

    public MenuDataDao menuDataDao;

    public MenuDataSummaryDao menuDataSummaryDao;

    public MenuQueryService(
            MenuDataDao menuDataDao,
            MenuDataSummaryDao menuDataSummaryDao
            ) {
        this.menuDataDao = menuDataDao;
        this.menuDataSummaryDao = menuDataSummaryDao;
    }

    @Cacheable(value = "menu", key = "#menuId.id")
    public MenuData getMenuDataByMenuId(MenuId menuId) {
        MenuData menuData = menuDataDao.findById(menuId)
                .orElseThrow(() -> new MenuException(MenuErrorCode.MENU_NOT_FOUND, menuId.getId() + " is not founded"));
        return menuData;
    }

    @Cacheable(value = "best", key = "#topNBest + ':' + #lastMDays")
    @Transactional(readOnly = true)
    public List<MenuDataSummary> getTopNBestMenuListInLastMDays(int topNBest, int lastMDays) {
        LocalDateTime lastMDaysAgo = LocalDateTime.now().minusDays(lastMDays);
        return menuDataSummaryDao.findTopNBestMenuListInLastMDays(topNBest, lastMDaysAgo);
    }
}