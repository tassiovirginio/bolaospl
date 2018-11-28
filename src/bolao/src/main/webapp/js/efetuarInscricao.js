$().ready(function(){
	
	$('textarea[name="desateesp_can"]').keypress(function(event){
		key = event.keyCode;
		if($(this).val().length==150 && (key != 8)){
			event.preventDefault();
		}
	});
	
    $('input[type="submit"]').click(function(event){
		if (!document.efetuarInscricao.aceitar_condicoes.checked)
    	{
    		alert("Para efetuar a inscri��o, voc� deve marcar a op��o que declara estar de acordo com as normas e prazos presentes no edital 028/2013.");
    		document.efetuarInscricao.aceitar_condicoes.focus();
    		return false;
    	}
    	if (document.efetuarInscricao.nome_can.value == "")
    	{
    		alert("Por favor, informe seu nome completo!");
    		document.efetuarInscricao.nome_can.focus();
    		scroll(0,0);
    		return false;
    	}  	
    	if (document.efetuarInscricao.nasc_can.value == "")
    	{
    		alert("Por favor, infome sua data de nascimento!");
    		document.efetuarInscricao.nasc_can.focus();
    		scroll(0,0);
    		return false;
    	}
    	if (document.efetuarInscricao.sexo_can.value == "")
    	{
    		alert("Por favor, informe o seu sexo!");
    		document.efetuarInscricao.sexo_can.focus();
    		scroll(0,0);
    		return false;
    	}
     });   
});