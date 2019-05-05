import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class SchedulingAlgorithms {
	Vector<Integer> myvector = new Vector<Integer>();
	Vector<Integer> temp = new Vector<Integer>();
	Vector<Integer> temp2 = new Vector<Integer>();
	Vector<Integer> seqSCAN = new Vector<Integer>();
	Vector<Integer> seqCSCAN = new Vector<Integer>();
	Vector<Integer> seqCLOOK = new Vector<Integer>();
	int head, cylinders;

	public void fillvector() {
		Scanner ob = new Scanner(System.in);
		System.out.println("Enter the HEAD:");
		head = ob.nextInt();
		Scanner sc = new Scanner(System.in);
		int line = 0;
		System.out.println("Enter the QUEUE:");
		while (true) {
			line = sc.nextInt();
			if (line == -1) {
				break;
			}
			myvector.add(line);
			temp2.add(line);
		}
	}

	public void FCFS() {

		int sum = 0;
		for (int i = 0; i < myvector.size(); i++) {
			if (i == 0) {
				sum += Math.abs(head - myvector.get(i));
			} else {
				sum += Math.abs(myvector.get(i) - myvector.get(i - 1));
			}

		}
		System.out.println("FCFS Head Movement: " + sum);
	}

	public void FCFSprint() {
		System.out.println("Sequence FCFS:");
		for (int i = 0; i < myvector.size(); i++) {
			System.out.print(myvector.get(i) + " ");
		}
	}

	public void SSTF() {

		int sum = 0;

		int min, m = 0, index;
		temp.add(53);
		for (int j = 0; j < myvector.size(); j++) {
			min = 100000;
			index = -1;
			for (int i = 0; i < myvector.size(); i++) {
				if (!temp.contains(myvector.get(i))) {
					m = Math.abs(head - myvector.get(i));
					if (m < min) {
						min = m;
						index = i;

					}
				}

			}
			sum += Math.abs(myvector.get(index) - head);
			temp.add(myvector.get(index));
			head = myvector.get(index);
		}

		System.out.println("\nSSTF Head Movement: " + sum);
	}

	public void SSTFprint() {
		System.out.println("Sequence SSTF: ");
		for (int i = 0; i < temp.size(); i++) {
			System.out.print(temp.get(i) + " ");
		}
	}

	public void SCAN() {
		int sum = 0;
		temp2.add(0);
		temp2.add(head);
		Collections.sort(temp2);
		Vector<Integer> tempp = new Vector<Integer>();
		int indx = 0, next = 0;
		for (int i = 0; i < temp2.size(); i++) {
			if (temp2.get(i) == head) {
				indx = i;
				next = i + 1;
			}
		}

		// System.out.print("INDEX IS " + indx + next);
		sum += temp2.get(next);
		for (int i = 0; i < indx; i++) {
			// System.out.println(temp2.get(i)+" " +temp2.get(i+1));
			sum += Math.abs(temp2.get(i) - temp2.get(i + 1));
			tempp.add(temp2.get(i));
		}
		tempp.add(temp2.get(indx));

		for (int i = 0; i < tempp.size(); i++) {
			seqSCAN.add(tempp.get(tempp.size() - i - 1));
			// System.out.println("temp" + tempp.get(i));

		}
		for (int x = indx + 1; x < temp2.size() - 1; x++) {
			// System.out.println(temp2.get(x)+" " +temp2.get(x+1));
			sum += Math.abs(temp2.get(x) - temp2.get(x + 1));
			seqSCAN.add(temp2.get(x));
		}
		seqSCAN.add(temp2.get(temp2.size() - 1));
		System.out.println("SCAN Head Movement: " + sum);
	}

	public void printSCAN() {
		System.out.println("Sequence SCAN: ");
		for (int i = 0; i < seqSCAN.size(); i++) {
			System.out.print(seqSCAN.get(i) + " ");
		}

	}

	public void CSCAN() {
		int index = 0, sum = 0;
		Vector<Integer> tempp = new Vector<Integer>();
		Scanner ob = new Scanner(System.in);
		System.out.println("Enter the END of Cylinders:");
		cylinders = ob.nextInt();
		temp2.add(0);
		if (!temp2.contains(head)) {
			temp2.add(head);
		}

		temp2.add(cylinders);
		Collections.sort(temp2);
		for (int i = 0; i < temp2.size(); i++) {
			if (head == temp2.get(i)) {
				index = i;
			}
		}
		sum += cylinders;
		for (int i = index; i < temp2.size() - 1; i++) {
			System.out.println("COST "
					+ Math.abs(temp2.get(i) - temp2.get(i + 1)) + " " + "FROM "
					+ temp2.get(i) + " TO  " + temp2.get(i + 1));
			sum += Math.abs(temp2.get(i) - temp2.get(i + 1));
			seqCSCAN.add(temp2.get(i));
		}
		seqCSCAN.add(cylinders);
		System.out.println("COST FROM BEGIN TO END " + cylinders);
		for (int i = 0; i < index - 1; i++) {
			System.out.println("COST "
					+ Math.abs(temp2.get(i) - temp2.get(i + 1)) + " " + "FROM "
					+ temp2.get(i) + " TO  " + temp2.get(i + 1));
			sum += Math.abs(temp2.get(i) - temp2.get(i + 1));
			seqCSCAN.add(temp2.get(i));
		}
		seqCSCAN.add(temp2.get(index - 1));
		System.out.println("C-SCAN Head Movement: " + sum);
	}

	public void printCSCAN() {
		System.out.println("Sequence C-SCAN: ");
		for (int i = 0; i < seqCSCAN.size(); i++) {
			System.out.print(seqCSCAN.get(i) + " ");
		}

	}

	public void CLOOK() {
		int index = 0, sum = 0;
		Vector<Integer> tempp = new Vector<Integer>();
		Scanner ob = new Scanner(System.in);
		if (!temp2.contains(head)) {
			temp2.add(head);
		}
		Collections.sort(temp2);
		//cost of long line 
		sum += Math.abs(temp2.get(0) - temp2.get(temp2.size() - 1));
		for (int i = 0; i < temp2.size(); i++) {
			if (head == temp2.get(i)) {
				index = i;
			}
		}
		for (int i = index; i < temp2.size() - 1; i++) {
			System.out.println("COST "
					+ Math.abs(temp2.get(i) - temp2.get(i + 1)) + " " + "FROM "
					+ temp2.get(i) + " TO  " + temp2.get(i + 1));
			sum += Math.abs(temp2.get(i) - temp2.get(i + 1));
			seqCLOOK.add(temp2.get(i));
		}
		System.out.println("COST OF LAST TO BEGIN IS: " + Math.abs(temp2.get(0) - temp2.get(temp2.size() - 1)));
		seqCLOOK.add(temp2.get(temp2.size() - 1));
		for (int i = 0; i < index - 1; i++) {
			System.out.println("COST "
					+ Math.abs(temp2.get(i) - temp2.get(i + 1)) + " " + "FROM "
					+ temp2.get(i) + " TO  " + temp2.get(i + 1));
			sum += Math.abs(temp2.get(i) - temp2.get(i + 1));
			seqCLOOK.add(temp2.get(i));
		}
		seqCLOOK.add(temp2.get(index - 1));
		System.out.println("C-LOOK Head Movement: " + sum);

	}

	public void printCLOOK() {
		System.out.println("Sequence C-LOOK: ");
		for (int i = 0; i < seqCLOOK.size(); i++) {
			System.out.print(seqCLOOK.get(i) + " ");
		}

	}

	
	public void newlyoptimized(){
		temp2.add(0);
		Collections.sort(temp2);
		int sum=0;
		for (int i =1; i<temp2.size(); i++){
			sum+=Math.abs(temp2.get(i)-temp2.get(i-1));
		}
		System.out.println("NEWLY OPTIMIZED Head Movement: " + sum);	
		
	}
	public void newlypotimizedprint(){
		for (int i =0 ; i<temp2.size(); i++)
		{
			System.out.print(temp2.get(i)+" ");
			
		}	
		}

	public void clear() {
		myvector.clear();
		temp2.clear();
		temp.clear();
		seqSCAN.clear();
		seqCSCAN.clear();
		seqCLOOK.clear();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SchedulingAlgorithms ob = new SchedulingAlgorithms();
		int x;
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out
					.println("\n CHOOSE ALGORITHM\n 1-FCFS\n 2-SSTF\n 3-SCAN\n 4-CSCAN\n 5-CLOOK\n 6-NEWLY OPTIMIZED\n 7-EXIT\n");
			x = sc.nextInt();
			if (x == 1) {
				ob.fillvector();
				ob.FCFS();
				ob.FCFSprint();
				ob.clear();
			} else if (x == 2) {
				ob.fillvector();
				ob.SSTF();
				ob.SSTFprint();
				ob.clear();
			} else if (x == 3) {
				ob.fillvector();
				ob.SCAN();
				ob.printSCAN();
				ob.clear();
			} else if (x == 4) {
				ob.fillvector();
				ob.CSCAN();
				ob.printCSCAN();
				ob.clear();
			} else if (x == 5) {
				ob.fillvector();
				ob.CLOOK();
				ob.printCLOOK();
				ob.clear();
			} 
			else if (x==6){
				ob.fillvector();
				ob.newlyoptimized();
				ob.newlypotimizedprint();
				ob.clear();
			}else {
				break;
			}

		}
	}

}
