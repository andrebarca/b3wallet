import { Operacao } from './operacao';
export class NotaCorretagem {
    id: number;
    numero: number;
    dataPregao: Date;
    operacoes: Operacao[] = [];
    custosOperacionais = 0;
    irrf = 0;
    totalTaxas = 0;
    dataCriacao: Date;
    dataAtualizacao: Date;

    addOperacao(operacao: Operacao): void {
        this.operacoes.push(operacao);
    }

    removeOperacao(operacao: Operacao): void {
        const index = this.operacoes.findIndex(op => op == operacao);
        this.operacoes.splice(index, 1);
    }

    get totalNota(): number {
        const t = (total: number, value: number) => total + value;
        const totalOperacoes: number[] = [];
        this.operacoes.forEach(o => {
            totalOperacoes.push(o.tipoOperacao === 'COMPRA' ? o.totalOperacao * -1 : o.totalOperacao);
        });
        return totalOperacoes.length === 0 ? 0 : (totalOperacoes.reduce(t) - (this.custosOperacionais));
    }
}
