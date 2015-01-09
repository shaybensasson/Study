/**
 * An infrastructure LinkedList implementation
 * NOTE: all methods (except toString()) run in O(1) time
 * 
 * NOTE: we implemented this custom linked list because its easy 
 * 		and it support 'Count' and we can ensure that all time complexities are known
 * 
 * @author Shay Ben-Sasson (061203410) and Itav Eliyahu (042828855)
 *
 * @param <T>
 */
public class LinkedList<T> {
	private Link<T> first;
	private Link<T> last;
	
	private int count;

	public LinkedList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}
	
	public Link<T> getFirst() {
		return first;
	}
	
	public Link<T> getLast() {
		return last;
	}
	
	public void addFirst(T data) {
		Link<T> newLink = new Link<T>(data);
		
		if (first == null) {
			first = newLink;
			last = first;
		}
		else {
			
			newLink.setNext(first);
			first = newLink;
		}
		
		count++;		
	}
	
	public void addLast(T data) {
		Link<T> newLink = new Link<T>(data);
		
		if (last == null) {
			last = newLink;
			first = last;
		}
		else {
			last.setNext(newLink);
			last = newLink;
		}
		
		count++;
	}
	
	public int getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (first != null) {
			Link<T> tmp = first;
			while (tmp != null) {
				if (tmp != first)
					sb.append("->");
				sb.append(tmp.toString());
				tmp = tmp.getNext();
			}
		}
		return sb.toString();
	}
}