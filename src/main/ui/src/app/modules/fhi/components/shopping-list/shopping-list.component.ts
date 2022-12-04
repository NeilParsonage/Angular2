import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';
import { ShoppingListService } from './shopping-list.service';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.scss']
})
export class ShoppingListComponent implements OnInit {

  ingredients: Ingredient[];

  constructor(private slService: ShoppingListService) { }

  // removed with service
  /*
  onIngredientAdded(ingredient: Ingredient) {
    this.ingredients.push(ingredient);
  }
  */

  ngOnInit(): void {
    this.ingredients = this.slService.getIngredients();
    this.slService.ingredientsChanged.subscribe( (ingredients: Ingredient[] ) => { this.ingredients = ingredients; } );
  }

}
