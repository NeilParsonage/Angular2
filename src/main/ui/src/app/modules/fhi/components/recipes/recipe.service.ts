import { EventEmitter, Injectable } from "@angular/core";
import { Ingredient } from "../shared/ingredient.model";
import { ShoppingListService } from "../shopping-list/shopping-list.service";
import { Recipe } from "./recipe.model";
@Injectable()

export class RecipeService{

  constructor( private shoppingListService: ShoppingListService){

  }

  recipeSelected = new EventEmitter<Recipe>();

  private recipes: Recipe[]  = [
    new Recipe( 'Tasty Schnitzel',
                'A super tasty Schnitzel - just awesone',
                'https://upload.wikimedia.org/wikipedia/commons/7//72/Schnitzel.JPG',
                  [
                      new Ingredient('Meat',1),
                      new Ingredient('French Fries',20)
                  ]
                ),




    new Recipe( 'Big fat Burger',
                'what else can we say?',
                'https://upload.wikimedia.org/wikipedia/commons/b/be/Burger_King_Angus_Bacon_%26_Cheese_Steak_Burger.jpg',
                  [
                    new Ingredient('Buns',2),
                    new Ingredient('Meat',2)
                  ]
                  ),
                ];


    getRecipes(){

      // slice returns a copy of the array and not a reference to the array itself
      return this.recipes.slice();
    }

    addIngredientsToShoppingList(ingredients: Ingredient[]){
      this.shoppingListService.addIngredients(ingredients);
    }

}