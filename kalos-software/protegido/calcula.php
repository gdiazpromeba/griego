<?php

$ns=$numeroDeSerie;

$cadena='WPERADWRAQUEPASAHACERATWQUENWPUEDWCWMUNSCARMECWNLAESTANCSAWCURREALGWCHES';

$suma=0;

for ($i=0; $i<strlen($ns); $i++){
  $suma=$suma + ord($ns[$i]);
}

$comienzo=$suma % 17;

$stringBuffer='';

for ($i=0; $i<strlen($ns); $i++){
  $car=$ns[$i];
  if ($car=='A'){
    $stringBuffer.='A';
  }else{
    $indice=ord($car);
    $indice=$comienzo + $indice;
    $indice=$indice % strlen($cadena);
	$stringBuffer.=$cadena[$indice];
  }
}

return $stringBuffer;

?>
