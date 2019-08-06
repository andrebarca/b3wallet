import { Operacao, Empresa } from '.';

 export class Papel {
    id: number;
    codigo: string;
    empresa: Empresa;
    operacoes: Operacao[] = [];
    totalCompra = 0;
    totalVenda = 0;
    totalCustodia = 0;
    custos = 0;
    precoMedio = 0;
    totalLucro = 0;
}
