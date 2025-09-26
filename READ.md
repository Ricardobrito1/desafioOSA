![CI](https://github.com/<owner>/<repo>/actions/workflows/ci.yml/badge.svg)
![Docker](https://github.com/<owner>/<repo>/actions/workflows/docker.yml/badge.svg)

## Como rodar local
- `./mvnw clean verify`
- `docker compose up --build`

## Artefatos do CI
- Baixe o JAR em *Actions → job → Artifacts → app-jar*.

## Imagem no GHCR (se ativado)
- `docker pull ghcr.io/<owner>/<repo>:main`
