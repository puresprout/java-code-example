/*
 * Main.java
 *
 * Created on 2004년 2월 12일 (목), 오전 10:35
 */

package designPattern.iterator;

/**
 *
 * @author  박성현
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(10);
        bookShelf.appendBook(new Book("무궁화 꽃이 피었습니다"));
        bookShelf.appendBook(new Book("The purpose: driven life"));
        
        Iterator it = bookShelf.iterator();
        
        while (it.hasNext())
        {
            Book book = (Book) it.next();
            System.out.println(book.getName());
        }
        
    }
    
}
