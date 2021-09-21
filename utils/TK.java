package utils;

public class TK {
	private int id;
	private String token;
	private String lexema;
	private long linha;
	private long coluna;

	public TK(int id, String token, String lexema) {
		this.id = id;
		this.token = token;
		this.lexema = lexema;
	}
	public TK(String token, String lexema) {
		
		this.token = token;
		this.lexema = lexema;
	}

	public TK(int id, String token, String lexema, long linha, long coluna) {
		this.id = id;
		this.token = token;
		this.lexema = lexema;
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public String getLexema() {
		return lexema;
	}
	
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public long getLinha() {
		return linha;
	}

	public long getColuna() {
		return coluna;
	}

	public void setLinha(long linha) {
		this.linha = linha;
	}

	public void setColuna(long coluna) {
		this.coluna = coluna;
	}

	public TK() {
		super();
	}
}