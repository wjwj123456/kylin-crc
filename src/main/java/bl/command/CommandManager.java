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
	private List<Command> undoList = new ArrayList<>();
	private List<Command> redoList = new ArrayList<>();

	// steps of undo command
	private int undoCount;

	public CommandManager() {
		undoCount = 20;
	}

	/**
	 * execute command
	 */
	public void executeCommand(Command cmd) {
		cmd.execute();

		undoList.add(cmd);

		// 保留最近undoCount次操作，删除最早操作
		if (undoCount != -1 && undoList.size() > undoCount) {
			undoList.remove(0);
		}

		// 执行新操作后清空redoList
		redoList.clear();
	}

	/**
	 * undo command
	 */
	public void undo() {
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
	public void redo() {
		if (redoList.size() <= 0) {
			return;
		}

		Command cmd = ((Command) (redoList.get(redoList.size() - 1)));
		cmd.execute();

		redoList.remove(cmd);
		undoList.add(cmd);
	}
}
