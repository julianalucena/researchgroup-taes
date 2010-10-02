package br.ufpe.cin.in980.bibtex;

import br.ufpe.cin.in980.membro.Membro;
import br.ufpe.cin.in980.membro.NaoMembro;
import br.ufpe.cin.in980.publicacao.ArtConferencia;
import br.ufpe.cin.in980.publicacao.ArtPeriodicoRevista;
import br.ufpe.cin.in980.publicacao.DissertacaoMestrado;
import br.ufpe.cin.in980.publicacao.Publicacao;
import br.ufpe.cin.in980.publicacao.TeseDoutorado;

public class GerarListaPublicacaoAux {

	public StringBuffer gerarPublicacaoBibTex(Publicacao publicacao, StringBuffer bib) {
		if (publicacao instanceof ArtConferencia) {
			ArtConferencia conferencia = (ArtConferencia) publicacao;
			bib.append("@Conference{");
			bib.append(getNomes(conferencia));
			bib.append(",\n");
			bib.append("author\t= \"");
			bib.append(getNomeAutores(conferencia));
			bib.append("\",\n");
			bib.append("title\t= \"");
			bib.append(conferencia.getTitulo());
			bib.append("\",\n");
			bib.append("booktitle\t= \"");
			bib.append("");
			bib.append("\",\n");
			bib.append("year\t= \"");
			bib.append(conferencia.getAno());
			bib.append("\",\n");
			bib.append("pages\t= \"");
			bib.append(conferencia.getPaginas());
			bib.append("\",\n");
			bib.append("month\t= \"");
			bib.append(conferencia.getMes());
			bib.append("\"\n}");
		} else if (publicacao instanceof ArtPeriodicoRevista) {
			ArtPeriodicoRevista periodicoRevista = (ArtPeriodicoRevista) publicacao;
			bib.append("@Article{");
			bib.append(getNomes(periodicoRevista));
			bib.append(",\n");
			bib.append("author\t= \"");
			bib.append(getNomeAutores(periodicoRevista));
			bib.append("\",\n");
			bib.append("title\t= \"");
			bib.append(periodicoRevista.getTitulo());
			bib.append("\",\n");
			bib.append("journal\t= \"");
			bib.append(periodicoRevista.getJournal());
			bib.append("\",\n");
			bib.append("year\t= \"");
			bib.append(periodicoRevista.getAno());
			bib.append("\",\n");
			bib.append("volume\t= \"");
			bib.append(periodicoRevista.getVolume());
			bib.append("\",\n");
			bib.append("number\t= \"");
			bib.append(periodicoRevista.getNumero());
			bib.append("\"\n}");
			bib.append("pages\t= \"");
			bib.append(periodicoRevista.getPaginas());
			bib.append("\"\n}");
		} else if (publicacao instanceof DissertacaoMestrado) {
			DissertacaoMestrado dissertacaoMestrado = (DissertacaoMestrado) publicacao;
			bib.append("@Mastersthesis{");
			bib.append(getNomes(dissertacaoMestrado));
			bib.append(",\n");
			bib.append("author\t= \"");
			bib.append(getNomeAutores(dissertacaoMestrado));
			bib.append("\",\n");
			bib.append("title\t= \"");
			bib.append(dissertacaoMestrado.getTitulo());
			bib.append("\",\n");
			bib.append("school\t= \"");
			bib.append(dissertacaoMestrado.getEscola());
			bib.append("\",\n");
			bib.append("year\t= \"");
			bib.append(dissertacaoMestrado.getAno());
			bib.append("\",\n");
			bib.append("month\t= \"");
			bib.append(dissertacaoMestrado.getMes());
			bib.append("\",\n}");
		} else if (publicacao instanceof TeseDoutorado) {
			TeseDoutorado teseDoutorado = (TeseDoutorado) publicacao;
			bib.append("@Phdthesis{");
			bib.append(getNomes(teseDoutorado));
			bib.append(",\n");
			bib.append("author\t= \"");
			bib.append(getNomeAutores(teseDoutorado));
			bib.append("\",\n");
			bib.append("title\t= \"");
			bib.append(teseDoutorado.getTitulo());
			bib.append("\",\n");
			bib.append("school\t= \"");
			bib.append(teseDoutorado.getEscola());
			bib.append("\",\n");
			bib.append("year\t= \"");
			bib.append(teseDoutorado.getAno());
			bib.append("\",\n");
			bib.append("month\t= \"");
			bib.append(teseDoutorado.getMes());
			bib.append("\",\n}");
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
