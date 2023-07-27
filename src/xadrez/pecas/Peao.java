package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
