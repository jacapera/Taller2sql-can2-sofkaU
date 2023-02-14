import { Component } from '@angular/core';
import { Product } from './product.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'app-taller2sql';
  name = 'Jesús Capera';
  age = 41;
  imgBonita = "https://www.soycarmin.com/__export/1499716847736/sites/debate/img/2017/07/10/rachel-mcadams-curiosida4.jpg_463833556.jpg";
  btnDisabled = true;
  person = {
    name: "Jesús Capera",
    age: 41,
    img:'https://www.soycarmin.com/__export/1499716847736/sites/debate/img/2017/07/10/rachel-mcadams-curiosida4.jpg_463833556.jpg'
  }

  emojis = [ '😂' , '🐦', '🐳','🌮', '💚']
  names: string[] = ['Jesus', 'Julian', 'Sebastian']

  newName = '';

  products: Product[] = [
    {
      name: 'La mejor camara',
      price: 565,
      image: 'https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?auto=compress&cs=tinysrgb&w=600',
      category: 'all',
    },
    {
      name: 'Bicicleta casi nueva',
      price: 356,
      image: 'https://images.pexels.com/photos/100582/pexels-photo-100582.jpeg?auto=compress&cs=tinysrgb&w=600'
    },
    {
      name: 'Colleción de albunes',
      price: 34,
      image: 'https://images.pexels.com/photos/4200745/pexels-photo-4200745.jpeg?auto=compress&cs=tinysrgb&w=600'
    },
    {
      name: 'Mis libros',
      price: 23,
      image: 'https://images.pexels.com/photos/694740/pexels-photo-694740.jpeg?auto=compress&cs=tinysrgb&w=600'
    },
    {
      name: 'Casa para perro',
      price: 34,
      image: 'https://www.rimax.com.co/media/wysiwyg/mascotas_600x600.jpg'
    },
    {
      name: 'Gafas',
      price: 3434,
      image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToMyYD7QsUT4HgitmQLlZ4pRehuigLLTatqQ8qFp0DZVEiF0MjiVbIWklo_klavoxV6Pk&usqp=CAU'
    }
  ]

  toggleButton() {
    this.btnDisabled = !this.btnDisabled;
  }

  increaseAge() {
    this.person.age += 1;
  }

  onScroll(event: Event) {
    const element = event.target as HTMLElement;
    console.log(element.scrollTop);
  }

  changeName(event: Event) {
    const element = event.target as HTMLInputElement;
    this.person.name = element.value;
  }

  addName() {
    this.names.push(this.newName);
    this.newName = '';
  }

  deleteName(index: number) {
    this.names.splice(index, 1);
  }

  deleteEmoji(index: number) {
    this.emojis.splice(index, 1);
  }

}
