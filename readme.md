
# Gestão de vagas - BackEnd

Este projeto foi desenvolvido como parte da trilha de Java do programa Ignite, com foco na criação da interface gráfica do front-end. Ele utiliza o Thymeleaf, uma template engine integrada ao Spring, que facilita o desenvolvimento de interfaces dinâmicas e simplifica a integração com o back-end. O objetivo foi criar uma interface gráfica funcional e eficiente, mantendo a simplicidade e agilidade no desenvolvimento.

 
 
## Resetar o application.properties para o Ambiente Local

1 - Abra o arquivo src/main/resources/application.properties.

2 - Defina no seu application.properties em qual porta ele irá rodar e em seguida, você precisara definir qual a porta, o back-end está rodando: 
***
    server.port=8081
    host.api.gestao.vagas=http://localhost:8081 
***

3- Salve o arquivo.

## Executando a aplicação no Ambiente Local

1 - Instale as dependências do projeto:
***
    mvn clean install
***

2 - Inicie o projeto: 
***
    mvn spring-boot:run
***

A aplicação estará disponível em http://localhost:8081.
(Note que que, este 8081 deverá ser substituído pela porta que você configurou no seu application.properties.)


>Importante: Certifique-se de não enviar alterações para a branch main no GitHub, pois isso pode causar a interrupção do site hospedado no Render. Sempre trabalhe em uma branch separada e valide as mudanças antes de realizar qualquer merge.

## config padrao deploy para o banco


            - name: Build project
              run: mvn clean install
            - name: Login docker
              run: docker login -u ${{secrets.DOCKER_USERNAME}} -p ${{secrets.DOCKER_PASSWORD}}
            - name: Build docker image
              run: docker build -t rocketseat-education/gestao_vagas .
            - name: Publish image
              run: docker push rocketseat-education/gestao_vagas
    deploy:
        needs: build
        runs-on: self-hosted
        steps:
            - name: Pull image docker hub 
              run: docker pull rocketseat-education/gestao_vagas:latest
            - name:  remove container
              run: docker rm -f gestao-vagas
            - name: Run docker container gestao-vagas
              run: docker run -d -p 8080:8080 -e DATABASE_URL=${{secrets.DATABASE_URL}} -e DATABASE_PASSWORD='${{secrets.DATABASE_PASSWORD}}' -e DATABASE_USERNAME=${{secrets.DATABASE_USERNAME}} -e DATABASE_NAME=${{secrets.DATABASE_NAME}} --name gestao-vagas rocketseat-education/gestao_vagas
              