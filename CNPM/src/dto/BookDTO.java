package dto;

public class BookDTO {
	private String isbn, bookTitle, publisher, description, dateReceived;
	private byte[] picture;
	private int authorId, genresId, quantity, id, price;

	public BookDTO() {
	}

	public BookDTO(int id, String isbn, String bookTitle, int authorId, int genresId, int quantity, String publisher,
			int price, String dateReceived, String description, byte[] picture) {
		this.id = id;
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.authorId = authorId;
		this.genresId = genresId;
		this.quantity = quantity;
		this.publisher = publisher;
		this.price = price;
		this.dateReceived = dateReceived;
		this.description = description;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getGenresId() {
		return genresId;
	}

	public void setGenresId(int genresId) {
		this.genresId = genresId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
