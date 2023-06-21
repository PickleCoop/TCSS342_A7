/**
 * @author Evan Cooper
 * @version 5/6/2023
 */

import java.io.IOException;

public class UniqueWords {
    public BookReader book;
    //public MyArrayList<String> alOfUniqueWords;
    public MyLinkedList<String> llOfUniqueWords;
    //public MyOrderedList<String> olOfUniqueWords;
    //public MyBinarySearchTree<String> bstOfUniqueWords;
    public MyBinarySearchTree<String> avlOfUniqueWords;

    public UniqueWords(){
        try {
            book = new BookReader("WarAndPeace.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //alOfUniqueWords = new MyArrayList<>();
        llOfUniqueWords = new MyLinkedList<>();
        //olOfUniqueWords = new MyOrderedList<>();
        //bstOfUniqueWords = new MyBinarySearchTree<>();
        avlOfUniqueWords = new MyBinarySearchTree<>();
    }

    public void addUniqueWordsToLinkedList() {
        System.out.println("\nAdding Unique Words to Linked List...");
        long startTime = System.currentTimeMillis();
        book.words.first();
        for (int i = 0; i < book.words.size(); i++){
            if (!llOfUniqueWords.contains(book.words.current())){
                llOfUniqueWords.addBefore(book.words.current());
            }

            book.words.next();

        }
        long stopTime = System.currentTimeMillis();
        System.out.println(llOfUniqueWords.size() + " Unique Words Found.");
        System.out.println(llOfUniqueWords.comparisons + " Comparisons Made.");
        System.out.println("Initiating Sorting...");
        startTime = System.currentTimeMillis();
        llOfUniqueWords.sort();
        stopTime = System.currentTimeMillis();
        System.out.println("Linked List Sorted in " + ((double)(stopTime - startTime) / 1000) + " Seconds.");
    }

    /*public void addUniqueWordsToBST(){
        bstOfUniqueWords.comparisons++;
        bstOfUniqueWords.add(book.words.first());
        for(int i = 1; i < book.words.size(); i++){
            if(bstOfUniqueWords.find(book.words.next()) == null){
                bstOfUniqueWords.add(book.words.current());
            }
        }
    }*/

    public void addUniqueWordsToAVL(){
        long startTime = System.currentTimeMillis();
        avlOfUniqueWords.comparisons++;
        avlOfUniqueWords.add(book.words.first());
        for(int i = 1; i < book.words.size(); i++){
            if(avlOfUniqueWords.find(book.words.next()) == null){
                avlOfUniqueWords.add(book.words.current());
            }
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("\nTime to Traverse: " + (stopTime - startTime) + " Milliseconds.");
        System.out.println("Comparisons Made: " + avlOfUniqueWords.comparisons + ".");
        System.out.println("Height of Tree: " + avlOfUniqueWords.height() + ".");
        System.out.println("Unique Words in AVL Tree: " + avlOfUniqueWords.size + ".");
        System.out.println("Rotations Made: " + avlOfUniqueWords.rotations + ".");
    }


    /*public void addUniqueWordsToArrayList() {
        System.out.println("\nAdding Unique Words to Array List...");
        long startTime = System.currentTimeMillis();
        book.words.first();
        for (int i = 0; i < book.words.size(); i++){
            if (!alOfUniqueWords.contains(book.words.current())){
                alOfUniqueWords.insert(book.words.current(), alOfUniqueWords.size());
            }
            book.words.next();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println(alOfUniqueWords.size() + " Unique Words Found.");
        System.out.println(alOfUniqueWords.comparisons + " Comparisons Made.");
        System.out.println("Initiating Sorting...");
        startTime = System.currentTimeMillis();
        alOfUniqueWords.sort();
        stopTime = System.currentTimeMillis();
        System.out.println("Array List Sorted in " + ((double)(stopTime - startTime) / 1000) + " Seconds.");
    }

    public void addUniqueWordsToOrderedList(){
        System.out.println("\nAdding Unique Words to Ordered List...");
        long startTime = System.currentTimeMillis();
        book.words.first();
        for (int i = 0; i < book.words.size(); i++){
            if (!olOfUniqueWords.binarySearch(book.words.current())){
                olOfUniqueWords.add(book.words.current());
            }

            book.words.next();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Unique Words Added and Sorted into Ordered List in " + (double)(stopTime - startTime)/1000 + " Seconds.");
        System.out.println(olOfUniqueWords.size() + " Unique Words Found.");
        System.out.println(olOfUniqueWords.comparisons + " Comparisons Made.");
    }
    */

}
