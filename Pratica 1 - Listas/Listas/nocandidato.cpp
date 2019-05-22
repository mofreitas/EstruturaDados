#include "NoCandidato.h"

NoCandidato::NoCandidato(Candidato *conteudo, NoCandidato *next)
{
    this->conteudo=conteudo;
    this->next=next;
}

string NoCandidato::toString()
{
    stringstream stream;
    if(next!=NULL)
    {
        stream << conteudo->toString() << " -> " << next->toString();
    }
    else
    {
        stream << conteudo->toString() << " -> " << 0;
    }
    return stream.str();
}
