package org.eclipse.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="author")
public class Author {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy="author", cascade= {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<Book> books;

	public Author(long id, String name, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.books = books;
	}
	public Author(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Author() {
		super();
		this.books = new ArrayList<Book>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonManagedReference(value="books")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}
	
	public void deleteBook(long idBook) {
		for (Book book: this.books) {
			if (book.getId() == idBook) {
				this.books.remove(book);
				return;
			}
		}
	}
	
	public void deleteBook(Book book) {
		for(Book b: this.books) {
			if (book.getId() == b.getId()) {
				this.books.remove(b);
				return;
			}
		}
	}
}
