/*
 * BookShelf.java
 *
 * Created on 2004�� 2�� 12�� (��), ���� 10:22
 */

package designPattern.iterator;

public class BookShelf implements Aggregate {
    
    private Book[] books;
    
    private int last = 0;
    
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
    
    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }
    
    public Book getBookAt(int index) {
        return this.books[index];
    }
    
    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }
    
    public int getLength() {
        return last;
    }
    
}
