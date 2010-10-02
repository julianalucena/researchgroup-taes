<script src="js/prototype.js" type="text/javascript"></script>
<script src="js/scriptaculous/scriptaculous.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">
	function mostrarAdicionais(){
		var myAjax = new Ajax.Request(
			'add_adicional.do', 
			{
				method: 'post',
				onComplete: inserirAdicionais
			}
		);
	}

	function inserirAdicionais(req){
		var adicional = $('adicional');
		adicional.innerHTML = '<tr> <td>Nome do orientador: </td> <td> <input id="nomeorientador" name="nomeorientador" type="text"> </td> </tr> <tr> <td>Nome do coorientador: </td> <td> <input id="nomecoorientador" name="nomecoorientador" type="text"> </td> </tr> <tr> <td>Status: </td> <td> <select id="tipoEstudante" name="tipoEstudante"> <option value="DOUTORADO">Doutorado</option> <option value="MESTRADO">Mestrado</option> <option value="IC">IC</option> </select>  </td> </tr>';
	}
</script>