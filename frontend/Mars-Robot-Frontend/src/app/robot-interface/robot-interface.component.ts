import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface RobotResponse {
  xCoord: number;
  yCoord: number;
  orientation: string;
}

@Component({
  selector: 'app-robot-interface',
  templateUrl: './robot-interface.component.html',
  styleUrls: ['./robot-interface.component.scss']
})
export class RobotInterfaceComponent {
  squareArray: any[] = new Array(25);
  showImage: boolean = false;

  rotation: string = '0deg';
  commands: string = '';
  robot: RobotResponse | null = null;
  errorMessage: string | null = null;
  apiUrl: string = 'http://localhost:8080/rest/mars';

  constructor(private http: HttpClient) { }

  sendCommands() {
    this.http.post<RobotResponse>(this.apiUrl, this.commands)
      .subscribe({
        next: (data) => {
          this.robot = data;
          this.errorMessage = null;

          switch (this.robot.orientation) {
            case 'N':
              this.rotation = '0deg';
              break;
            case 'E':
              this.rotation = '90deg';
              break;
            case 'S':
              this.rotation = '180deg';
              break;
            case 'W':
              this.rotation = '-90deg';
              break;
            default:
              this.rotation = '0deg';
          }
        },
        error: (error) => {
          this.robot = null;
          this.errorMessage = 'Houve um erro! Verifique se as coordenadas inseridas têm caracteres além de R, L e M ou se o robô saiu do terreno';
        }
      });
  }

  calculateIndex(index: number) {
    index = index;
    console.log('Índice clicado:', index);
    this.showImage = true;
  }

  getInvertedIndex(index: number): number {
    const gridSize = 5;
    const row = Math.floor(index / gridSize);
    const col = index % gridSize;
    const invertedIndex = (gridSize - row - 1) * gridSize + col;
    return invertedIndex;
  }

  showRobot(index: number) {
    let grid: number = 5;
    return this.robot
      ? (grid * this.robot.yCoord) + this.robot.xCoord == index
      : false;
  }
}
