package itsar.mes.business;

public class StatusManager {

	public static int nextStatus(int actualStatus, int predStatus) {
		double rnd = Math.random() * 100;
		if (predStatus == 2) {
			if (rnd < 90) {
				return actualStatus;
			} else if (rnd < 99.9) {
				if (actualStatus == 2)
					return 2;
				else if (actualStatus == 9)
					return 0;
				else
					return (actualStatus + 1);
			} else {
				return 9;
			}
		} else {
			return actualStatus;
		}
	}

}
