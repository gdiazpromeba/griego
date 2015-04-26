<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Contact information</title>
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
			<br/>
			<p class="bottom">
			   <strong>Mailing address</strong><br/>
			   8568 Warren Pkwy #514<br/>
			   Frisco, TX 75034
			   USA
			</p>
			<p class="bottom">
			   <strong>Phone number:</strong><br/>
			   (512)621-2274<br/>
			</p>
                        <p class="bottom">
			   <strong>Or use this email form:</strong><br/>
			   We will answer in less than 24 hours.<br/>
			   (You can paste Greek text if necessary).
			   <form method="post" action="accionMail.php">
				   <!-- DO NOT change ANY of the php sections -->
				   <?php
				     $ipi = getenv("REMOTE_ADDR");
				     $httprefi = getenv ("HTTP_REFERER");
				     $httpagenti = getenv ("HTTP_USER_AGENT");
				   ?>
				   <input type="hidden" name="ip" value="<?php echo $ipi ?>" />
				   <input type="hidden" name="httpref" value="<?php echo $httprefi ?>" />
				   <input type="hidden" name="httpagent" value="<?php echo $httpagenti ?>" />
				   <table>
				     <tr>
				       <td>My name:</td>
				       <td><input type="text" name="visitor" size="35" /></td>
				     </tr>
				     <tr>
				       <td>My email:</td>
				       <td><input type="text" name="visitormail" size="35" /></td>
				     </tr>
					 <tr>
					   <td>The subject is:</td>
					   <td>
					     <select name="attn" size="1">
				           <option value="Request general information">Request general information</option>
				           <option value="Sales">Sales</option>
				           <option value="Technical Support">Technical Support</option>
				           <option value="Suggest a new feature">Suggest a new feature</option>
				           <option value="Report a defect">Report a defect</option>
				           <option value="Other">Other</option>
				         </select>
                       </td>
                     </tr>
                     <tr>
                       <td valign="top">Mail Message:</td>
                       <td>
                         <textarea name="notes" rows="4" cols="40"></textarea>
                       </td>
                     </tr>
                     <tr>
                       <td/>
                       <td><input type="submit" value="Send Mail" /></td>
                     </tr>
                   </table>
			   </form>
		    </p>
	    </div>
    </div>
</div>
<?php include 'footer.php'; ?>
</body>
</html>
