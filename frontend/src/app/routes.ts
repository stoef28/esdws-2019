import {Routes} from "@angular/router";
import {LibraryComponent} from "./library/library.component";


export const routes: Routes = [
  {path: 'library', component: LibraryComponent},
  {path: '', redirectTo: 'library', pathMatch: 'full'},
  {path: '**', redirectTo: 'library', pathMatch: 'full'}
];
