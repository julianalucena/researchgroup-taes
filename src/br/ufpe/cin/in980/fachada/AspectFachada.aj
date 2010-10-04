package br.ufpe.cin.in980.fachada;

public privileged aspect AspectFachada {

	pointcut acessoAoBanco() : execution(* Fachada.buscar*(..))
								|| execution(* Fachada.listar*(..));
	pointcut acessoAoBancoComCommit() : execution(* Fachada.cadastrar*(..)) 
										|| execution(* Fachada.editar*(..))
										|| execution(* Fachada.deletar*(..));

	before() : acessoAoBanco() || acessoAoBancoComCommit() {
		Fachada.obterInstancia().getConexao().createConnection();
	}
	
	after() : acessoAoBanco() {
		Fachada.obterInstancia().getConexao().closeConnection();
	}
	
	after() : acessoAoBancoComCommit() {
		Fachada instancia = Fachada.obterInstancia();
		instancia.getConexao().commitTransaction();
		instancia.getConexao().closeConnection();
	}

}
