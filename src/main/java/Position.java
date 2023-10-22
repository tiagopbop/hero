public class Position
{
    public int x;
    public int y;
    public Position position;

    public Position(int a, int b)
    {
        this.x = a;
        this.y = b;
    }




    public int getX() {return x;}
    public void setX(int a) {this.x = a;}
    public int getY() {return y;}
    public void setY(int b) {this.y = b;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }

}
