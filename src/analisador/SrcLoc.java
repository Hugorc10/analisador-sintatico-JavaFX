package analisador;

public class SrcLoc {
	private int source_line;
	private int source_col;

	private String source_file;

	public SrcLoc(int lineNumber, int columnNumber, String fileName) {
		source_line = lineNumber;
		source_col = columnNumber;
		source_file = fileName;
	}

	@Override
	public String toString() {
		return source_file + ":" + source_line + ":" + source_col;
	}
}
