import i18next from 'i18next';

i18next.init({
    lng: 'cz',
    resources: {
        cz: {
            translation: {
                "Hello World!": "Ahoj Světe!",
            }
        }
    }
});

i18next.t('Hello World!'); // -> 'Ahoj Světe!'