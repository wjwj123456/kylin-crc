package bl.command;

import java.util.ArrayList;
import java.util.List;

import blservice.Command;

/**
 * manager of commands, handle execute, redo and undo command
 * 
 * @author song
 */
public class CommandManager {
	private static List<Command> undoList = new ArrayList<>();
	private static List<Command> redoList = new ArrayList<>();

	// steps of undo command
	private static final int undoCount = 10;

	private CommandManager() {
	}

	/**
	 * execute command
	 */
	public static int executeCommand(Command cmd) {
		int result = cmd.execute();

		undoList.add(cmd);

		// 保留最近undoCount次操作，删除最早操作
		if (undoList.size() > undoCount) {
			undoList.remove(0);
		}

		// 执行新操作后清空redoList
		redoList.clear();

		return result;
	}

	/**
	 * undo command
	 */
	public static void undo() {
		if (undoList.size() <= 0) {
			return;
		}

		Command cmd = ((Command) (undoList.get(undoList.size() - 1)));
		cmd.undo();

		undoList.remove(cmd);
		redoList.add(cmd);
	}

	/**
	 * redo command
	 */
	public static void redo() {
		if (redoList.size() <= 0) {
			return;
		}

		Command cmd = ((Command) (redoList.get(redoList.size() - 1)));
		cmd.execute();

		redoList.remove(cmd);
		undoList.add(cmd);
	}
}
