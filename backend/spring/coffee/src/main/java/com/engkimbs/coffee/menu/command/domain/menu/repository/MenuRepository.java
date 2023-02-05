package com.engkimbs.coffee.menu.command.domain.menu.repository;

import com.engkimbs.coffee.menu.command.domain.menu.entity.Menu;
import com.engkimbs.coffee.menu.command.domain.menu.vo.MenuId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, MenuId> {
}
