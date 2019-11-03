package io.github.whywhathow.books.service;




import io.github.whywhathow.books.pojo.Menu;
import io.github.whywhathow.books.pojo.Role;

import java.util.List;

public interface  MenuService {
    // 根据rid 返回 permission 集合
    List<Menu> getMenuByRole(Role role);

    int insertMenu(Menu menu);
}
