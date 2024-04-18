import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () =>
      import('./modules/auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: '',
    loadChildren: () =>
      import('./modules/home/home.module').then((m) => m.HomeModule),
  },
  {
    path: 'search-doctor',
    loadChildren: () =>
      import('./modules/home/home.module').then((m) => m.HomeModule),
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./modules/dash-doctor/dash-doctor.module').then((m) => m.DashDoctorModule),
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./modules/dash-patient/dash-patient.module').then((m) => m.DashPatientModule),
  },
  {
    path: 'page-under-construction',
    loadChildren: () =>
      import('./modules/not-found/not-found.module').then(
        (m) => m.NotFoundModule
      ),
  },
  { path: '**', loadChildren: () => import('./modules/not-found/not-found.module').then(m => m.NotFoundModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
