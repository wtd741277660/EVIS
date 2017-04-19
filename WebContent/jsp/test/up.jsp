<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>jQuery File Upload Example</title>
<style>
.bar {
    height: 18px;
    background: green;
}
</style>
<script src=<%= request.getContextPath() + "/plugin/easyui/jquery-1.8.0.min.js" %>></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/vendor/jquery.ui.widget.js" %>></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/jquery.iframe-transport.js" %>></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/jquery.fileupload.js" %>></script>
<script src=<%= request.getContextPath() + "/js/up.js" %>></script>
</head>
<body>
	<input id="fileupload" type="file" name="files[]" data-url="server/php/" multiple>
	<div id="progress">
	    <div class="bar" style="width: 0%;"></div>
	</div>
</body> 
</html>