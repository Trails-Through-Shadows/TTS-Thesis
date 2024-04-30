export class Data {
    constructor(
        private root: HTMLElement,
        private template: string,
    ) {}

    public queryData(url: string, lazy: boolean = false, include: [] = []): void {
        const currentTime = new Date().getTime();

        // Block the UI while loading
        const Notiflix = window['Notiflix'];
        Notiflix.Block.circle("#entryDataReplacement", 'Loading...');

        const request = new XMLHttpRequest();
        request.onreadystatechange = () => {
            if (request.readyState === 4 && request.status === 200) {
                const took = new Date().getTime() - currentTime;
                console.log(`Data | Query took ${took}ms`);

                // Replace the object data
                const tableDataElement = this.root.querySelector('#entryDataReplacement');
                tableDataElement.innerHTML = request.responseText;

                // Evaluate the script
                const dataScript = document.querySelector('#entryDataScript');
                if (dataScript) eval(dataScript.innerHTML);
            }
        };

        let newUrl = url + `?template=${this.template}&lazy=${lazy}`
        newUrl += include.length ? `&include=${include.join(',')}` : '';

        console.log(`Data | Querying data from ${url}`)
        console.log(`Data |  - Params: ${newUrl.replace(url, '')}`);
        request.open('GET', newUrl, true);
        request.send();
    }
}