#ifndef NOCANDIDATO_H
#define NOCANDIDATO_H
#include <cstring>
#include <Candidato.h>
#include <nocandidato.h>
#include <iostream>
#include <sstream>

using namespace std;

class NoCandidato
{
public:
    Candidato *conteudo;
    NoCandidato *next;

    NoCandidato(Candidato *conteudo, NoCandidato *next);
    string toString();
};

#endif // NOCANDIDATO_H
