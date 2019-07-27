// IBookManager.aidl
package com.competition.pdking.ipcdemo;

// Declare any non-default types here with import statements

import com.competition.pdking.ipcdemo.Book;
interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
}
