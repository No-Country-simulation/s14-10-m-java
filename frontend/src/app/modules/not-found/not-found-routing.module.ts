import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageUnderConstructionComponent } from './page-under-construction/page-under-construction.component';
import { AuthGuard } from 'src/app/core/shared/guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    children: [{
      path: '',
      component: PageUnderConstructionComponent
    }],
    canActivate: [AuthGuard]
  }
,]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class NotFoundRoutingModule { }
