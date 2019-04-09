import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreacionPersonasComponent } from './nucleo/capa/principal/creacion-personas/creacion-personas.component'
import { ApoyoComponent } from './nucleo/capa/principal/apoyo/apoyo.component';
const routes: Routes = [
	{
		path: 'personas-crear',
		component: CreacionPersonasComponent
	},
	{
		path: 'componente-apoyo',
		component: ApoyoComponent
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
