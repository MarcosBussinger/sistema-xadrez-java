package tabuleiro;

public abstract class Peca {
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public abstract boolean[][] possiveisMovimentos();
	
	public boolean possiveisMovimentos(Posicao posicao) {
		return possiveisMovimentos()[posicao.getLinha()][posicao.getColuna()];
	}
}
