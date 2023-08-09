package xadrez;


import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {
	private Cor cor;
	private int contaMovimentos;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	public int getContaMovimentos() {
		return contaMovimentos;
	}

	public void aumentaContador() {
		contaMovimentos++;
	}

	public void decrementaContador() {
		contaMovimentos--;
	}

	protected boolean existePecaOponete(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

}
