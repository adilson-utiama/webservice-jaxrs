<h3>Envio de arquivos</h3>
<form enctype="multipart/form-data" 
	action="<%=request.getContextPath()%>/rest/carros"
	method="post">
	
	<input name="file" type="file" />
	<br /><br />
	<input type="submit" value="Enviar arquivo" />
</form>

<h3>Outra opções...</h3>

<a href="toBase64.jsp">Converter arquivo para Base64</a>
<br />
<a href="uploadBase64.jsp"">Enviar Base64</a>
