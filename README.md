##About The app
Operations are performed by calling api endpoints over the network.

Local data is in effect immutable, the client just downloads updated
versions of data as needed. Local data is only modified as a result of
api operations.

The domain model objects are used throughout the app. They are plain
Kotlin objects.

State is stored in a simple database using Room Libary. It loads
from disk on app startup if database is not empty. All data are kept in memory
once a successful api call occurred.

Activities and fragments are for presentation logic only. Each
activity or fragment should have its own ViewModels where business
logic is placed. The ViewModel reacts to data changes via LiveData.

View Models, API services, and repositories are injected using
Dagger Library.


The application displays a list of 20 Rick and Morty characters where each list
item contains the character's name, status, and species using a RecyclerView with
a custom Adapter.

MainFragment   ->      DetailsFragment
      |                      |
     \/                     \/
recyclerview      load image/api call to get location data


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Push your branch (git push origin my-new-feature)
5. Create a new Pull Request

