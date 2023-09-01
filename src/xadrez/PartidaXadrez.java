package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.Position;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.WHITE;
		setupInicial();
	}

	public int getTurno(){
		return turno;
	}

	public Cor getJogadorAtual(){
		return jogadorAtual;
	}
	
	public boolean getCheck(){
		return check;
	}

	public boolean getCheckMate(){
		return checkMate;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoInicial) {
		Posicao posicao = posicaoInicial.paraPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public PecaXadrez executarJogadaXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicao();
		Posicao destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = fazerMovimento(origem, destino);

		if(testeCheck(jogadorAtual)){
			desfazerJogada(origem, destino, pecaCapturada);
			throw new XadrezException("Voce nao pode se colcoar em cheque!");
		}

		check = (testeCheck(oponente(jogadorAtual))) ? true : false;
		proximaTurno();
		return (PecaXadrez)pecaCapturada;
	}
	
	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removePeca(origem);
		((PecaXadrez) p).aumentaContador();
		Peca pecaCapturada = tabuleiro.removePeca(destino);
		tabuleiro.porPeca(p, destino);

		if(pecaCapturada != null){
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}

	private void desfazerJogada(Posicao origem, Posicao destino, Peca pecaCapturada){
		Peca p = tabuleiro.removePeca(destino);
		((PecaXadrez)p).decrementaContador();
		tabuleiro.porPeca(p, origem);

		if(pecaCapturada != null){
			tabuleiro.porPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.eUmaPeca(posicao)) {
			throw new XadrezException("Não há peça na posição");
		}
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){
			throw new XadrezException("A peça escolhida nao é sua!");
		}
		if (!tabuleiro.peca(posicao).isThereAnyPossibleMove()){
			throw new XadrezException("Não há movimentos possíveis para a peça selecionada");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).possiveisMovimentos(destino)) {
			throw new XadrezException("A peça não pode ser movida para o destino escolhido.");
		}
	}

	private void proximaTurno(){
		turno++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private Cor oponente ( Cor cor){
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private PecaXadrez rei (Cor cor){
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list){
			if(p instanceof Rei){
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Nao Foi encontrado Rei dessa cor");
	}	

	private boolean testeCheck (Cor cor){
		Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peca> pecasOpenete = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasOpenete){
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]){
				return true;
			}
		}
		return false;
	}

	private void novaPosicao(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.porPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
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
