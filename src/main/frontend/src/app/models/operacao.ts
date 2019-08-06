import { Papel, NotaCorretagem } from '.';

export class Operacao {
  id: number;
  notaCorretagem: NotaCorretagem;
  papel: Papel;
  valor = 0;
  dataOperacao: Date;
  quantidade = 0;
  tipoOperacao: string;
  custoOperacao = 0;
  totalOperacao = 0;
}
