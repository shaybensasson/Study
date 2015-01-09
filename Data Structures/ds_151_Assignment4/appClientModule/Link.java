/**
 * An infrastructure LinkedList Link implementation
 * 
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 * @param <T>
 */
public class Link<T> {
	private T data;
	private Link<T> next;


	public Link(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public Link<T> getNext() {
		return next;
	}

	public void setNext(Link<T> next) {
		this.next = next;
	}

	public String toString() {
		return data.toString();
	}
}