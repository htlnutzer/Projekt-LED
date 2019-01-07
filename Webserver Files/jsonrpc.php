
<?php
        ini_set('display_errors', 1);
        ini_set('display_startup_errors', 1);
        error_reporting(E_ALL);

        $global_access_key = "geheim";

        /* Filter the parameters - AppVersion, AppToken, AppHWID */
        $secret         = $_POST['secret'];
        $led            = $_POST['led'];
        $status         = $_POST['status'];

        // Check if the secret is valid
        if(IsKeyValid($secret,$global_access_key)){

                // Action to perform (either HIGH or LOW)
                if(isset($led) && ($led > 0)){
                        // Set the value of the pin high (turn it on)
                        if(isset($status) && ($status == "on")){
                                shell_exec("/usr/local/bin/gpio -g mode ".$led." out");
                                shell_exec("/usr/local/bin/gpio -g write ".$led." 1");
                                $response = [ 'status'=>'success', 'pin'=>$led, 'value'=>'on' ];
                                header('Content-type: application/json');
                                http_response_code(200);
                                echo json_encode($response);
                        }else{
                                shell_exec("/usr/local/bin/gpio -g mode ".$led." out");
                                shell_exec("/usr/local/bin/gpio -g write ".$led." 0");
                                $response = [ 'status'=>'success', 'pin'=>$led, 'value'=>'off' ];
                                header('Content-type: application/json');
                                http_response_code(200);
                                echo json_encode($response);
                        }

                }else{

                }

        }else{
                http_response_code(403); // Not allowed
                echo "You are not allowed to use this service";
        }

        function IsKeyValid($key,$global_access_key){
                if(isset($key) & ($key != '')){
                        //Open the database connection
                        if($key == $global_access_key){
                        return true;
                        }
                }else{
                        return false;
                }
        }

?>
