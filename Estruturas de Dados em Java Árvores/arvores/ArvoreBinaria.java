package br.com.projeto.arvores;

public class ArvoreBinaria<T extends Comparable<T>> {

    private BinNo<T> raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(T conteudo) {
        BinNo<T> novoNo = new BinNo<>(conteudo);
        raiz = inserir(raiz, novoNo);
    }

    private BinNo<T> inserir(BinNo<T> atual, BinNo<T> novoNo) {
        if (atual == null) {
            return novoNo;
        } else if (novoNo.getConteudo().compareTo(atual.getConteudo()) < 0) {
            atual.setNoEsquerdo(inserir(atual.getNoEsquerdo(), novoNo));
        } else {
            atual.setNoDireito(inserir(atual.getNoDireito(), novoNo));
        }
        return atual;
    }


    public void exibirEmOrdem() {
        System.out.println("\n exibindo em ordem");
        exibirEmOrdem(this.raiz);
    }

    private void exibirEmOrdem(BinNo<T> atual) {
        if (atual != null) {
            exibirEmOrdem(atual.getNoEsquerdo());
            System.out.print(atual.getConteudo() + " - ");
            exibirEmOrdem(atual.getNoDireito());
        }
    }

    public void exibirPosOrdem() {
        System.out.println("\n exibindo em pos ordem");
        exibirPosOrdem(this.raiz);
    }

    private void exibirPosOrdem(BinNo<T> atual) {

        if (atual != null) {
            exibirPosOrdem(atual.getNoEsquerdo());
            exibirPosOrdem(atual.getNoDireito());
            System.out.print(atual.getConteudo() + " - ");
        }

    }

    public void exibirPreOrdem() {
        System.out.println("\n exibindo em pos ordem");
        exibirPreOrdem(this.raiz);
    }

    private void exibirPreOrdem(BinNo<T> atual) {

        if (atual != null) {
            System.out.print(atual.getConteudo() + " - ");
            exibirPreOrdem(atual.getNoEsquerdo());
            exibirPreOrdem(atual.getNoDireito());
        }

    }

    public void remove(T conteudo) {


        try {

            BinNo<T> atual = this.raiz;
            BinNo<T> pai = null;
            BinNo<T> filho = null;
            BinNo<T> temp = null;

            while (atual != null && atual.getConteudo().equals(conteudo)) {

                pai = atual;
                if (conteudo.compareTo(atual.getConteudo()) < 0) {
                    atual = atual.getNoEsquerdo();
                } else {
                    atual = atual.getNoDireito();
                }
            }

            if (atual == null) {
                System.out.println("Conteudo nao encontrado : bloco try");
            }

            if (pai == null) {

                if (atual.getNoDireito() == null) {
                    this.raiz = atual.getNoEsquerdo();
                } else if (atual.getNoEsquerdo() == null) {
                    this.raiz = atual.getNoDireito();
                } else {
                    for (temp = atual, filho = atual.getNoEsquerdo(); filho.getNoDireito() != null; temp = filho, filho = filho.getNoEsquerdo()) {
                        if (filho != atual.getNoEsquerdo()) {
                            temp.setNoDireito(filho.getNoEsquerdo());
                            filho.setNoEsquerdo(raiz.getNoEsquerdo());
                        }
                    }
                    filho.setNoDireito(raiz.getNoDireito());
                    raiz = filho;
                }


            } else if (atual.getNoDireito() == null) {
                if (pai.getNoEsquerdo() == atual) {
                    pai.setNoEsquerdo(atual.getNoEsquerdo());
                } else {
                    pai.setNoDireito(atual.getNoEsquerdo());
                }

            } else if (atual.getNoEsquerdo() == null) {
                if (pai.getNoEsquerdo() == atual) {
                    pai.setNoEsquerdo(atual.getNoDireito());
                } else {
                    pai.setNoDireito(atual.getNoDireito());
                }

            } else {

                for (temp = atual, filho = atual.getNoEsquerdo(); filho.getNoDireito() != null; temp = filho, filho = filho.getNoDireito()) {
                    if (filho != atual.getNoEsquerdo()) {
                        temp.setNoDireito(filho.getNoEsquerdo());
                        filho.setNoEsquerdo(atual.getNoEsquerdo());

                    }
                    filho.setNoDireito(atual.getNoDireito());
                    if (pai.getNoEsquerdo() == atual) {
                        pai.setNoEsquerdo(filho);

                    } else {
                        pai.setNoDireito(filho);
                    }

                }

            }

        } catch (NullPointerException erro) {
            System.out.println("Conteudo nao encontrado : bloco catch");
        }


    }
}
