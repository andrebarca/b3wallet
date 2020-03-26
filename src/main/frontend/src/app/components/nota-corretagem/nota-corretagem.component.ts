import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NotaCorretagemService, OperacaoService,PapelService } from '../../services';
import { NotaCorretagem, Papel, Operacao, Option } from '../../models';
import { OperacaoComponent } from '../operacao/operacao.component';

@Component({
  selector: 'nota-corretagem',
  templateUrl: './nota-corretagem.component.html',
  styleUrls: ['./nota-corretagem.component.css']
})
export class NotaCorretagemComponent implements OnInit {

  @Input()
  notaCorretagem: NotaCorretagem = null;

  @Output()
  notaCorretagemChange = new EventEmitter<NotaCorretagem>();

  selectedOperacao: Operacao = null;
  editingOperacaoFlag: boolean;
  newOperacaoFlag: boolean;

  papeis: Papel[] = [];
  optionsTipoOperacao: Option[] = [];

  constructor (
    private notaCorretagemService: NotaCorretagemService,
    private operacaoService: OperacaoService,
    private papelService: PapelService,
    private operacaoComponent: OperacaoComponent
  ) {}

  ngOnInit() {
    this.operacaoService.listOptionsTipoOperacao().subscribe(response => this.optionsTipoOperacao = response.body);
    this.papelService.list().subscribe(response => this.papeis = response.body);
  }

  save(): void {
    this.notaCorretagemService.save(this.notaCorretagem).subscribe(response => {
      this.operacaoComponent.updateServices();
      this.close();
    }, error => {
      console.log(error);
    });
  }

  newOperacao(): void {
    this.newOperacaoFlag = true;
    this.selectedOperacao = new Operacao();
  }

  confirmOperacao(): void {
    if (this.newOperacaoFlag) {
      this.notaCorretagem.addOperacao(Object.assign(new Operacao(), this.selectedOperacao));
      this.selectedOperacao = null;
      this.newOperacaoFlag = false;
    }

    if (this.editingOperacaoFlag) {
      this.editingOperacaoFlag = false;
    }
  }

  editOperacao(operacao: Operacao): void {
    this.selectedOperacao = operacao;
    this.editingOperacaoFlag = true;
  }

  cancelOperacao(): void {
    if (this.notaCorretagem.operacoes.length > 0) {
      this.selectedOperacao = null;
      this.newOperacaoFlag = false;
    }
  }

  removeOperacao(operacao: Operacao): void {
    if (this.isOperacaoRemovable()) {
      this.notaCorretagem.removeOperacao(operacao);
    }
  }

  isNew(): boolean {
    return this.newOperacaoFlag;
  }

  isEditing(): boolean {
    return this.editingOperacaoFlag;
  }

  isOperacaoRemovable(): boolean {
    return this.notaCorretagem.operacoes.length > 1;
  }

  notaCorretagemIsValid(): boolean {
    return true;
  }

  selectedOperacaoModelValid(): boolean {
    if (this.selectedOperacao != null) {
      return this.selectedOperacao.papel != null &&
      this.selectedOperacao.valor !== 0
      && this.selectedOperacao.quantidade !== 0;
    }
    return false;
  }

  close(): void {
    this.notaCorretagem = null;
    this.notaCorretagemChange.emit(this.notaCorretagem);
  }
}
