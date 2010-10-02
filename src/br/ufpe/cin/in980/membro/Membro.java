package br.ufpe.cin.in980.membro;

import java.io.Serializable;
import java.util.List;

import br.ufpe.cin.in980.publicacao.Publicacao;

public class Membro implements Serializable {

	private static final long serialVersionUID = -5185438972310192057L;

	private Long idMembro;
	private String nomeMembro;
	private String departamento;
	private String universidade;
	private String email;
	private String telefone;
	private String website;
	private String cidade;
	private String pais;
	private byte[] foto;
	private String status;
	private List<Publicacao> publicacoes;

	public Membro(Long idMembro, String nomeMembro, String departamento,
			String universidade, String email, String telefone, String website,
			String cidade, String pais, byte[] foto, String status,
			List<Publicacao> publicacoes) {
		super();
		this.idMembro = idMembro;
		this.nomeMembro = nomeMembro;
		this.departamento = departamento;
		this.universidade = universidade;
		this.email = email;
		this.telefone = telefone;
		this.website = website;
		this.cidade = cidade;
		this.pais = pais;
		this.foto = foto;
		this.status = status;
		this.publicacoes = publicacoes;
	}

	public Membro() {
	}

	public Long getIdMembro() {
		return idMembro;
	}

	public void setIdMembro(Long idMembro) {
		this.idMembro = idMembro;
	}

	public String getNomeMembro() {
		return nomeMembro;
	}

	public void setNomeMembro(String nomeMember) {
		this.nomeMembro = nomeMember;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getUniversidade() {
		return universidade;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
}
