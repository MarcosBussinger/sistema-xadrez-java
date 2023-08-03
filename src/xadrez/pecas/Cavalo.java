package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao p = new Posicao(0, 0);
		
		//Cima direita
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Direita cima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Direita baixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Baixo direita
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Baixo esquerda
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Esquerda baixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Esquerda baixo
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		//Cima esquerda
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(p) && !getTabuleiro().eUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		if (getTabuleiro().existePosicao(p) && existePecaOponete(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}

