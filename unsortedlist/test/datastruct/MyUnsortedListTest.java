package datastruct;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyUnsortedListTest {
	MyUnsortedList<Integer> liste;
	MyUnsortedList<Integer> unchangedlist;
	MyUnsortedList<Integer> listevide;
	int taille, debut, fin, milieu;
	/**
	 * test1 : liste vide et liste (0, 1, 2, 3, 4)
	 * 		- 1 changement pour popLast qui ne levait pas la bonne exception
	 * test2 : listevide et (0, 1, 2, 3, 4, 5, 6, 7, 8, 9) 13/13
	 * test3 : listevide et (0)
	 * test4 : listevide et (0,1)
	 * test5 : listevide et (0,1,2)
	 * 		- 1 changement : ajout size-- a la methode remove
	 * */

	@BeforeEach
	void setUp() throws Exception {
		liste = MyUnsortedList.of(0, 1, 2, 3, 4);
		unchangedlist = MyUnsortedList.of(0, 1, 2, 3, 4);
		listevide = MyUnsortedList.of();
		taille = 5;
		debut = 0;
		fin = 4;
		milieu = 2;
	}


	@Test
	void testEmpty() {
		assertFalse(liste.isEmpty());
		assertTrue(listevide.isEmpty());
	}
	
	@Test
	void testSize() {
		assertEquals(liste.size(),taille);
		assertEquals(listevide.size(),0);		
	}
	
	@Test
	void testPop() {
		assertEquals((int)liste.pop(),debut);
		assertThrows(EmptyListException.class, ()->{listevide.pop();});
	}
	
	@Test
	void testPopLast() {
		assertEquals((int)liste.popLast(),fin);
		assertThrows(EmptyListException.class, ()->{listevide.popLast();});
	}
	
	@Test
	void testRemove() {
		assertEquals((int)liste.remove(milieu),milieu);
		assertThrows(IndexOutOfBoundsException.class, ()->{liste.remove(taille+5);});
		assertThrows(IndexOutOfBoundsException.class, ()->{liste.remove(-5);});
		
		assertThrows(IndexOutOfBoundsException.class, ()->{listevide.remove(0);});
		assertThrows(IndexOutOfBoundsException.class, ()->{listevide.remove(1);});
	}
	
	@Test
	void testPrepend() {
		liste.prepend(8);
		assertEquals((int)liste.pop(),(int)8);
		listevide.prepend(8);
		assertEquals((int)listevide.pop(),(int)8);
	}
	
	@Test
	void testAppend() {
		int a = 8;
		liste.append(a);
		assertEquals((int)liste.popLast(),a);
		listevide.append(a);
		assertEquals((int)listevide.popLast(),a);
	}
	
	@Test
	void testInsertFirst() {
		int a = 4;
		liste.insert(a, 0);
		listevide.insert(a, 0);
		assertEquals(a,(int)liste.pop());
		assertEquals(a,(int)listevide.pop());
	}
	
	@Test
	void testInsertLast() {
		int a = 4;
		liste.insert(a, taille);
		listevide.insert(a, 0);
		assertEquals(a,(int)liste.popLast());
		assertEquals(a,(int)listevide.popLast());
	}
	
	@Test
	void testInsert() {
		int a = 4;
		liste.insert(a, 2);
		assertEquals(a,(int)liste.remove(2));
		assertThrows(IndexOutOfBoundsException.class, ()->{listevide.insert(a, 1);});
		
	}
	
	@Test
	void testEqualsFirst() {
		int a = liste.pop();
		liste.prepend(a);
		assertEquals(liste,unchangedlist);
	}
	
	@Test
	void testEqualsLast() {
		int a = liste.popLast();
		liste.append(a);
		assertEquals(liste,unchangedlist);
	}
	
	@Test
	void testEquals() {
		int a = liste.remove(milieu);
		liste.insert(a, milieu);
		assertEquals(liste,unchangedlist);
	}

}
