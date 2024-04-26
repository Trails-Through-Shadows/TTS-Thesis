function handleLogin() {
  Loading.dots('Loading...') // Načítání pomocí knihovny Notiflix

  postRequest(`${api}/session/login`, null, {key: licenseNumber, password: password},
    (data: any) => {
      licenseId = data.licenseId;
      token = data.token;

      sessionStorage.setItem('licenseId', licenseId.toString());
      sessionStorage.setItem('token', token);

      logIn();
    },
    (data: any) => {
      Loading.remove();
      Notify.failure(data.message) // Zobrazení notifikace pomocí knihovny Notiflix
    }
  );
}