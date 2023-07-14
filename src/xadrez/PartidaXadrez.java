package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0; i<tabuleiro.getLinhas(); i++) {
			for(int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i,j);
			}
		}
		return mat;
	}
	private void novaPosicao(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.porPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
	}
	
	private void setupInicial() {
		novaPosicao('a', 1, new Torre(tabuleiro, Cor.WHITE));
		novaPosicao('b', 1, new Cavalo(tabuleiro, Cor.WHITE));
		novaPosicao('c', 1, new Bispo(tabuleiro, Cor.WHITE));
        novaPosicao('d', 1, new Rainha(tabuleiro, Cor.WHITE));
        novaPosicao('e', 1, new Rei(tabuleiro, Cor.WHITE));
        novaPosicao('f', 1, new Bispo(tabuleiro, Cor.WHITE));
        novaPosicao('g', 1, new Cavalo(tabuleiro, Cor.WHITE));
        novaPosicao('h', 1, new Torre(tabuleiro, Cor.WHITE));
        novaPosicao('a', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPosicao('b', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPosicao('c', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPosicao('d', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPosicao('e', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPosicao('f', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPosicao('g', 2, new Peao(tabuleiro, Cor.WHITE));
        novaPosicao('h', 2, new Peao(tabuleiro, Cor.WHITE));

        novaPosicao('a', 8, new Torre(tabuleiro, Cor.BLACK));
        novaPosicao('b', 8, new Cavalo(tabuleiro, Cor.BLACK));
        novaPosicao('c', 8, new Bispo(tabuleiro, Cor.BLACK));
        novaPosicao('d', 8, new Rainha(tabuleiro, Cor.BLACK));
        novaPosicao('e', 8, new Rei(tabuleiro, Cor.BLACK));
        novaPosicao('f', 8, new Bispo(tabuleiro, Cor.BLACK));
        novaPosicao('g', 8, new Cavalo(tabuleiro, Cor.BLACK));
        novaPosicao('h', 8, new Torre(tabuleiro, Cor.BLACK));
        novaPosicao('a', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPosicao('b', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPosicao('c', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPosicao('d', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPosicao('e', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPosicao('f', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPosicao('g', 7, new Peao(tabuleiro, Cor.BLACK));
        novaPosicao('h', 7, new Peao(tabuleiro, Cor.BLACK));
	}
}