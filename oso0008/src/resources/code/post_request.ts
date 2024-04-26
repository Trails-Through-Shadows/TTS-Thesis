export function postRequest(url: string, token: string | null, data: any, successCallback: Function, failureCallback: Function): void {
  const request = new XMLHttpRequest();

  console.log(`POST | ${url}`);

  request.onreadystatechange = () => {
    if (request.readyState === 4) {
      if (request.status === 200) {
        if (request.responseText) {
          const response = JSON.parse(request.responseText);
          successCallback(response);
        }
        else {
          successCallback();
        }
      }
      else {
        console.log('Error: ' + request.status);
        const response = JSON.parse(request.responseText);
        failureCallback(response);
      }
    }
  }

  request.open('POST', url, true);
  request.setRequestHeader('Content-Type', 'application/json');
  request.setRequestHeader('Accept', 'application/json');
  if (token) {
    request.setRequestHeader('Authorization', `Basic ${token}`);
  }
  request.send(JSON.stringify(data));
}