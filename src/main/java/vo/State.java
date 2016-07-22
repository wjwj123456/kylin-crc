package vo;

import java.io.Serializable;

public enum State implements Serializable {
	agree, refuse, commit, merged, work, timefinish, ownerfinish;

	public static int getTaskState(State state) {
		if (state.equals(State.work)) {
			return 0;
		} else if (state.equals(State.timefinish)) {
			return 1;
		} else {
			return 2;
		}
	}

}
