Object.prototype.clone = clone;

function clone (deep) {
  var objectClone = new this.constructor();
  for (var property in this)
    if (!deep)
      objectClone[property] = this[property];
    else if (typeof this[property] == 'object')
      objectClone[property] = this[property].clone(deep);
    else
      objectClone[property] = this[property];
  return objectClone;
}

String.prototype.trim=trimFunc;

function trimFunc()
{
   return this.replace(/^\s*|\s*$/g,"");
}

String.prototype.equalsIgnoreCase=equalsIgnoreCaseFunc;

function equalsIgnoreCaseFunc(strTerm){
	var strToSearch = this.toLowerCase();
	strTerm = strTerm.toLowerCase();
	if(strToSearch==strTerm){
		return true;
	}else{
		return false;
	}
}

String.prototype.scan=scanFunc;

function scanFunc()
{	
	var paragraphs = this.split('\n');
	var textFreeOfParagraphs = '';
	for(i = 0; i < paragraphs.length; i++){
		if(paragraphs[i].trim() != ''){
			textFreeOfParagraphs += paragraphs[i].trim();
		}
	}
	
	return textFreeOfParagraphs.replace(/>|\'|\\\'|\\|&|\[|\]|{|}|\(|\)|#|</g," ");
}

String.prototype.scanURL=scanURLFunc;

function scanURLFunc()
{	
	var paragraphs = this.split('\n');
	var textFreeOfParagraphs = '';
	for(i = 0; i < paragraphs.length; i++){
		if(paragraphs[i].trim() != ''){
			textFreeOfParagraphs += paragraphs[i].trim();
		}
	}
	
	return textFreeOfParagraphs.replace(/>|\'|\\\'|\\|\[|{|#|</g,"");
}

function validaCampoData(campoId)
{
	var campo = $(campoId);
	var val = campo.value;
	if(val.trim() == ''){
		campo.style.backgroundColor = '#568ABB';
		campo.style.color = '#FFFFFF';
		return true;
	}
	var reTipo = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/\d{4}$/;
	if(!reTipo.test(val)){
		campo.style.backgroundColor = '#FFFF00';
		campo.style.color = '#000000';
		return false;
	}else{
		campo.style.backgroundColor = '#568ABB';
		campo.style.color = '#FFFFFF';
		return true;
	}
}

function validaCampoVazio(campoId)
{
	var campo = $(campoId);
	var val = campo.value;
	if(val.trim() == ''){
		campo.style.backgroundColor = '#FFFF00';
		campo.style.color = '#000000';
		return false;
	}else{
		campo.style.backgroundColor = '#568ABB';
		campo.style.color = '#FFFFFF';
		return true;
	}
}

function validaCampoEmail(campoId)
{
	var campo = $(campoId);
	var val = campo.value;
	var reTipo = /^[\w!#$%&'*+\/=?^`{|}~-]+(\.[\w!#$%&'*+\/=?^`{|}~-]+)*@(([\w-]+\.)+[A-Za-z]{2,6}|\[\d{1,3}(\.\d{1,3}){3}\])$/;
	if(!reTipo.test(val) && val.trim() != ''){
		campo.style.backgroundColor = '#FFFF00';
		campo.style.color = '#000000';
		return false;
	}else{
		campo.style.backgroundColor = '#568ABB';
		campo.style.color = '#FFFFFF';
		return true;
	}
}

function validaCampoNumeroInteiro(campoId)
{
	var campo = $(campoId);
	var val = campo.value;
	var reTipo = /^\d+$/;
	if(!reTipo.test(val)){
		campo.style.backgroundColor = '#FFFF00';
		campo.style.color = '#000000';
		return false;
	}else{
		campo.style.backgroundColor = '#568ABB';
		campo.style.color = '#FFFFFF';
		return true;
	}
}

function validaCampoNumeroInteiroOuVazio(campoId)
{
	var campo = $(campoId);
	var val = campo.value;
	var reTipo = /^\d+$/;
	if(val.trim() == ''){
		campo.style.backgroundColor = '#568ABB';
		campo.style.color = '#FFFFFF';
		return true;
	}
	if(!reTipo.test(val)){
		campo.style.backgroundColor = '#FFFF00';
		campo.style.color = '#000000';
		return false;
	}else{
		campo.style.backgroundColor = '#568ABB';
		campo.style.color = '#FFFFFF';
		return true;
	}
}

function validaSelectVazio(selectId){
	var mySelect = $(selectId);
	if(mySelect.options.length == 0){
		mySelect.style.backgroundColor = '#FFFF00';
		mySelect.style.color = '#000000';
		return false;
	}else{
		mySelect.style.backgroundColor = '#568ABB';
		mySelect.style.color = '#FFFFFF';
		return true;
	}
}

function validaSelectNaoSelecionado(selectId){
	var mySelect = $(selectId);
	var achou = false;
	for(i = 0; i < mySelect.options.length; i++){
		if(mySelect.options[i].selected){
			achou = true;
		}
	}
	if(!achou){
		mySelect.style.backgroundColor = '#FFFF00';
		mySelect.style.color = '#000000';
		return false;
	}else{
		mySelect.style.backgroundColor = '#568ABB';
		mySelect.style.color = '#FFFFFF';
		return true;
	}
}

function textCounter(fieldName, maxlimit) {
	var field = $(fieldName);
	if (field.value.length > maxlimit){
		field.value = field.value.substring(0, maxlimit);
	}
}