package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
 
public class UI {
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
		
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Limpar console em outros sistemas operacionais
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		try {
			String s =sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		}catch(RuntimeException e){
			throw new InputMismatchException("Erro lendo a posicao. Os valores validos sao entre a1 e h8.");
		}
	}

	public static void printPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturada){
		printTabuleiro(partidaXadrez.getPecas());
		System.out.println();
		mostrarPecaCapturada(capturada);
		System.out.println();
		System.out.println("Turno : " + partidaXadrez.getTurno());
		if(!partidaXadrez.getCheckMate()){
			System.out.println("Esperando jogador: " + partidaXadrez.getJogadorAtual());
			if(partidaXadrez.getCheck()){
				System.out.println("CHECK!");
			}
		}else{
			System.out.println("CHECKMATE!");
			System.out.println("vencedor: "+ partidaXadrez.getJogadorAtual());
		}
		
	}
	
	public static void printTabuleiro(PecaXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {//usando length tbm pra j pois a matriz vai ser quadrada
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] possiveisMovimentos) {
		for(int i=0; i<pecas.length; i++) {
			System.out.print((8-i)+" ");
			for(int j = 0; j<pecas.length;j++) {//usando length tbm pra j pois a matriz vai ser quadrada
				printPeca(pecas[i][j], possiveisMovimentos[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPeca(PecaXadrez peca, boolean planoFundo) {
		if (planoFundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if(peca == null) {
			System.out.print("-"+ ANSI_RESET);
		}else {
			if (peca.getCor() == Cor.WHITE) {
            System.out.print(ANSI_WHITE + peca + ANSI_RESET);
        	}
        	else {
            System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
        	}
		}
		System.out.print(" ");
	}

	private static void mostrarPecaCapturada(List<PecaXadrez> capturada ){
		List<PecaXadrez> branca = capturada.stream().filter(x -> x.getCor() == Cor.WHITE).collect(Collectors.toList());
		List<PecaXadrez> preta = capturada.stream().filter(x -> x.getCor() == Cor.BLACK).collect(Collectors.toList());
		System.out.println("Peças Capturadas:");
		System.out.print("Branca: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Preta: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.print(ANSI_RESET);
	}
}
