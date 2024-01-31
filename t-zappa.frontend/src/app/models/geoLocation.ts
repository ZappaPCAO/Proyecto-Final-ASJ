export interface Country{
    id: string;
    name: string;
    states: State[];
}

export interface State {
    id: string;
    name: string;
    cities: City[];
}

export interface City {
    id: string;
    name: string;
}