package com.example.ia.library;

import java.util.Scanner;

public class NeighborhoodLibrary {

    static Book[] books = {
            new Book(100, "0865477639", "West With the Night", false, ""),
            new Book(101, "0439352436", "Talkin' About Bessie", false, ""),
            new Book(102, "030681837X", "East to the Dawn", false, ""),
            new Book(103, "0151103720", "Aviatrix", false, ""),
            new Book(104, "0688091121", "To Space and Back", false, "")
    };

    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        boolean done = false;

        // Display Home Screen
        while (!done) {
            System.out.println("Welcome to Your Neighborhood Library!\n");

            System.out.println("1. Borrow a Book");
            System.out.println("2. Return a Book");
            System.out.println("3. Exit\n");

            System.out.print("Please enter a number:  ");


            String input = scanner.nextLine();
            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    showBorrowBooksScreen();
                    break;
                case 2:
                    showReturnBooksScreen();
                    break;
                case 3:
                    System.out.println("Thanks, come again!");
                    done = true;
                    break;
                default:
                    System.out.println("I'm sorry, I didn't understand that; Try again:");
                    break;
            }
        }
    }

    private static void showBorrowBooksScreen() {
        boolean done = false;
        do {
            System.out.print("Here are the books we have available\n\n");
            System.out.print("ID   Title\n");
            System.out.print("--   -----\n");

            // List the books that are available (not checked out)
            for (Book b : books) {
                if (!b.isCheckedOut()) {
                    System.out.println(b.getId() + "  " + b.getTitle());
                }
            }
            System.out.println("\nTo borrow a book, please enter the book ID");
            System.out.print("... or 0 to return to the Home Screen:  ");

            String input = scanner.nextLine();
            int selectedBookId = Integer.parseInt(input);

            if (selectedBookId == 0) {
                done = true;
            } else {
                Book selectedBook = findBookById(selectedBookId);
                if (selectedBook == null) {
                    System.out.println("I'm sorry; I didn't understand that.  Try again");
                } else {
                    confirmCheckOut(selectedBook);
                    done = true;
                }
            }
        } while (!done);
    }

    private static void confirmCheckOut(Book book) {
        System.out.println("Checking out " + book.getTitle());
        System.out.print("Please enter your name: ");

        String borrowerName = scanner.nextLine();
        book.checkOut(borrowerName);

        System.out.printf("Thank you, %s; Enjoy reading %s !\n", book.getCheckedOutTo(), book.getTitle());
    }


    private static void showReturnBooksScreen() {

        boolean done = false;
        do {
            System.out.print("\n\n");
            System.out.print("Here are the books that are currently on loan\n\n");
            System.out.print("ID   Title\n");
            System.out.print("--   -----\n");

            // List books that are checked out
            for (Book b : books) {
                if (b.isCheckedOut()) {
                    System.out.println(b.getId() + "  " + b.getTitle());
                }
            }
            System.out.println();
            System.out.println("To Return a book, enter the book ID");
            System.out.println("or enter 0 to return to Home Screen\n");
            System.out.print("Please enter a number:  ");


            String input = scanner.nextLine();
            int selectedBookId = Integer.parseInt(input);

            if (selectedBookId == 0) {
                done = true;
            } else {
                Book selectedBook = findBookById(selectedBookId);
                if (selectedBook == null) {
                    System.out.println("I'm sorry; I didn't understand that.  Try again");
                } else {
                    confirmReturn(selectedBook);
                    done = true;
                }
            }
        } while (!done);
    }

    private static void confirmReturn(Book book) {
        System.out.printf("Thank you, %s for returning %s !\n", book.getCheckedOutTo(), book.getTitle());
        book.checkIn();
    }

    private static Book findBookById(int id) {

        for (Book b: books){
            if (id == b.getId()){
                return b;
            }
        }
        return null;
    }


}
