# Pomodoro App

## Descrição
Aplicativo Android para técnica Pomodoro com login, cadastro, auto-login e gerenciamento de sessões do usuário.  
O backend é implementado em **Spring Boot**, fornecendo APIs REST seguras com **JWT** para autenticação.

---

## Funcionalidades
- Registro e login de usuários  
- Auto-login via JWT  
- Logout  
- Tela de sessão com email do usuário  
- Temporizador, Estatísticas e Metas  
- Design moderno com Jetpack Compose  

---

## Tecnologias Frontend (Android)
- Kotlin  
- Jetpack Compose  
- Retrofit  
- DataStore  
- MVVM (Model-View-ViewModel)  

---

## Tecnologias Backend (Spring Boot)
O backend é desenvolvido em **Java/Kotlin com Spring Boot**, oferecendo:

- **Spring Web**: para criação de APIs REST (`@RestController`, `@RequestMapping`)  
- **Spring Security**: autenticação e autorização via **JWT**  
- **Spring Data JPA**: persistência de usuários no banco de dados  
- **DTOs**: para comunicação com o frontend (`LoginRequest`, `LoginResponse`, `CadastroRequest`)  
- **JWT (JSON Web Token)**: usado para criar tokens seguros, permitindo **auto-login** no app  

**Fluxo de autenticação:**

```mermaid
flowchart LR
    A[App Android] -->|Login/Cadastro| B[Backend Spring Boot]
    B -->|Valida credenciais| C[Banco de Dados]
    C -->|Confirmação| B
    B -->|JWT + Email| A
    A -->|Salva token DataStore| D[Auto-login em próximas aberturas]

## Como usar
1. Clone o repositório: `git clone https://github.com/claudiomedika/Pomodoro.git`
2. Abra no Android Studio
3. Execute no emulador ou dispositivo real
4. Teste cadastro, login e logout

## Contato
Desenvolvedor: Cláudio Kayinda  
Email: [isamedika123@gmail.com]
