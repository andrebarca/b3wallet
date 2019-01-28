import { Acao } from "../models/acao";

export class Analise {
  id: number;
  acao: Acao;
  anotacao: string;
  dataCriacao: Date;
  dataAtualizacao: Date;
}