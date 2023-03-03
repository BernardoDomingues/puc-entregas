package model;

public class X {
	private int codigo;
	private String Y;
	
	public X() {
		this.codigo = -1;
		this.Y = "";
	}
	
	public X(int codigo, String Y) {
		this.codigo = codigo;
		this.Y = Y;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getY() {
		return Y;
	}

	public void setY(String Y) {
		this.Y = Y;
	}

	@Override
	public String toString() {
		return "X [codigo=" + codigo + ", Y=" + Y + "]";
	}	
}
