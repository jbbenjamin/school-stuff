<?php
/**
 * Created by PhpStorm.
 * User: Justyn
 * Date: 4/12/2017
 * Time: 9:05 PM
 */
$command = $_POST["command"];

if($command == "Insert"){
    $field1 = $_POST["URL"];
    $field2 = $_POST["description"];
    $query = "INSERT "."INTO urltable VALUES (\"".$field1."\", \"".$field2."\")";
    print("Record inserted");
}

else if($command == "Update"){
    $field1 = $_POST["URL"];
    $field2 = $_POST["description"];
    $query = "UPDATE urltable "."SET description = \"".$field2."\" where URL = \"".$field1."\"";
}

else if($command == "Search"){
    $keyword = $_POST["Search"];
    if($keyword == "*"){
        $query = "SELECT ".$keyword." from urltable";
    }
    else{
        $query =  "SELECT *"." FROM urltable WHERE URL LIKE \"%".$keyword."%\" OR description LIKE \"%".$keyword."%\"";
    }
}

else if($command == "search"){
    $keyword = $_POST["search"];
    $query = "SELECT ".$keyword." from urltable";
}

if( ! ( $database = mysql_connect("CopDataSvr.ccec.unf.edu", "n00853961", "53961Spr2017#"))){
    die("Could not connect to database </body></html>");
}

if( !mysql_select_db("n00853961", $database)){
    die("Could not open urls database </body></html>");
}

if( ! ( $result = mysql_query($query, $database))){
    printf("<p>Could not execute query!</p>");
    die(mysql_error() . "</body></html>");
}

    print("<table>");

while ( $row = mysql_fetch_row($result)){
    print("<tr>");
    foreach ($row as $value){
        print("<td>$value</td>");
    }
    print("</tr>\n");
}
mysqli_close($database);
?>