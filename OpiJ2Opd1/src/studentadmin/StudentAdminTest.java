package studentadmin;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StudentAdminTest {
	private StudentenAdmin studAdmin = null;
	
	@Before
	public void setUp() {
		studAdmin = new StudentenAdmin();
	}
	// normale situatie is via GUI getest.

	@Test 
	// test op juiste foutmelding bij student maken met Cpp in plaats van opleiding
	public void testStudentMetCppExceptie() {
		try {
			studAdmin.maakStudent("erwin", "java");
		}
		catch (StudentAdminException e) {
			assertEquals("kies een bestaande opleiding", e.getMessage());
		}
	}
	
	@Test 
	// test op juiste foutmelding bij Scholer maken met Opleiding in plaats van CPP
	public void testScholerMetOpleidingExceptie() {
		try {
			studAdmin.maakScholer("erwin", "wiskunde");
		}
		catch (StudentAdminException e) {
			assertEquals("kies een bestaande CPP", e.getMessage());
		}
	}
	@Test 
	public void testScholerMetOpleidingExceptie2() {
		try {
			studAdmin.maakScholer("erwin", "  1");
		}
		catch (StudentAdminException e) {
			assertEquals("kies een bestaande CPP", e.getMessage());
		}
	}
	
}
