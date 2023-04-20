package ui;

import entity.Emp;

public class Main {
	public static void main(String[] args) {
		Emp tempEmp = new Emp();
		new Feature_UI(tempEmp).setVisible(true);
	}
}
