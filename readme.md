## Star Wars Characters
Feito por: Felipe Lugão Eccard.
### Requisitos:
```text
1 - Desenvolver um aplicativo que exibirá a listas dos personagens de Star Wars utilizando a API httts://swapi.co/.
2 - O aplicativo deverá ter uma tela principal com a lista de personagens com a possibilidade de filtrar por nome e
    por filme em qual ele aparece.
3 - Ao clicar em um personagem, deverá ser exibido detalhes do mesmo, como altura, peso, sexo e filmes em que ele aparece.
4 - Deve ser feito a sincronização dos filmes e os personagens ao entrar no aplicativo pela primeira vez.
5 - Para persistência dos dados deve ser usado o Realm.
6 - Deve ser utilizada a arquitetura MVVM utilizando a biblioteca Data Binding do
    Android (https://developer.android.com/topic/libraries/data-binding).
```
### Desenvolvimento do App
Como a [API](httts://swapi.co/) está fora do ar, a primeira tarefa foi criar um mock. Para confirmar as informações em relação ao universo de Star Wars, utilizei as seguintes fontes: [Wikipedia](https://pt.wikipedia.org/wiki/Star_Wars), [starwars.com](https://www.starwars.com/) e [IMDb](https://www.imdb.com).
No desenvolvimento do projeto utilizei a arquitetura MVVM usando Dagger2, Realm, Retrofit, Coil e algumas outas bibliotecas para um uso específico:

- [Timber](https://github.com/JakeWharton/timber) para logs.
- [AvatarImageGenerator](https://github.com/skyways/AvatarImageGenerator) para criar um avatar quando necessário.
- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) para possibilitar o teste de UI de forma isolada.
- [Retrofit-mock](https://github.com/square/retrofit/tree/master/retrofit-mock) para realizar o teste unitário da API.

#### Detalhes de implementação
A persistência de dados utilizando o Realm está isolada em uma camada. Dessa forma não tenho uma grande dependência do Realm. Sendo assim, tenho a possibilidade de mudar de persistência sem que haja necessidade de ter uma grande refatoração. Porém sempre que ocorre uma integração com essa camada de persistência, tenho a desvantagem ao realizar cópias de objetos.
Os pacotes do aplicativo estão separadas na estrutura abaixo:

- **data:** Contém os dados, acesso e manipulação.
- **di:** Configurações de injeção de dependências.
- **ui:** Classes da camada de View (Activity e Fragments) e camada de ViewModel.
- **util:**  Classes de utilidade.
##### Testes
- Cobertura de 100% na camada de ViewModel.
- Teste na camada da API do retrofit (StarWarsApi).
- Testes na camada repository.
- Testes isolados em todos os fragments.

#### Extras
- Tela de Filmes com informações + trailer.

### Build e testes
Para rodar os testes unitários:<br>
`./gradlew testDebugUnitTest`<br>
Para rodar os testes instrumentais:<br>
`./gradlew app:connectedAndroidTest`<br>
Instalação do aplicativo:<br>
`./gradlew app:installDebug`

### Trabalhos futuros
- Implementar mais testes na camada de UI e em outas camadas como a de persistência.
- Publicar o mock em um servidor
- Adicionar o tratamento de erros de comunicação com servidor.
- Adicionar o suporte à paginação na API e na interação com a camada de persistência.
- Setup do CI/CD

##### Referências
- [Architecture-components-samples](https://github.com/android/architecture-components-samples)
- [Realm-integration-in-android-best-practices](https://medium.com/@Viraj.Tank/realm-integration-in-android-best-practices-449919d25f2f)
- [Android-mvvm-architecture](https://github.com/MindorksOpenSource/android-mvvm-architecture)
- [Android-best-practices](https://github.com/futurice/android-best-practices)
- [MVVM-with-kotlin-android-architecture-components](https://proandroiddev.com/mvvm-with-kotlin-android-architecture-components-dagger-2-retrofit-and-rxandroid-1a4ebb38c69)
- [Databinding-samples](https://github.com/android/databinding-samples)