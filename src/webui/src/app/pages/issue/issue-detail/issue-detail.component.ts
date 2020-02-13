import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IssueService} from "../../../services/shared/issue.service";
import {ProjectService} from "../../../services/shared/project.service";
import {UserService} from "../../../services/shared/user.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-issue-detail',
  templateUrl: './issue-detail.component.html',
  styleUrls: ['./issue-detail.component.scss']
})
export class IssueDetailComponent implements OnInit {

  @ViewChild('tplDateCell', {static: true}) tplDateCell: TemplateRef<any>;
  //route parameter options
  id: number;
  private sub: any;

  issueDetailForm: FormGroup;

  //history table options
  datatable_rows = [];

  //Dropdown values
  projectOptions = [];
  assigneeOptions = [];
  issueStatusOptions = [];

  constructor(
    private route: ActivatedRoute,
    private issueService: IssueService,
    private projectService: ProjectService,
    private userService: UserService,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit() {

    this.sub = this.route.params.subscribe(param => {
      this.id = +param['id'];
      this.loadIssueDetails();
    })

    this.loadProjects();
    this.loadAssignees();
    this.loadIssueStatuses();
  }

  private loadIssueDetails() {
    this.issueService.getByIdWithDetails(this.id).subscribe(response => {
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }

  private loadProjects() {
    this.issueService.getAllIssueStatuses().subscribe(res => {
      this.issueStatusOptions = res;
    })
  }

  private loadAssignees() {
    this.userService.getAll().subscribe(res => {
      this.assigneeOptions = res;
    })
  }

  private loadIssueStatuses() {
    this.projectService.getAll().subscribe(res => {
      this.projectOptions = res;
    })
  }

  createIssueDetailFormGroup(response) {
    return this.formBuilder.group({
      id: response['id'],
      description: response['description'],
      details: response['details'],
      date: this.fromJsonDate(response['date']),
      issueStatus: response['issueStatus'],
      assignee_id: response['assignee'] ? response['assignee']['id'] : '',
      project_id: response['project'] ? response['project']['id'] : '',
      project_manager: response['project'] && response['project']['manager'] ? response['project']['manager']['nameSurname'] : '',
    });
  }

  saveIssue() {
    this.issueService.updateIssue(this.issueDetailForm.value).subscribe(response => {
      this.issueDetailForm = this.createIssueDetailFormGroup(response);
      this.datatable_rows = response['issueHistories'];
    });
  }

  fromJsonDate(jDate): string {
    const bDate: Date = new Date(jDate);
    return bDate.toISOString().substring(0, 10);
  }
}
