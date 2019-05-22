#ifndef LISTACANDIDATOS_H
#define LISTACANDIDATOS_H
#include <nocandidato.h>
#include <cstring>
#include "Candidato.h"
#include <fstream>
#include <iostream>

using namespace std;

class ListaCandidatos
{
public:
    NoCandidato *head;
    ListaCandidatos();
    ListaCandidatos(string nomeDoArquivo);
    void adicioneComoHead(Candidato *c);
    bool estaVazia();
    int tamanho();
    string toString();
    bool remove(string nome, string sobrenome);
    void filtrarCandidatos(int nota);
    void concatena(ListaCandidatos *lc);

};

#endif // LISTACANDIDATOS_H
