package io.github.whywhathow.books.service;


import io.github.whywhathow.books.controller.BorrowVo;
import io.github.whywhathow.books.pojo.User;
import io.github.whywhathow.books.utils.Result;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    Result registerUser(User user);

    Result loginUser(User user, HttpServletRequest request);

    Result updateUser(User user);

    Result getMenu(User user);

//    Result sendACtiveCodeToUser(String uid, String email);

    Result sendACtiveCodeToUser(User email);

    Result checkUserName(String username);

    Result borrowBook(BorrowVo vo);

    Result returnBook(BorrowVo vo);

    Result payOwe(String uid);
}
