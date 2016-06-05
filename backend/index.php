<?php

require 'vendor/autoload.php';
$db = Connection::getConnection();
$app 	=	new \Slim\Slim();

$app->get('/grafica1', function () use ( $app ) {
	
	$col1 = array();
	$col2 = array();
	$valores =  Grafica1::where('id', '>=', 33)->get();

	foreach ($valores as $key => $value) {
		$col1[] = $value->COL_1;
		$col2[] = $value->COL_2;
	}

	$chunk1 = array_chunk($col1, 4);
	$chunk2 = array_chunk($col2, 4);

	$result = array("categories"=>$chunk1,"data"=>$chunk2);
	header('Content-type: application/json');

	echo $_GET["callback"]."(".json_encode($result).")";

});


$app->get('/grafica2', function () use ( $app ) {
	
	$col1 = array();
	$col2 = array();
	$valores =  Grafica2::where('id', '>=', 33)->get();
	//$valores =  Grafica2::all();

	foreach ($valores as $key => $value) {
		$col1[] = $value->COL_1;
		$col2[] = $value->COL_2;
	}

	$chunk1 = array_chunk($col1, 4);
	$chunk2 = array_chunk($col2, 4);

	$result = array("categories"=>$chunk1,"data"=>$chunk2);
	header('Content-type: application/json');

	echo $_GET["callback"]."(".json_encode($result).")";

});


$app->get('/grafica3', function () use ( $app ) {
	
	$col1 = array();
	$col2 = array();
	$valores =  Grafica3::where('id', '>=', 33)->get();
	//$valores =  Grafica3::all();

	foreach ($valores as $key => $value) {
		$col1[] = $value->COL_1;
		$col2[] = $value->COL_2;
	}

	$chunk1 = array_chunk($col1, 4);
	$chunk2 = array_chunk($col2, 4);

	$result = array("categories"=>$chunk1,"data"=>$chunk2);
	header('Content-type: application/json');
	echo $_GET["callback"]."(".json_encode($result).")";
});


$app->get('/grafica4', function () use ( $app ) {
	
	$col1 = array();
	$col2 = array();
	$valores =  Grafica4::where('id', '>=', 33)->get();
	//$valores =  Grafica4::all();

	foreach ($valores as $key => $value) {
		$col1[] = $value->COL_1;
		$col2[] = $value->COL_2;
	}

	$chunk1 = array_chunk($col1, 4);
	$chunk2 = array_chunk($col2, 4);

	$result = array("categories"=>$chunk1,"data"=>$chunk2);
	header('Content-type: application/json');
	echo $_GET["callback"]."(".json_encode($result).")";

});

$app->run();