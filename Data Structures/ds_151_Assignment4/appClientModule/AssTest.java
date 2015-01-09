/**
 * 
 * @author OR SHLOMO
 *
 */

public class AssTest {
	public static void main(String[] args)
	{
		DumpableNamedPoint Moshe = new DumpableNamedPoint(1,2, "Moshe");
		DumpableNamedPoint Avram = new DumpableNamedPoint(2,1, "Avram");
		DumpableNamedPoint Itzik = new DumpableNamedPoint(5,3,"Itzik");
		DumpableNamedPoint Abdallah = new DumpableNamedPoint(9,6, "Abdallah");
		DumpableNamedPoint Jak = new DumpableNamedPoint(4,4,"Jaklawak");
		
		AVL<DumpableNamedPoint> Xtree = new AVL<DumpableNamedPoint>();
		Xtree.insert(Moshe.getX(), Moshe);
		Xtree.insert(Avram.getX(), Avram);
		Xtree.insert(Itzik.getX(), Itzik);
		Xtree.insert(Abdallah.getX(), Abdallah);
		Xtree.insert(Jak.getX(), Jak);
		
		TreePrinter.printTree(Xtree,3);
		System.out.println("");
		
		AVL<DumpableNamedPoint> Ytree = new AVL<DumpableNamedPoint>();
		Ytree.insert(Moshe.getY(), Moshe);
		Ytree.insert(Avram.getY(), Avram);
		Ytree.insert(Itzik.getY(), Itzik);
		Ytree.insert(Abdallah.getY(), Abdallah);
		Ytree.insert(Jak.getY(), Jak);
		
		TreePrinter.printTree(Ytree,3);
		System.out.println("");
		
		LinkedList<DumpableNamedPoint> result = Xtree.Range(0, 10);
		Link<DumpableNamedPoint> tmp;
		tmp = result.getFirst();
		while (tmp != null) {
			System.out.println(tmp.getData());
			tmp = tmp.getNext();
		}
		System.out.println("");
		
		result = Ytree.Range(0, 10);
		tmp = result.getFirst();
		while (tmp != null) {
			System.out.println(tmp.getData());
			tmp = tmp.getNext();
		}
		System.out.println("");
		
		HashTable table = new HashTable(10);
		table.insert(Moshe);
		table.insert(Avram);
		table.insert(Itzik);
		table.insert(Abdallah);
		table.insert(Jak);
		System.out.println(table.search(1, 2).getData());
		System.out.println(table.search(2, 1).getData());
		System.out.println(table.search(5, 3).getData());
		System.out.println(table.search(9, 6).getData());
		System.out.println(table.search(4, 4).getData());
		System.out.println("");
		
		DumpableNamedPoint Hefer = new DumpableNamedPoint(140,48,"Hefer");
		DumpableNamedPoint Tali = new DumpableNamedPoint(246,107,"Tali");
		DumpableNamedPoint Amir = new DumpableNamedPoint(295,50,"Amir");
		DumpableNamedPoint Eyal = new DumpableNamedPoint(455,51,"Eyal");
		DumpableNamedPoint Lilach = new DumpableNamedPoint(546,98,"Lilach");
		DumpableNamedPoint Daniel = new DumpableNamedPoint(506,202,"Daniel");
		DumpableNamedPoint Yardena = new DumpableNamedPoint(252,172,"Yardena");
		
		AVL<DumpableNamedPoint> newX = new AVL<DumpableNamedPoint>();
		newX.insert(Hefer.getX(), Hefer);
		newX.insert(Tali.getX(), Tali);
		newX.insert(Amir.getX(), Amir);
		newX.insert(Eyal.getX(), Eyal);
		newX.insert(Lilach.getX(), Lilach);
		newX.insert(Daniel.getX(), Daniel);
		newX.insert(Yardena.getX(), Yardena);
		
		result = newX.Range(1, 5);
		tmp = result.getFirst();
		while (tmp != null) {
			System.out.println(tmp.getData());
			tmp = tmp.getNext();
		}
		System.out.println("");
		
		AVL<DumpableNamedPoint> newY = new AVL<DumpableNamedPoint>();
		newY.insert(Hefer.getY(), Hefer);
		newY.insert(Tali.getY(), Tali);
		newY.insert(Amir.getY(), Amir);
		newY.insert(Eyal.getY(), Eyal);
		newY.insert(Lilach.getY(), Lilach);
		newY.insert(Daniel.getY(), Daniel);
		newY.insert(Yardena.getY(), Yardena);
		
		result = newY.Range(0, 308);
		tmp = result.getFirst();
		while (tmp != null) {
			System.out.println(tmp.getData());
			tmp = tmp.getNext();
		}
		System.out.println("");
		
		System.out.println(newX.search(140).getData());
		System.out.println(newX.search(246).getData());
		System.out.println(newX.search(295).getData());
		System.out.println(newX.search(253) == null ? "" : newX.search(253).getData());
	}
	
}