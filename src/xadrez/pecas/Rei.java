package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);

		//Acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Acima-esquerda
		p.setValores(posicao.getLinha() - 1, posicao.getColuna()- 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Acima-direita
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Baixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Baixo-esquerda
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Baixo-direita
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
}
