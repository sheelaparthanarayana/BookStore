/*
|--------------------------------------------------------------------------
| Book java class
|--------------------------------------------------------------------------
|
|book class is user for handling all book object
|An book object is used for fetch a single book information from services
|There may have multiple books with different attributes
|
*/
package com.ecom.ecar.entity;


public class Book {
	
	private String bookid;
	
	private String title;
	
	private int price;
	
	private String author;

	private String category;

	public String getBookid() {
		return bookid;
	}
	/**
	 * @param bookid the bookid to set
	 */
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
}
