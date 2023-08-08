package xadrez.pecas;

import tabuleiro.Posicao;
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

		Posicao p = new Posicao(0, 0);

		//Acima
		if (getCor() == Cor.WHITE) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().eUmaPeca(p2)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		} else {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		return mat;
	}
}

		//if (p_ini.getLinha() == p.getLinha()) {
		//	for (int i = 1; i < 3; i++) {
		//		p.setValores(posicao.getLinha() - i, posicao.getColuna());
		//		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
		//			mat[p.getLinha()][p.getColuna()] = true;
		//		}
		//	}
		//	p.setValores(0, 0);
		//}
