package io.github.whywhathow.books;

import io.github.whywhathow.books.mapper.UserMapper;
import io.github.whywhathow.books.pojo.Book;
import io.github.whywhathow.books.service.UserService;
import io.github.whywhathow.books.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceTest {

    @Autowired
    static UserMapper mapper;

    public static void test() {
        List<Book> books = mapper.borrowHistoryAll();
        for (Book book : books
        ) {
            System.err.println(book.getBname());

        }
    }

    public static void main(String[] args) {
        String uid = "95951edf4f9b49b9b791778fd76723b1";
//        test();
        UserService service = new UserServiceImpl();
        service.getUserBorrowHistoryByUid(uid);
    }

}
