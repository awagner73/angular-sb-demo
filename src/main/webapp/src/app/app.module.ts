import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FrameComponent} from './frame/frame.component';
import {DaleOverviewComponent} from './dale-overview/dale-overview.component';
import {HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import localeDe from '@angular/common/locales/de';
import {registerLocaleData} from '@angular/common';
import {SucheCardComponent} from './suche-card/suche-card.component';
import {BezugsobjektInfoComponent} from './bezugsobjekt-info/bezugsobjekt-info.component';
import {RowInfoComponent} from './row-info/row-info.component';
import {DarlehenCreateComponent} from './darlehen-create/darlehen-create.component';
import {ReactiveFormsModule} from '@angular/forms';
import {DarlehenOverviewTableComponent} from './darlehen-overview-table/darlehen-overview-table.component';
import {ToastsContainerComponent} from './toasts-container/toasts-container.component';

registerLocaleData(localeDe);

@NgModule({
    declarations: [
        AppComponent,
        FrameComponent,
        DaleOverviewComponent,
        SucheCardComponent,
        BezugsobjektInfoComponent,
        RowInfoComponent,
        DarlehenCreateComponent,
        DarlehenOverviewTableComponent,
        ToastsContainerComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        NgbModule,
        ReactiveFormsModule
    ],
    providers: [
        {provide: LOCALE_ID, useValue: 'de-DE'}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
