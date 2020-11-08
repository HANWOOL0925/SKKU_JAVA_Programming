//Library와 Book object를 만들어 책을 add, find, borrow, return하는 기능을 구현하여라.
//error handling - exception 사용할 것.

class LibraryFullException extends RuntimeException{

}

class NoBookException extends RuntimeException{

}

class BorrowBookException extends RuntimeException{

}

class Library{
    int BoNum;
    int count = 0;
    Book books[];

    public Library(int booknumber) {
        this.BoNum = booknumber;
        books = new Book[booknumber];
    }

    public void addBook(Book book) throws LibraryFullException {
        if (count < BoNum) {
            books[count] = book;
            count++; 
        }
        else{
            throw new LibraryFullException();
        }
    }
    public void findBook(String name) throws NoBookException {
        int i;
        for (i = 0; i < books.length; i++){
            if(books[i].name.equals(name)){
                i = books.length + 1;
            }
        }
        if ( i == books.length){
            throw new NoBookException();
        }  
    }

    public void borrowBook(String name) throws NoBookException, BorrowBookException {
        int i;
        for (i = 0; i < books.length; i++) {
            if (books[i].name.equals(name) && books[i].state == true) {
                System.out.println("Found Book");
                books[i].state = false;
                i = books.length + 1;
            } else if (books[i].name.equals(name)) {
                throw new BorrowBookException();
            }
        }
        if (i == books.length) {
            throw new NoBookException();
        }
    }

    void returnBook(String name) throws NoBookException, BorrowBookException {
        int i;
        for (i = 0; i < books.length; i++) {
            if (books[i].name.equals(name) && books[i].state == true) {
                throw new BorrowBookException();
            } else if (books[i].name.equals(name)) {
                books[i].state = true;
                i = books.length + 1;
            }
        }
        if (i == books.length) {
            throw new NoBookException();
        }
    }
}

class Book {
    String name;
    Boolean state;

    public Book(String bookname) {
        name = bookname;
        state = true;
    }
}

public class LA6_LibrarySystem {
    public static void main(String args[]) {

        Library lib = new Library(1);
        try {
            lib.addBook(new Book("K&R"));
        } catch (LibraryFullException e) {
            System.out.println("Library is Full");
        }
        try {
            lib.addBook(new Book("2046"));
        } catch (LibraryFullException e) {
            System.out.println("Library is Full");
        }
        try {
            lib.findBook("Window is garbage");
        } catch (NoBookException e) {
            System.out.println("No book with that name");
        }
        try {
            lib.borrowBook("Window is garbage");
        } catch (NoBookException e) {
            System.out.println("No book with that name");
        } catch (BorrowBookException e) {
            System.out.println("Book is already borrowed");
        }
        try {
            lib.borrowBook("K&R");
        } catch (NoBookException e) {
            System.out.println("No book with that name");
        } catch (BorrowBookException e) {
            System.out.println("Book is already borrowed");
        }
        try {
            lib.returnBook("2045");
        } catch(NoBookException e){
            System.out.println("No book with that name");
        } catch(BorrowBookException e){
            System.out.println("Book is not borrowed");
        }
    }
}