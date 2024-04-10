import { Injectable } from '@angular/core';
import { AbstractControl, FormGroup } from '@angular/forms';

export function emailValidator(control: AbstractControl) {
  const specialCharacter =
    /^[a-zA-Z0-9._-]{2,}@[a-zA-Z0-9-]{2,}\.[a-zA-Z]{2,}$/;

  const value = control.value as string;

  if (!specialCharacter.test(value)) {
    return {
      email: true,
    };
  }

  return null;
}

export function passwordValidator(control: AbstractControl) {
  const regex =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[-_+|!¡@#$%^&\.{}\*"'\/()=?!¿'´~;,:<>°])[A-Za-z\d-_+|!¡@#$%^&\.{}\*"'\/()=?!¿'´~;,:<>°]+$/g;
  const value = control.value as string;
  const isValid = regex.test(value);

  if (!isValid) {
    return { password: true };
  }

  return null;
}

@Injectable({
  providedIn: 'root',
})
export class GeneralValidator {
  hasError(form: FormGroup, control: string, error: string) {
    return (
      form.controls[control].touched && form.controls[control].hasError(error)
    );
  }
}
