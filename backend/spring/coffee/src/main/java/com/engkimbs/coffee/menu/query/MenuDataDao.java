package com.engkimbs.coffee.menu.query;

import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDataDao extends JpaRepository<MenuData, MenuId> {
}
