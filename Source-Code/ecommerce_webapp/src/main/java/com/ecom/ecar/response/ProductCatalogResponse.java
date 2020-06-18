/*
|--------------------------------------------------------------------------
| ProductCatalogResponse java class
|--------------------------------------------------------------------------
|
|ProductCatalogResponse class is used for retrieve all information need to display the home page
|
*/
package com.ecom.ecar.response;

import com.ecom.ecar.entity.Book;

import java.util.List;

public class ProductCatalogResponse {


    private List<Book> books;

    private List<String> categories;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
