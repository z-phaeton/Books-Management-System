package com.libr.dao;

import java.util.Date;
import java.util.List;

import com.libr.entity.Book;

public interface BookDao extends BaseDao {
	//插入
	int insertBook(Book p);
	//更新
	int updateBook(Book p);
	
	int deleteById(int id);
	
	Book getOneById(int id);
	
//	List<Book> getOneByDate(Date day);
	
	List<Book> getOneByAuthor(String name);
	
	List<Book> getAll();//把所有全列出来
	
	List<Book> getOneByBookName(String name);
	
	List<Book> getAllByKeyword(String keyword);
	
	// 根据图书id查询图书的在馆的数量，位置
	List<Book> viewBookStatement(int bid);
	
	// 根据用户id查询用户未还的图书记录
	List<Book> viewUnreturnedRecordsById(int user_id);
	
	//根据图书关键词查询图书在管数量，位置 
	List<Book> viewBookStatementByKeywords(String bname);
	
}