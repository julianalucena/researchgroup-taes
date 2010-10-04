package br.ufpe.cin.in980.bibtex;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;
import br.ufpe.cin.in980.publicacao.ArtConferencia;
import br.ufpe.cin.in980.publicacao.ArtPeriodicoRevista;
import br.ufpe.cin.in980.publicacao.Monografia;
import br.ufpe.cin.in980.publicacao.Monografia.TipoMonografia;
import br.ufpe.cin.in980.publicacao.Publicacao;

public class GerarListaPublicacaoAux {

	public StringBuffer gerarPublicacaoBibTex(Publicacao publicacao, StringBuffer bib) {
		
		// Comum a todas as publicacoes
		String nomesAutorTitulo = getNomes(publicacao) + ",\n author\t= \"" + getNomeAutores(publicacao) 
									+ "\",\n title\t= \"" + publicacao.getTitulo() + "\",\n";
		
		if (publicacao instanceof ArtConferencia) {
			
			ArtConferencia conferencia = (ArtConferencia) publicacao;
			bib.append("@Conference{" + nomesAutorTitulo + "booktitle\t= \"" + "\",\n year\t= \"" + conferencia.getAno() 
					+ "\",\n pages\t= \"" + conferencia.getPaginas() 
					+ "\",\n month\t= \"" + conferencia.getMes() + "\"\n}");

		} else if (publicacao instanceof ArtPeriodicoRevista) {
			
			ArtPeriodicoRevista periodicoRevista = (ArtPeriodicoRevista) publicacao;
			bib.append("@Article{" + nomesAutorTitulo + "journal\t= \"" + periodicoRevista.getJournal() 
					+ "\",\n year\t= \"" + periodicoRevista.getAno() + "\",\n volume\t= \"" + periodicoRevista.getVolume() 
					+ "\",\n number\t= \"" + periodicoRevista.getNumero() + "\"\n} pages\t= \"" + periodicoRevista.getPaginas() + "\"\n}");
			
		} else if (publicacao instanceof Monografia) {
			
			TipoMonografia tipoMonografia = ((Monografia) publicacao).getTipoMonografia();
			
			if (tipoMonografia.equals(TipoMonografia.DISSERTACAO_MESTRADO)) {
				bib.append("@Mastersthesis{");
			} else if (tipoMonografia.equals(TipoMonografia.TESE_DOUTORADO)) {
				bib.append("@Phdthesis{");
			}
			
			bib.append(nomesAutorTitulo + "school\t= \"" + ((Monografia) publicacao).getEscola() + "\",\n year\t= \"" + publicacao.getAno() 
					+ "\",\n month\t= \"" + ((Monografia) publicacao).getMes() + "\"\n}");
		}
		
		return bib;
	}

	private String getNomeAutores(Publicacao publicacao) {
		String nomes = "";
		for (Membro membro : publicacao.getAutoresMembros()) {
			if (!nomes.isEmpty()) {
				nomes += " and ";
			}
			String[] nome = membro.getNomeMembro().split(" ");
			for (int i = 0; i < nome.length; i++) {
				if (i == nome.length - 1) {
					nomes += "{" + nome[i] + "}";
				} else {
					nomes += nome[i];
				}
			}
		}
		return nomes;
	}

	private String getNomes(Publicacao publicacao) {
		String nomes = "";
		for (Membro membro : publicacao.getAutoresMembros()) {
			String[] nome = membro.getNomeMembro().split(" ");
			if (!nomes.isEmpty()) {
				nomes += "+" + nome[nome.length - 1];
			} else {
				nomes += nome[nome.length - 1];
			}
		}

		for (NaoMembro naoMembro : publicacao.getAutoresNaoMembros()) {
			String[] nome = naoMembro.getNome().split(" ");
			if (!nomes.isEmpty()) {
				nomes += "+" + nome[nome.length - 1];
			} else {
				nomes += nome[nome.length - 1];
			}
		}
		return nomes;
	}
}
