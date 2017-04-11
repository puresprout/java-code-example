/*
 * BookShelfIterator.java
 *
 * Created on 2004�� 2�� 12�� (��), ���� 10:27
 */

package designPattern.iterator;

public class BookShelfIterator implements Iterator {
    
    private BookShelf bookShelf;
    private int index;
    
    /** Creates a new instance of BookShelfIterator */
    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }
    
    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        } else {
            return false;
        }
    }
    
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        
        return book;
    }
    
}
