TEMPLATE = app
CONFIG += console c++11
CONFIG -= app_bundle
CONFIG -= qt

SOURCES += main.cpp \
    NoCandidato.cpp \
    ListaCandidatos.cpp

HEADERS += \
    Candidato.h \
    NoCandidato.h \
    ListaCandidatos.h
