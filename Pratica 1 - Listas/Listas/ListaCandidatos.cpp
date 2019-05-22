#include "ListaCandidatos.h"


ListaCandidatos::ListaCandidatos()
{
    head=NULL;
}

ListaCandidatos::ListaCandidatos(string nomeDoArquivo)
{
    head=NULL;
    ifstream leitor(nomeDoArquivo);

    string dados;
    getline(leitor, dados);
    cout << "criacao da lista de candidatos de: " << dados << endl;

    while(getline(leitor,dados))
    {
        adicioneComoHead(new Candidato(dados));
    }
    leitor.close();
}

void ListaCandidatos::adicioneComoHead(Candidato *c)
{
    head=new NoCandidato(c, head);
}

bool ListaCandidatos::estaVazia()
{
    if(head==NULL)
    {
        return true;
    }
    return false;
}

int ListaCandidatos::tamanho()
{
    int numero_elementos=0;

    NoCandidato *no = head;
    while(no!=NULL)
    {
        numero_elementos++;
        no=no->next;
    }
    return numero_elementos;
}

string ListaCandidatos::toString()
{
    if(head!=NULL)
    {
        return head->toString();
    }
    return "0";
}

bool ListaCandidatos::remove(string nome, string sobrenome)
{
    NoCandidato *no=head;

    if(head==NULL)
    {
        return false;
    }

    if(no->conteudo->igual(nome, sobrenome))
    {
        head=no->next;
        delete(no);
        return true;
    }


    while(no->next!=NULL)
    {
        if(no->next->conteudo->igual(nome, sobrenome))
        {
           NoCandidato *temp=no->next;
           no->next=temp->next;
           delete(temp);
           return true;
        }
        no=no->next;
    }
    return false;
}

void ListaCandidatos::filtrarCandidatos(int nota)
{
    NoCandidato *no=head;

    while(no!=NULL)
    {
        if(no==head && no->conteudo->nota<nota)
        {
            if(no->next==NULL)
            {
                delete(head);
                head=NULL;
                no=NULL;
            }
            else
            {
                head=head->next;
                delete(no);
                no=head;
            }
        }
        else if(no->next!=NULL && no->next->conteudo->nota<nota)
        {
            NoCandidato *temp=no->next;
            no->next=temp->next;
            delete(temp);
        }
        else
        {
            no=no->next;
        }
    }
}

void ListaCandidatos::concatena(ListaCandidatos *lc)
{
    NoCandidato *no=head;
    while(no->next!=NULL)
    {
        no=no->next;
    }
    no->next=lc->head;
}
