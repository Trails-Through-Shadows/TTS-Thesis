def requestAPI(method, url, jsonData=None):
    print("")
    print("Requesting: ", url)

    # Decode data if binary
    if isinstance(jsonData, bytes):
        print("- Decoding data...")
        jsonData = jsonData.decode('utf-8')

    if jsonData is not None:
        print("- Data: ", jsonData)

    # Set credentials
    credentials = "admin:admin"
    hash = base64.b64encode(credentials.encode('utf-8')).decode('utf-8')

    try:
        response = req(
            method=method,
            url=url,
            json=jsonData,
            headers={
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': 'Basic ' + hash
            },
        )

        responseCode = response.status_code
        responseData = {}

        if responseCode != 204:
            responseData = response.json()
    except Exception as e:
        print(e)
        return 404, {'error': "Could to make connection to '" + url + "'"}

    print("- Response: ", responseCode, processJsonData(responseData))
    return responseCode, processJsonData(responseData)