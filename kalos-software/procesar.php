<?php
/*
 * process.php
 *
 * PHP Toolkit for PayPal v0.50
 * http://www.paypal.com/pdn
 *
 * Copyright (c) 2004 PayPal Inc
 *
 * Released under Common Public License 1.0
 * http://opensource.org/licenses/cpl.php
 *
*/

//Configuration File
include_once('includes/config.inc.php');

//Global Configuration File
include_once('includes/global_config.inc.php');

?>



<html>
<head><title>::PHP PayPal::</title></head>
<body onLoad="paypal_form.submit();">
<form method="post" name="paypal_form" action="<?=$paypal[url]?>">

   <input type="hidden" name="return" value="success.php"/>
   <input type="hidden" name="rm" value="2"/>


<?php
//show paypal hidden variables



 $paypal[item_name]='KALOS 4.0 activation key';
 $paypal[item_number]=1;
 $paypal[quantity]=1;
 $paypal[amount]=29.00;
 $paypal[success_url]='success.php';
 $paypal[custom_field]='1111222233334444';





  showVariables();

?>

<center><font face="Verdana, Arial, Helvetica, sans-serif" size="2" color="333333">Processing Transaction . . . </font></center>

</form>
</body>
</html>
