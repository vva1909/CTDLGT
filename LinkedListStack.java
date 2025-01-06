
package w4;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<E> implements StackInterface<E> {
    // Lớp Node lưu trữ các phần tử của stack
    class Node {
        E element;
        Node next;
        
        // Constructor cho Node
        Node(E element) {
            this.element = element;
            this.next = null;
        }
    }
    
    // Con trỏ đến đỉnh ngăn xếp
    private Node stack = null;
    
    // Số lượng phần tử trong stack
    private int size = 0;

    @Override
    public void push(E element) {
        // Tạo một node mới với phần tử được thêm
        Node newNode = new Node(element);
        
        // Liên kết node mới với node đỉnh hiện tại
        newNode.next = stack;
        
        // Cập nhật node đỉnh là node mới
        stack = newNode;
        
        // Tăng kích thước stack
        size++;
    }

    @Override
    public E pop() {
        // Kiểm tra nếu stack rỗng
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        
        // Lưu lại giá trị của node đỉnh
        E topElement = stack.element;
        
        // Chuyển node đỉnh sang node tiếp theo
        stack = stack.next;
        
        // Giảm kích thước stack
        size--;
        
        // Trả về phần tử đã bị loại
        return topElement;
    }

    @Override
    public boolean isEmpty() {
        // Kiểm tra nếu stack rỗng (không có node nào)
        return stack == null;
    }

    @Override
    public E top() {
        // Kiểm tra nếu stack rỗng
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        
        // Trả về phần tử của node đỉnh mà không loại bỏ nó
        return stack.element;
    }

    @Override
    public Iterator<E> iterator() {
        // Trả về iterator để duyệt qua các phần tử của stack
        return new StackIterator();
    }

    // Lớp Iterator để duyệt qua các phần tử của stack
    class StackIterator implements Iterator<E> {
        // Con trỏ đến node hiện tại
        private Node currentNode = stack;

        @Override
        public boolean hasNext() {
            // Kiểm tra xem còn node tiếp theo không
            return currentNode != null;
        }

        @Override
        public E next() {
            // Kiểm tra nếu không còn node nào
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            // Lấy phần tử của node hiện tại
            E data = currentNode.element;
            
            // Chuyển con trỏ sang node tiếp theo
            currentNode = currentNode.next;
            
            // Trả về phần tử
            return data;
        }
    }
}

