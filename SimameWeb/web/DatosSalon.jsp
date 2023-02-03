<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <script type="text/javascript">

            function validarDatos(){
                    //hay que validar que nada quede en blanco
                    alert("Hay que validar los datos...");

                    return true;
            }

        </script>
        
    </head>
    <body>
        <form method="post" action="ServletSalon">
            <h1>Datos del Salon</h1>
            <table border="1">
                <tr>
                    <td>NUM_SALON</td>
                    <td>
                        <input type="number" id="txtNum_salon" name="num_salon"
                               maxlength="30" style="width:400px;" placeholder="Ingrese su Numero_salon"/>
                    </td>
                </tr>
                <tr>
                    <td>CANT_CAMAS</td>
                    <td>
                        <input type="number" id="txtCant_camas" name="cant_camas" 
                               placeholder="Ingrese su Cant_camas" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>AREA</td>
                    <td>
                        <input type="text" id="txtArea" name="area" 
                               placeholder="Ingrese su area" style="width:400px;"/>
                    </td>
                </tr>
                <tr>
                    <td>CEDULA_DOCTOR</td>
                    <td>
                        <input type="number" id="txtCedula_doctor" name="cedula_doctor" 
                               placeholder="Ingrese su cedula_doctor" style="width:400px;"/>
                    </td>
                </tr>
            </table>
            <br/><br/>

            <input type="reset" value="Limpiar"/>
            <input type="submit" value="Enviar datos" onclick="return validarDatos();"/>


        </form>
    </body>
</html>

