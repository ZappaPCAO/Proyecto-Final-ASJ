import { Article } from "../models/article";
import { Provider } from "../models/provider";
import { PurchaseOrder } from "../models/purchase-order";
import { ProviderService } from "../services/provider.service";

// Funciones auxiliares
function verificarCaracteresEspeciales(value: string): boolean{
    const regex: RegExp = /^([0-9 \u00c0-\u01ffa-zA-Z'\-\_ .@/:])+$/; 

    console.log('este valor es: ' + value + ' => ' + regex.test(value));

    if(typeof value === 'object')
        return true;

    return regex.test(value);
}

function verificarSoloNumeros(value: number): boolean{
    const regex: RegExp = /^([0-9\_.,])+$/; 
    console.log(`este valor es: ${value} => ${regex.test(''+value)}`);

    return regex.test(''+value);
}

function esCorreo(correo: string): boolean {
    const regex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(correo);
}
function esCuit(cuit: string): boolean {
    const regex: RegExp = /^\d{2}-\d{8}-\d{1}$/;
    return regex.test(cuit);
}
function esWeb(web: string): boolean {
    const regex: RegExp = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i;
    return regex.test(web);
}
//--------------------------------------------------------------------------//

export function verificarDatos(data: Provider | Article | PurchaseOrder): boolean{  
    const values = Object.values(data);
    let bands: boolean[] = [];

    for (const value of values) {
        // Si es un objeto lo tengo que manejar de esta forma, xq al usar interfaces el typeof no funciona del todo correcto.
        if (value === '[object Object]'){
            bands.push(true);
        }else if(typeof value === 'string' && value !== null){
            bands.push(verificarCaracteresEspeciales(value)); // verifico que no tenga caracteres raros.
        }else if(typeof value === 'number'){          
            bands.push(verificarSoloNumeros(value));  // verifico que sea numeros, puntos y comas.
        }
    }
    return bands.every(band => band);
}

export function verificarLongitudes(data: any): boolean{
    let band = true; 

    for (const key in data) {
        let longitud = (''+data[key]).length;

        if(band && (key !== 'id' && key !== 'descri')){ // Si es id o descri omito esta parte. Ya la descripcion no es obligatoria y el id lo manejo yo.
            band = (longitud >= 3); // Verifico que sea al menos 3 en todos los campos.
            if(key === 'cod'){      // Lo trato distinto, necesito que sea >=4
                band = (longitud >= 4);
            }
            if(key === 'cp'){
                band = (longitud <= 8); // Lo trato distinto, necesito que sea <=8
            }
        }
        console.log(`KEY=${key} ; LONGITUD=${longitud} ; BAND=${band}`);        
    }
    return band;
}

export function verificarCamposEspeciales(data: any): boolean{
    let band = true;

    for (const key in data) {
        if(band){
            if(key === 'datosFiscales')
                band = esCuit(data[key].cuit);
            
            if(key === 'email'){ // Verifico los 2 correos
                band = esCorreo(data[key]);
                //band = esCorreo(data['datosContacto'].email); omito de momento.
            }            
            if(key === 'sitioWeb')
                band = esWeb(data[key]);               
        }        
    }
    return band;
}