package com.libr.service.impl;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.libr.dao.BookDao;
import com.libr.dao.impl.BookDaoImpl;
import com.libr.dao.impl.UserInfoDaoImpl;
import com.libr.entity.Book;
import com.libr.entity.UserInfo;
import com.libr.service.AdminService;

public class AdminServiceImpl implements AdminService {

	private BookDao BookDao;

	// 构造函数，传入数据库连接对象
	public AdminServiceImpl(Connection connection) {
		this.BookDao = new BookDaoImpl();
	}

	// 修改管理员密码根据id
	@Override
    public void changePassword(String newPassword) {
        BookDao.getPassword(String passwd){
        }System.out.println("密码修改成功！");

	}

	// 添加管理员
	@Override
	public void addAdmin() {
		// 创建一个新的管理员对象
		UserInfo newAdmin = new UserInfo();
		// 输入名称
		System.out.print("请输入管理员名称：");
		Scanner scan = new Scanner(System.in);
		String newname = scan.nextLine();
		newAdmin.setUserName(newname);
		// 输入密码
		boolean sign = false;
		while (!sign) {

			System.out.print("请输入密码：");
			String newPassWd1 = scan.nextLine();
			System.out.print("请再次输入密码：");
			String newPassWd2 = scan.nextLine();
			if (newPassWd1.equals(newPassWd2)) {
				newAdmin.setUserPassword(newPassWd1);
				sign = true;
			} else {
				System.out.println("密码不统一，请重新输入");
			}
		}
		newAdmin.setUserAdmin(true);

		// 使用UserInfoDaoImpl来插入管理员
		try {
			UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();
			userInfoDaoImpl.insert(newAdmin);
		} catch (Exception e) {
			System.out.println("管理员添加失败！");
			e.printStackTrace();
		}finally {
			System.out.println("管理员添加成功！");
		}
	}


	// 添加新图书
	@Override
	public void addBook(Book book) {
		BookDao.insert(book);
	}

	// 修改图书信息
	@Override
	public void modifyBook(int bookId, String newName, String newType, int newNumber) {
		Book book = BookDao.getOneById(bookId);
		if (book != null) {
			book.setBookName(newName);
			book.setBookType(newType);
			book.setBookNumber(newNumber);
			BookDao.update(book);
			System.out.println("图书信息修改成功: " + book.getBookName());
		} else {
			System.out.println("没有找到ID为 " + bookId + " 的图书");
		}
	}

	// 删除图书
	@Override
	public void deleteBook(int bookId) {
		BookDao.deleteBook(bookId);
	}

	// 根据ID查询图书
	@Override
	public void searchBookById(int bookId) {
		Book book = BookDao.findBookById(bookId);
		if (book != null) {
			System.out.println("找到图书: " + book);
		} else {
			System.out.println("没有找到ID为 " + bookId + " 的图书");
		}
	}

	// 根据名称搜索图书
	@Override
	public void searchBookByName(String keyword) {
		List<Book> books = BookDao.searchBooksByName(keyword);
		if (books.isEmpty()) {
			System.out.println("没有找到包含关键词 '" + keyword + "' 的图书");
		} else {
			books.forEach(book -> System.out.println("找到图书: " + book));
		}
	}

	// 根据日期搜索图书
	@Override
	public void searchBookByDate(LocalDate date) {
		List<Book> books = BookDao.searchBooksByDate(date);
		if (books.isEmpty()) {
			System.out.println("没有找到日期为 " + date + " 的图书");
		} else {
			books.forEach(book -> System.out.println("找到图书: " + book));
		}
	}

	@Override
	public void viewBorrowRecordsById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewUnreturnedRecordsById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void viewBookStatement() {
		// TODO Auto-generated method stub

	}

}
