Zaawansowane techniki integracji systemow 2018
-

Środowisko do pozyskiwania, integracji i analizy informacji ze wybranych portali blogowych

Aby zaciąnąć posty z portali blogowych należy najpierw uruchomić instancję mongoDB i stworzyć w niej bazę **`ztis`** z kolekcją **`posts`**.

Następnie należy uruchomić klasę **`App`** z modułu **`app`**.

Wybrane tematy należy sprecyzować w odpowiednich plikach - np. `subreddits`

Po uruchomieniu zaciągnięte zostaną wszystkie posty dodane po dacie **`START_DATE`** z klasy **`App`**.