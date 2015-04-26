<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Greek Software</title>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
</div>
<div id="content">
	<div id="colOne">
		<div id="logo">
			<h1><a href="#">Kal&oacute;s</a></h1>
			<h2>beautiful Greek software</a></h2>
		</div>
		<?php include 'menu.php'; ?>
	</div>
	<div id="colTwo">
		<div class="box">
		<?php
			if(!$visitormail == "" && (!strstr($visitormail,"@") || !strstr($visitormail,"."))){
				echo "<h4>The email you entered doesn't appear to be valid</h4>\n";
				$badinput = "<h4>The message was NOT sent</h4>\n";
				echo $badinput;
			}
			if(empty($visitor) || empty($visitormail) || empty($notes )) {
				echo "<h4>You should fill in all the fields on the mail form</h4>\n";
			}

			$todayis = date("l, F j, Y, g:i a") ;

			$attn = $attn ;
			$subject = $attn;

			$notes = stripcslashes($notes);

			$message = " $todayis [EST] \n
			Attention: $attn \n
			Message: $notes \n
			From: $visitor ($visitormail)\n
			Additional Info : IP = $ip \n
			Browser Info: $httpagent \n
			Referral : $httpref \n
			";

			$from = "From: $visitormail\r\n";


			mail("info" . "@" . "kalos-software.com", $subject, $message, $from);
		?>

		<p align="center">
			Date: <?php echo $todayis ?>
			<br />
			Thank You : <?php echo $visitor ?> ( <?php echo $visitormail ?> )
			<br />

			Attention: <?php echo $attn ?>
			<br />
			Message:<br />
			<?php $notesout = str_replace("\r", "<br/>", $notes);
			echo $notesout; ?>
			<br />
			<?php echo $ip ?>
	    </p>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) 2006 Sitename.com. All rights reserved. Design by <a href="http://freecsstemplates.org/">Free CSS Templates</a>.</p>
</div>

<div style="font-size: 0.8em; text-align: center;">
<br />
Design downloaded from Zeroweb.org: <a href="http://www.zeroweb.org">Free website templates, layouts, and tools.</a><br />
<br />
</div>
</body>
</html>
