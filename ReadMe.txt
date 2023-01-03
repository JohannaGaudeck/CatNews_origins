# CatNews

## Introduction


CatNews is a news program that allows users to obtain newspaper articles directly from [APA](https://api.ots.at/doku/) (Austrian Press Agency) and save them locally for easy viewing. 
Possible queries include according to current project schedule:
- Latest
- Keyword search

In addition, and also name-giving, the [CatAPI](https://thecatapi.com) is integrated for prettifying the user interface.

## Branching Strategy

We follow the [git flow](https://nvie.com/posts/a-successful-git-branching-model/) model with the following conventions, but in a rather basic form:

- The development branch is called `dev`.
- Branch names must be lowercase.
- `${description}`s should be as short as possible while still carrying enough information.
- `${issueId}`s used in branch names must not contain the `#` part.
- **For now** we are zero padding to 2 digits, might change in the process if it's too much of a hassle.
- E.g. for the GitHub Issue `As a participant I want to have a README.md to inform myself about the rules and standards of the project #3` the branch should be named `feat/03/readme`.
  If no ticket is available just skip the id-part.
- Every [feature branch](#feature-branches) is named `feat/{issueId}/{description}`.
  You _can_ use further `/`es in your own description.
- `main` is our **deployment branch**, commits merged to `main` get deployed automatically.

## Feature Branches
You're working on the latest and greatest feature.
Nice!

1. Check out dev and update it
```sh
git checkout dev
git pull
```
2. Create a feature branch
```sh
git checkout -b feat/{isseuId}/{description}
```
3. Implement the feature
4. **Create tests** for the added feature, there is no feature without tests
5. Create a pull request to `dev` and assign a reviewer and somebody that will merge the branch (can be the same person).

## Collaborators
- [Johanna Gaudeck](https://github.com/JohannaGaudeck) 
- [Patrick Cosic](https://github.com/P-a-co)
- [Aelfric-Wieland	Mayer](https://github.com/HeroinHeroO)

