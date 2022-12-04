import { EventEmitter } from "@angular/core";
import { Ingredient } from "../shared/ingredient.model";

export class ShoppingListService{

  ingredientsChanged = new EventEmitter<Ingredient[]> ();

  private ingredients: Ingredient[] = [
    new Ingredient('Apples',5),
    new Ingredient('Tomatoes',10)
  ];


  getIngredients(){
    return this.ingredients.slice();
  }

  addIngredient(ingredient: Ingredient){
    this.ingredients.push(ingredient);
    this.ingredientsChanged.emit(this.ingredients.slice());
  }

  addIngredients(ingredients: Ingredient[] ){

    // simple alternative with for list but too many events emitted
    /*
    for ( let ingredient of ingredients){
      this.addIngredient(ingredient);
    }
    */

    // splice operator pushed elements of an array onto a list
    this.ingredients.push(...ingredients);

    // the slice makes a copy of the array so we don't send back the original
    this.ingredientsChanged.emit(this.ingredients.slice());

  }

}