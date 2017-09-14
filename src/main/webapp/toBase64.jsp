<h3>Converter um arquivo para Base64</h3>
<form enctype="multipart/form-data" 
	action="<%=request.getContextPath()%>/rest/carros/toBase64"
	method="post">
	
	<input name="file" type="file" />
	<br /><br />
	<input type="submit" value="Enviar arquivo" />
</form>

<a href="upload.jsp">Voltar</a>