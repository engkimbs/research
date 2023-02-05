package com.engkimbs.coffee.menu.query;

import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuDataSummaryDao extends JpaRepository<MenuDataSummary, MenuId> {

    @Query(value = """
            select
             popular_menu.menu_id,
             menu.menu_name,
             row_number() over (order by cnt desc) ranking,
             cnt
            from (
            select menu_id, sum(quantity) as cnt
            from order_line
            where order_date > :lastNDays
            group by menu_id) popular_menu join menu on popular_menu.menu_id = menu.menu_id
            where rownum <= :topNBest
            order by cnt desc
    """, nativeQuery = true)
    public List<MenuDataSummary> findTopNBestMenuListInLastMDays(@Param("topNBest") int topNBest, @Param("lastNDays") LocalDateTime localDateTime);
}
