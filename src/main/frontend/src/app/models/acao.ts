import { Operacao } from '../models/operacao';
 export class Acao {

    id: number;
    codigo: string;
    nome: string;
    operacoes: Operacao[] = [];
    totalCompra: number = 0;
    totalVenda: number = 0;
    totalCustodia: number = 0;
    precoMedio: number = 0;
    totalLucro: number = 0;
}