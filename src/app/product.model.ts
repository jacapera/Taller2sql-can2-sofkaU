export interface Product {
  name: string;
  price: number;
  image: string;
  category?: string; //el signo de interrogación es para indicar que sea opcional
}
