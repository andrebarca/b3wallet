import { Operacao } from './operacao';

export class NotaCorretagem {
    id: number;
    numero: number;
    dataPregao: Date;
    operacoes: Operacao[] = [];
    custos = 0;
    irrf = 0;
    totalTaxas = 0;
    dataCriacao: Date;
    dataAtualizacao: Date;

    get totalNota(): number {
        const t = (total: number, value: number) => total + value;
        const totalOperacoes: number[] = [];
        this.operacoes.forEach(o => {
            totalOperacoes.push(o.tipoOperacao === 'COMPRA' ? o.totalOperacao * -1 : o.totalOperacao);
        });
        return totalOperacoes.length === 0 ? 0 : (totalOperacoes.reduce(t) - (this.custos + this.irrf));
    }
}
