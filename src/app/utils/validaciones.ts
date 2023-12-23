import { providers, Provider } from "../models/provider";


// Funciones auxiliares
function verificarCaracteresEspeciales(value: string): boolean{
    const regex: RegExp = /^([0-9 \u00c0-\u01ffa-zA-Z'\-\_ .@/:])+$/; 

    console.log('este valor es: ' + value + ' => ' + regex.test(value));

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

export function verificarDatos(provider: Provider): boolean{  
    const values = Object.values(provider);

    return( values.every(value => {
        console.log('Es: ' + typeof(value));
        return ((typeof(value)==='string') ? 
            verificarCaracteresEspeciales(value) : // Si es de tipo string llamo a esta funcion.
            verificarSoloNumeros(value)            // Si es de tipo number llamo a esta funcion.
        );
    }))
};

export function verificarLongitudes(provider: any): boolean{
    let band = true; 

    for (const key in provider) {
        let longitud = (''+provider[key]).length;

        if(band && key !== 'id'){
            band = (longitud >= 3); // Verifico que sea al menos 3 en todos los campos.
            
            if(key === 'cod'){ // Lo trato distinto, necesito que sea >=4
                band = (longitud >= 4);
            }
        }
        console.log(`KEY=${key} ; LONGITUD=${longitud} ; BAND=${band}`);        
    }

    return band;
}

export function verificarCamposEspeciales(provider: any): boolean{
    let band = true;

    for (const key in provider) {
        if(band){
            if(key === 'cuil'){
                band = esCuit(provider[key]);
            }
            if(key === 'email' || key === 'emailEmpresa'){
                band = esCorreo(provider[key]);
            }
            if(key === 'sitioWeb'){
                band = esWeb(provider[key]);
            }
        }        
    }

    return band;
}