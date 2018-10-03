import java.util.ArrayList;
public class MinxTest {

	public static void main(String[] args) {
		Megaminx SOLVED_MINX = new Megaminx();
		Megaminx minx1 = new Megaminx();
		minx1.rotateFace(1, true);
		
		ArrayList<Megaminx> minxArr = new ArrayList<Megaminx>();
		boolean isCounterClockwise;
		for (int i = 0; i < 24; i++) {
			Megaminx temp = new Megaminx();
			temp.copyMinx(minx1);
			int tempi = i;
			isCounterClockwise = true;
			if (tempi > 11) {
				tempi = tempi % 12;
				isCounterClockwise = false;
			}
			temp.rotateFace(tempi, isCounterClockwise);
			minxArr.add(temp);	
		}
		for (Megaminx m : minxArr) {
			System.out.println(m.compareTo(SOLVED_MINX));
		}

	}


}
