
export class User implements UserInterface {
	public id: number;
	public username: string;
	public password: string;
	public firstName: string;
	public lastName: string;
	public citizenID: number;
	public indexNumber: string;
	public balance: number;
	public userAuthorities: AuthorityInterface[];
	public passingExams: PassingExamsInterface[];
	public subjectPresences: SubjectPresencesInterface[];
	public transactions: TransactionInterface[];
	public subjectLectures: SubjectLecturesInterface[];
	public passedExam: PassedExamInterface;


	constructor(userCfg:UserInterface) {
		this.id = userCfg.id;
		this.username = userCfg.username;
		this.password = userCfg.password;
		this.firstName = userCfg.firstName;
		this.lastName = userCfg.lastName;
		this.citizenID = userCfg.citizenID;
		this.indexNumber = userCfg.indexNumber;
		this.balance = userCfg.balance;
		this.userAuthorities = userCfg.userAuthorities;
		this.passingExams = userCfg.passingExams;
		this.subjectPresences = userCfg.subjectPresences;
		this.transactions = userCfg.transactions;
		this.subjectLectures = userCfg.subjectLectures;
		this.passedExam = userCfg.passedExam;
	}
}

interface UserInterface {
	id?: number;
	username: string;
	password: string;
	firstName: string;
	lastName: string;
	citizenID: number;
	indexNumber: string;
	balance: number;
	userAuthorities: AuthorityInterface[];
	passingExams: PassingExamsInterface[];
	subjectPresences: SubjectPresencesInterface[];
	transactions: TransactionInterface[];
	subjectLectures: SubjectLecturesInterface[];
	passedExam: PassedExamInterface;
}

export interface PassedExamInterface {
	id?: number;
	grade: number;
	passing_exams: PassingExamsInterface;
}

interface AuthorityInterface {
	id?: number;
	authority: object;
}

export interface ExamInterface {
	id?: number;
	date: Date;
	subject: SubjectInterface;
	teacher: UserInterface;
}

export interface SubjectInterface {
	id?: number;
	name: string;
	description: string;
}

export interface TransactionInterface {
	id?: number;
	amount: number;
}

export interface SubjectPresencesInterface {
	id?: number;
	subject: SubjectInterface;
}

export interface PassingExamsInterface {
	id?: number;
	date: Date;
	subject: SubjectInterface;
	teacher: UserInterface;
	students: UserInterface[];
}

export interface SubjectLecturesInterface {
	id?: number;
	teacher: UserInterface;
	subject: SubjectInterface;
}

export class Record implements RecordInterface{
	public id: number;
	public title: string;
	public imageUrl: string;
	public styles: StyleInterface[];
	public author: AuthorInterface;
	public price: number;

	constructor(recordCfg:RecordInterface)
	{
		this.id = recordCfg.id;
		this.title = recordCfg.title;
		this.imageUrl = recordCfg.imageUrl;
		this.styles = recordCfg.styles;
		this.author = recordCfg.author;
		this.price = recordCfg.price;
	}
}

interface RecordInterface{
	id?: number;
	title: string;
	imageUrl: string;
	styles: StyleInterface[];
	author: AuthorInterface;
	price: number
}

export interface AuthorInterface{
	id?: number;
	name?: string;
}

interface StyleInterface{
	id?: number;
	name?: string;
}

export enum Order{
  asc,
  desc
}
