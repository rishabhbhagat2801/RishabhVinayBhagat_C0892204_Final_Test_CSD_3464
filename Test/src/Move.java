import java.util.ArrayList;
import java.util.List;

// Name: RISHABH VINAY BHAGAT.
// ID: C0892204.
class Move {
	/* *************************************** */

	// class Item with itemName
	static class Item {
		protected String itemName;

		// Item class constructor
		public Item(String itemName) {
			this.itemName = itemName;
		}
		// get method for ItemName
		public String getItemName() {
			return itemName;
		}
	}
	// SingleObject class extending the Item class
	static class SingleObject extends Item {
		public SingleObject(String itemName) {
			super(itemName);
		}
	}
	// Box class extending Item class
	static class Box extends Item {
		private List<Item> items;
		// Box class constructor
		public Box(int capacity, int boxNumber) {
			super("Box " + boxNumber);
			this.items = new ArrayList<>(capacity);
		}
		// addItem method for adding item
		public void addItem(Item item) {
			items.add(item);
		}
		// getItems method to get the list of items
		public List<Item> getItems() {
			return items;
		}
	}
	// list for boxes
	private List<Box> boxes;
	// Move class contructor
	public Move() {
		this.boxes = new ArrayList<>();
	}
	// addBox method to add box
	public void addBox(Box box) {
		boxes.add(box);
	}
	//print method for printing the content of the boxes
	public void print() {
		System.out.println("The objects of my move are:");
		for (Box box : boxes) {
			printContents(box);
		}
	}
	// printContents Method for printing the content of the box
	private void printContents(Box box) {
		for (Item item : box.getItems()) {
			if (item instanceof SingleObject) {
				System.out.println(((SingleObject) item).getItemName());
			} else if (item instanceof Box) {
				printContents((Box) item);
			}
		}
	}
	// find method to find the number of the box with a itemName
	public int find(String itemName) {
		for (Box box : boxes) {
			if (containsItemInBox(box, itemName)) {
				return Integer.parseInt(box.getItemName().substring(4));
			}
		}
		return -1;
	}
	// containsItemInBox method to check if a item with itemName is inside box
	private boolean containsItemInBox(Box box, String itemName) {
		for (Item item : box.getItems()) {
			if (item instanceof SingleObject && ((SingleObject) item).getItemName().equals(itemName)) {
				return true;
			} else if (item instanceof Box) {
				if (containsItemInBox((Box) item, itemName)) {
					return true;
				}
			}
		}
		return false;
	}
	/* *************************************** */
 // main method
	public static void main(String[] args) {
		// We create a move that will hold 2 main boxes
		Move move = new Move();

		/*
		 * We create and then fill 3 boxes
		 * Arguments of the constructor of Box:
		 * argument 1: number of items (simple items/objects or box) that the box can hold
		 * argument 2: box number
		 */

		// box 1 contains scissors
		Box box1 = new Box(1, 1);
		box1.addItem(new SingleObject("scissors"));

		// box 2 contains one book
		Box box2 = new Box(1, 2);
		box2.addItem(new SingleObject("book"));

		// box 3 contains one compass
		// and one box containing one scarf
		Box box3 = new Box(2, 3);
		box3.addItem(new SingleObject("compass"));
		Box box4 = new Box(1, 4);
		box4.addItem(new SingleObject("scarf"));
		box3.addItem(box4);

		// We add the three boxes to the first box of move - see below
		Box box5 = new Box(3, 5);
		box5.addItem(box1);
		box5.addItem(box2);
		box5.addItem(box3);

		// We add one box containing 3 objects to move
		Box box6 = new Box(3, 6);
		box6.addItem(new SingleObject("pencils"));
		box6.addItem(new SingleObject("pens"));
		box6.addItem(new SingleObject("rubber"));

		// We add the two most external boxes to the move
		move.addBox(box5);
		move.addBox(box6);

		// We print all the contents of the move
		move.print();

		// We print the number of the outermost cardboard containing the item "scarf"
		System.out.println("The sarf is in the cardboard number " + move.find("scarf"));
	}
}