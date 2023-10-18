
## About
This App allows you to:
- Fetch and display a list of crypto currencies.
- Switch currency to `SEK`, `USD`, `INR` in the app and get updated prices.

## Architecture
The app follows the MVVM (Model-View-ViewModel) architecture, separating different components for a clean and structured codebase.
![architecture](https://github.com/karibadr/cryptolist/assets/3876922/0c36c3fd-85fe-43c7-911f-d17069ac8b25)

### Data
The data module is at the core of the application, responsible for managing data sources and handling data retrieval. It contains a repository that acts as an intermediary between remote and local data sources. The data is exposed using Kotlin's Flow, allowing asynchronous updates and providing real-time information on the status of requests (e.g., Loading, Success, Failure) to all other layers.

### UI
The UI module has all user interface components, including viewmodel. The ViewModel consume data from the repository and facilitate communication with the UI, ensuring interaction between the data layer and the user interface.

## Technologies Used
`Hilt`: for efficient and convenient dependency injection, making it easier to manage dependencies and maintain code structure.

`Retrofit`: Retrofit is employed for handling network calls, retrieve data from remote servers.

`Flow`: Flow, a part of Kotlin's Coroutines,handling data streams asynchronously, ensuring responsive updates.

`Jetpack Compose`:  modern Android UI toolkit, offering a more flexible and declarative way to build user interfaces.

## Areas for Improvement

`Paging Mechanism`: Implement a paging mechanism, either through the API or database, as the current API don't support pagination. This will allow users to navigate through extensive lists of cryptocurrencies more efficiently.

`Pull-to-Refresh`: Introduce a pull-to-refresh mechanism, integrating Jetpack Compose's SwipeRefresh functionality to provide a realtime way to refresh the displayed data.

`Error Handling`: Enhance error handling to provide.

`Automatic Retry Mechanism (Throttling)`: Implement an automatic retry mechanism, such as throttling, to manage the rate of data retrieval and reduce the risk of overloading the api.

`Unit Tests`: Add unit tests for the repository, ensuring that data retrieval and processing are reliable and error-free.

`UI Tests`: Implement UI tests to validate the app's functionality,.
