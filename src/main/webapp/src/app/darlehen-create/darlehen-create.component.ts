import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, Validators} from '@angular/forms';
import {DarlehenService} from '../darlehen.service';
import {ToastService} from '../toast.service';

@Component({
  selector: 'app-darlehen-create',
  templateUrl: './darlehen-create.component.html',
  styleUrls: ['./darlehen-create.component.css']
})
export class DarlehenCreateComponent implements OnInit {

  status: { value: string, bezeichnung: string }[] = [
    {
      value: 'PLAN',
      bezeichnung: 'Plan'
    }, {
      value: 'GUELTIG',
      bezeichnung: 'Gültig'
    }, {
      value: 'ARCHIV',
      bezeichnung: 'Archiv'
    }
  ];

  darlehenForm = this.fb.group({
    anwender: ['', Validators.required],
    dalenummerbank: ['', Validators.required],
    laufzeitende: ['', [Validators.required, Validators.pattern(/\d{2}\.\d{2}\.\d{4}/)]],
    festzinsdatum: ['', [Validators.required, Validators.pattern(/\d{2}\.\d{2}\.\d{4}/)]],
    darlehensbetrag: [''],
    restschuld: [''],
    status: ['', Validators.required],
  });

  constructor(private fb: FormBuilder, private darlehenService: DarlehenService,
              private toastService: ToastService) {
  }

  ngOnInit(): void {
  }

  submit(): void {
    if (this.darlehenForm.invalid) {
      this.darlehenForm.markAllAsTouched();
    } else {
      this.darlehenService.createDarlehen(this.darlehenForm.value).subscribe(data => {
        if (data.success) {
          this.toastService.show('Erfolgreich', {classname: 'bg-success'});
        }
      });
    }
  }

  getValidationClass(element: AbstractControl): any {
    const invalid = element.invalid && (element.touched || element.dirty);
    const valid = !element.invalid && (element.touched || element.dirty);
    return {
      'is-valid': valid,
      'is-invalid': invalid
    };
  }

}
