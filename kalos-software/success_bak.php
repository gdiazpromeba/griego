<?php
  session_start();
  $email=$_POST[payer_email];
  $fecha=$_POST[payment_date];
  $numeroDeArticulo=$_POST[item_number];
  $numeroDeSerie=$_POST[custom];
  $_SESSION['numero_de_serie']=$numeroDeSerie;
  $_SESSION['email']=$email;
  $_SESSION['fecha']=$fecha;
  $_SESSION['numero_de_articulo']=$numeroDeArticulo;

  $fromname="KALLISTOS CORPORATION";
  $fromaddress="sales@classicgreek.net";
  $subject="Your KALÓS 3.0 activation key";
  $toaddress=$email;
  $message="Thank you for buying KALÓS 3.0 \nYour activation key is " . (include('/home/kallisto/public_html/kalos/protegido/calcula.php')) . "\n";
  $message=$message . "If you experience any difficulty activating your product, please contact us at  support@classicgreek.net";


  $headers  = "MIME-Version: 1.0\n";
  $headers .= "Content-type: text/plain; charset=iso-8859-1\n";
  $headers .= "X-Priority: 3\n";
  $headers .= "X-MSMail-Priority: Normal\n";
  $headers .= "X-Mailer: php\n";
  $headers .= "From: \"".$fromname."\" <".$fromaddress.">\n";
  mail($toaddress, $subject, $message, $headers);
?>
<html>
  <body>
    <?php echo 'del post' . $_POST[payer_email]; ?><br/>
    <?php echo 'el email es' . $email; ?><br/>
    <?php echo 'de la sesión' . $_SESSION['fecha']; ?><br/>
  </body>
</html>
