import { Article } from "../models/article";
import { Category } from "../models/category";
import { Provider } from "../models/provider";
import { PurchaseOrder } from "../models/purchase-order";

// Funciones auxiliares
export function checkSpecialCharacters(value: string): boolean{
    const regex: RegExp = /^([0-9 \u00c0-\u01ffa-zA-Z'_ .,@/:])+$/;
    return regex.test(value);
}
export function checkOnlyNumbers(value: number): boolean{
    const regex: RegExp = /^([0-9\_.,])+$/; 
    return regex.test(''+value);
}
export function isEmail(correo: string): boolean {
    const regex: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(correo);
}
export function isCuit(cuit: string): boolean {
    const regex: RegExp = /^\d{2}-\d{8}-\d{1}$/;
    return regex.test(cuit);
}
export function isWebsite(web: string): boolean {
    const regex: RegExp = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i;
    return regex.test(web);
}
export function isPhoneNumber(phone: string): boolean {
    const regex: RegExp = /^\(\d{3}\) \d{3}-\d{4}$/;
    return regex.test(phone);
}
// //--------------------------------------------------------------------------//
// Providers

export function checkDataProvider(data: Provider): boolean {
    let bands: boolean[] = [];

    // Verificar campos directos
    bands.push(checkSpecialCharacters(data.codProvider));
    bands.push(checkSpecialCharacters(data.businessName));
    bands.push(isEmail(data.email));
    bands.push(isPhoneNumber(data.phone));

    // Verificar campo location
    const location = data.location;
    bands.push( checkSpecialCharacters(location.street) && checkSpecialCharacters(location.postalCode) && 
                location.city.id > 0 &&  location.city.state.id > 0 && location.city.state.id > 0 
    );

    // Verificar campo sector
    const sector = data.sector;
    bands.push(sector.id > 0);

    // Verificar campo taxData
    const taxData = data.taxData;
    bands.push(taxData.ivaCondition.id > 0 && isCuit(taxData.cuit));

    // Verificar campo contactData
    const contactData = data.contactData;
    bands.push(checkSpecialCharacters(contactData.name) && checkSpecialCharacters(contactData.lastName) && 
               isPhoneNumber(contactData.phone) && isEmail(contactData.email) && checkSpecialCharacters(contactData.role));

    // No requeridos
    if(data.location.number)
        bands.push(checkSpecialCharacters(''+data.location.number));

    if(data.logo)
        bands.push(isWebsite(data.logo));

    if(data.website)
        bands.push(isWebsite(data.website!));
    
    // Verificar si todos los criterios de validación son verdaderos
    console.log("resultado final => "+bands.every(band => band) );
    return bands.every(band => band);
}

export function checkLongsProvider(data: Provider): boolean{
    let bands:boolean[] = []; 

    // Requeridos
    bands.push((''+data.codProvider).length > 3 && (''+data.businessName).length > 3 && (''+data.email).length > 4 &&
               (''+data.phone).length > 9 && (''+data.location.street).length > 1 && (''+data.location.postalCode).length > 3 &&
               (''+data.contactData.name).length > 3 && (''+data.contactData.lastName).length > 3 && (''+data.contactData.phone).length > 9 &&
               (''+data.contactData.email).length > 4 && (''+data.contactData.role).length > 3 && (''+data.taxData.cuit).length > 12
    );
    
    // No requeridos
    if(data.location.number)
        bands.push((''+data.location.number).length > 1);

    if(data.logo)
        bands.push((''+data.logo).length > 3);

    if(data.website)
        bands.push((''+data.website).length > 3);

    // Verificar si todos los criterios de validación son verdaderos 
    console.log("resultado final => "+bands.every(band => band) );
    return bands.every(band => band);
}

// //--------------------------------------------------------------------------//
// ArticlescheckDataPurchaseOrder

export function checkDataArticle(data: Article) : boolean{
    let bands:boolean[] = []; 

    // Requeridos
    bands.push(checkSpecialCharacters(data.codArticle) && checkSpecialCharacters(data.name) && checkOnlyNumbers(data.price));

    // No requeridos
    if(data.image)
        bands.push(isWebsite(data.image));

    if(data.description)
        bands.push(checkSpecialCharacters(data.description));
    
    // Verificar si todos los criterios de validación son verdaderos 
    console.log("resultado final datos => "+bands.every(band => band) );
    return bands.every(band => band);
}

export function checkLongsArticle(data: Article) : boolean{
    let bands:boolean[] = []; 

    // Requeridos
    bands.push((''+data.codArticle).length > 3 && (''+data.name).length > 3 && (''+data.price).length > 1);

    // No requeridos
    if(data.image)
        bands.push((''+data.image).length > 3)
    
    if(data.description)
        bands.push((''+data.description).length > 3);
    
    // Verificar si todos los criterios de validación son verdaderos 
    console.log("resultado final largo => "+bands.every(band => band) );
    return bands.every(band => band);
}

// //--------------------------------------------------------------------------//
// PurchaseOrders

export function checkDataPurchaseOrder(data: PurchaseOrder):boolean{
    let bands:boolean[] = []; 

    // Requeridos
    bands.push(checkSpecialCharacters(data.numPurchaseOrder));

    // No requeridos
    if(data.description)
        bands.push(checkSpecialCharacters(data.description));
    
    // Verificar si todos los criterios de validación son verdaderos 
    console.log("resultado final datos => "+bands.every(band => band) );
    return bands.every(band => band);
}

export function checkLongsPurchaseOrder(data: PurchaseOrder):boolean{
    let bands:boolean[] = []; 

    // Requeridos
    bands.push((''+data.numPurchaseOrder).length > 1 );

    if(data.description)
        bands.push((''+data.description).length > 3);
    
    // Verificar si todos los criterios de validación son verdaderos 
    console.log("resultado final largo => "+bands.every(band => band) );
    return bands.every(band => band);
}