![alt text](https://i2.wp.com/geek.pizza/wp-content/uploads/2020/04/Copertina-Maestri-del-Rinascimento.jpg)
# Prova Finale Ingegneria del Software anno 2020-2021

L'obbiettivo del progetto è quello di sviluppare una verisione software del gioco da tavola "Maestri del Rinascimento".
Il progetto consiste nell'implementazione di un sistema distribuito composto da un singolo server che gestisce la partita e 
da multipli client.
E' stato usato il pattern architetturale Model View Controller (MVC) ed è stato seguito un approccio di programmazione orientato agli oggetti.
L'elaborato copre sia le regole per le partite multigiocatore sia le regole per le partite singole.
L'interazione da parte dei client con il gioco può avvenire secondo due modalità: utilizzando un'interfaccia da riga da comando (CLI) oppure 
usando l'interfaccia grafica (GUI).
La rete è stata gestita utilizzando l'approccio dei socket.

## Documentazione

### UML 
I seguenti diagrammi delle classi rappresentano rispettivamente i primi diagrammi UML iniziali e i diagrammi UML finali.
* [UML iniziali](https://github.com/pasqualecastiglione/ingswAM2021-Aiello-Capponcelli-Castiglione/blob/master/UML_Iniziale.pdf)
* [UML finali](https://github.com/pasqualecastiglione/ingswAM2021-Aiello-Capponcelli-Castiglione/tree/master/UMLFinal)

### JavaDoc
La seguente documentazione include una descrizione per la maggior parte delle classi e dei metodi usati.
* [JavaDoc](https://github.com/pasqualecastiglione/ingswAM2021-Aiello-Capponcelli-Castiglione/tree/master/JavaDoc)

### Librerie e Plugins

| Libreria/Plugin    |       Descrizione       |
|--------------------|:-----------------------:|
|                    |strumento di gestione per|
|       Maven        |software basati su java e|
|                    |build automation.        |
|                    |                         |
|        JUnit       |Framework di unit testing|
|                    |                         |
|       JavaFx       |Libreria grafica di Java |


### Jars
I seguenti jar sono stati usati per la realizzazione di questo progetto.
[Jars]()


## Funzionalità
 * Regole complete
 * CLI
 * Socket
 * GUI

### Funzionalità Aggiuntive

 * Partite multiple 
 * Editor dei parametri (riusciamo a leggere da file esterno)


## Esecuzione

### Client
Il client è eseguito scegliendo l'interfaccia con cui giocare. Può scegliere se usare CLI o GUI
#### CLI
Il seguente comando lancia il client in modalità CLI:

> --c, --cli

Per specificare l'indirizzo:
> -a=, --address=

Per specificare la porta:
> -p=, --port=

#### GUI
Il seguente comando lancia il client in modalità GUI:
> --client 

Per specificare l'indirizzo:
> -a=, --address=

Per specificare la porta:
> -p=, --port=

### Server
Per lanciare il server è necessario digitare da terminale il seguente comando:
> --server

Per specificare il percorso di un file settings.json personalizzato:
> -s=, --settings=

Per specificare la porta sulla quale il server si mette in ascolto:
> -p=, --port=

### Editor
Per far partire l'editor dei parametri
> --editor


### Componenti del gruppo
* [Pasquale Castiglione](https://github.com/pasqualecastiglione)
* [Angelo Capponcelli](https://github.com/angelocapponcelli)
* [Tommaso Aiello](https://github.com/tommasoaiello)
