import {Component, OnInit} from '@angular/core';
import {Book} from "./book";
import {BooksService} from "./books.service";
import {FeeService} from "./fee.service";
import {ConfirmationService} from 'primeng/api';

@Component({
  selector: 'app-books-overview',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css'],
  providers:[ConfirmationService]
})
export class LibraryComponent implements OnInit {

  availableBooks: Book[];
  selectedBooksBasket: Book[];
  draggedBook: Book;
  loggedInUser: string = "AnyUser";

  constructor(
    private booksService: BooksService,
    private feeService: FeeService,
    private confirmationService: ConfirmationService
  ) {
  }

  ngOnInit() {
    this.initialise();
  }

  private initialise() {
    this.selectedBooksBasket = [];
    this.booksService.getBooks()
      .subscribe((books: Book[]) => {
        this.availableBooks = books
      });
  }

  drop($event: any) {
    if (this.draggedBook) {
      let draggedBookIndex = this.findIndex(this.draggedBook);
      this.selectedBooksBasket = [...this.selectedBooksBasket, this.draggedBook];
      this.availableBooks = this.availableBooks.filter((val, i) => i != draggedBookIndex);
      this.draggedBook = null;
    }
  }

  findIndex(book: Book) {
    let index = -1;
    for (let i = 0; i < this.availableBooks.length; i++) {
      if (book.id === this.availableBooks[i].id) {
        index = i;
        break;
      }
    }
    return index;
  }

  dragStart($event: any, book: Book) {
    this.draggedBook = book;
  }

  dragEnd($event: any) {
    this.draggedBook = null;
  }

  resetSelectedBooksBasket() {
    this.initialise();
  }

  calculateFee() {
    this.feeService.calculateFee(this.selectedBooksBasket, this.loggedInUser)
      .subscribe(fee => {
        this.confirmationService.confirm({
          message: `${fee}`.replace(/\n/g,'<br/>'),
          header:"Your fee",
          acceptLabel:"Accept",
          rejectLabel:"Reject",
          accept: () => {
            this.resetSelectedBooksBasket();
          }
        });
      });
  }

  increaseDaysRentedOf(book: Book) {
    book.numberOfDaysRented++;
  }
}
