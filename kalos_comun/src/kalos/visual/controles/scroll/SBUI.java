
				
	
	
	
	
	
		
		
		
		
		
    
	
	
	
	
		
		
		
		
		
	
	
	
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
	<html>
	<head>
		<script type="text/javascript">
			function onKeyDown() {
				if (null != window.event) {
					switch(window.event.keyCode) {
						case 32:	/* space */
						case 13:	/* enter */
							showDetails();
							break;						
					}
				}
				return false;
			}

			function showDetails() {
				
					window.location =  'pcucmd://ShowMsgDetail?height=250&width=303';
				
			}
	
			function NotifyParent()  { 
				if (parent != null && parent.OnDocReady != null) {
					parent.OnDocReady( document ); 
				}
			} 
		
		</script>

		<script type="text/javascript">
			var css_lang = 'ES';	
			if(css_lang!=null && css_lang!="undefined" && (css_lang.toLowerCase()=='ar' || css_lang.toLowerCase()=='he' )){
				document.write("<style>");
				document.write("body {direction:rtl}");
				document.write("</style>");
			}
		</script>

	
		<link rel="stylesheet" href="../skin/skin.css" />
		<style type="text/css">
			.bannerHoriz {
				width:260;
				height:75;
				border-width: 0px;
			}
			.bannerVert {
				width:90;
				height:155;
				border-width: 0px;
			}
		</style>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
		<META HTTP-EQUIV="MSThemeCompatible" CONTENT="yes">
	</head>
	<body id="sn