package tabuleiro;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas<1 || colunas <1) {
			throw new TabuleiroException("Erro ao criar o tabuleiro! E necessario que haja pelo menos 1 linha e uma coluna!");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		if(!existePosicao(linha, coluna)) {
			throw new TabuleiroException("Posicao fora do Tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new TabuleiroException("Posicao fora do Tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void porPeca(Peca peca, Posicao posicao) {
		if(existePosicao(posicao)) {
			throw new TabuleiroException("Ja existe uma peca na posicao " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()]= peca;
		peca.posicao = posicao;
	}
	
	private boolean existePosicao(int linha, int coluna) {
		return linha >=0 && linha < linhas && coluna >=0 && coluna < colunas;
	}
	
	public boolean existePosicao(Posicao posicao) {
		return existePosicao(posicao.getLinha(),posicao.getColuna());
	}
	
	public boolean eUmaPeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new TabuleiroException("Posicao fora do Tabuleiro");
		}
		return peca(posicao)!= null;
	}
}

