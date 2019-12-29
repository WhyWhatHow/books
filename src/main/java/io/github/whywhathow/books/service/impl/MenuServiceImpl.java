package io.github.whywhathow.books.service.impl;

import io.github.whywhathow.books.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.whywhathow.books.mapper.MenuMapper;
import io.github.whywhathow.books.pojo.Menu;
import io.github.whywhathow.books.pojo.Role;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper mapper ;
    @Override
    public List<Menu> getMenuByRole(int rid) {
        return mapper.selectByRole(rid);
    }

    @Override
    public int insertMenu(Menu menu) {
        return 0;
    }
}
