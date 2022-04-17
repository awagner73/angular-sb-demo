describe('Darlehenerstellung', () => {
    beforeEach('Erstellung öffnen', () => {
        cy.visit('/create');
    })
    it('Erstelle Darlehen', () => {
        cy.get('#anwender')
            .type('AnwE2E')
            .should('have.value', 'AnwE2E')
            .should('have.class','ng-valid');

        cy.get('#dalenummerbank')
            .type('Dale 1')
            .should('have.value', 'Dale 1')
            .should('have.class','ng-valid');

        cy.get('#laufzeitende')
            .type('01.09.2020')
            .should('have.value', '01.09.2020')
            .should('have.class','ng-valid');

        cy.get('#festzinsdatum')
            .type('02.09.2020')
            .should('have.value', '02.09.2020')
            .should('have.class','ng-valid');

        cy.get('#darlehensbetrag')
            .type('75000')
            .should('have.value', '75000')
            .should('have.class','ng-valid');

        cy.get('#restschuld')
            .type('1000')
            .should('have.value', '1000')
            .should('have.class','ng-valid');

        cy.get('#status')
            .select('Gültig')
            .should('have.value', '2: GUELTIG')
            .should('have.class','ng-valid');

        cy.get('button').contains('Erstellen').click();

        cy.get('ngb-toast').should('be.visible').should('contain.text', 'Erfolgreich');

        cy.visit('/overview');

        cy.get('tbody tr:first-child').should('contain.text', 'AnwE2E');
    });
});
