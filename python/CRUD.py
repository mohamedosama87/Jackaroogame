from flask import Flask,jsonify,request
import psycopg
app = Flask(__name__)
db_URL  = "postgresql://postgres:mo2006@localhost:5432/College"
class Student:
    def __init__(self,name,age,gender,major,id):
        self.name = name
        self.age = age
        self.gender = gender 
        self.major = major
        self.id = id
    def dict(self):
        return {'name' : self.name, 'age' : self.age, 'gender': self.gender, 'major' : self.major,'id' : self.id
        }
@app.route('/student', methods = ['POST'])
def create():
    data  = request.json
    name = data['name']
    age = data['age']
    major = data['major']
    gender = data['gender']
    id = data['id']
    conn = psycopg.connect(db_URL)
    cur = conn.cursor() 
    cur.execute('INSERT INTO "Students"."Student" VALUES(%s,%s,%s,%s,%s)' , (id,name,gender,age,major))
    cur.close()
    conn.commit()
    return jsonify({"msg" : "The Student was inserted sucessfully"})
@app.route('/student/<id>', methods = ['GET'])
def read(id):
    data  = request.json
    id = data['id']
    conn = psycopg.connect(db_URL)
    cur = conn.cursor()
    cur.execute('SELECT name,gender,age,major FROM "Students"."Student" WHERE id = %s',(id,))
    read_student = cur.fetchone()
    if(read_student):
        name,gender,age,major = read_student
        student_dict = {
            'name' : name,
            'gender' : gender,
            'age' : age,
            'major' : major
        }
        return jsonify(student_dict)
    else:
        return jsonify({'msg':'The student is not availble'})
@app.route('/student/<id>', methods = ['DELETE'])
def delete(id):
   data = request.json
   id = data['id']
   conn = psycopg.connect(db_URL)
   cur = conn.cursor()
   cur.execute('SELECT * FROM "Students"."Student" WHERE id = %s' , (id,))
   deleted_student = cur.fetchone()
   if(deleted_student):
     id,name,gender,age,major = deleted_student
     student_dict = {
         'id' : id,
         'name' : name,
         'gender' : gender,
         'age ' : age,
         'major' : major
     }
     cur.execute('DELETE FROM "Students"."Student" WHERE id = %s' , (id,))
     cur.close()
     conn.commit()
     return jsonify(student_dict)
   else:
       return jsonify({'msg' : "The student is not availble"})
@app.route('/student/<id>' , methods = ['PUT'])
def update(id):
    data = request.json
    id = data['id']
    name = data['name']
    age = data['age']
    major = data['major']
    gender = data['gender']
    conn = psycopg.connect(db_URL)
    cur = conn.cursor()
    cur.execute('SELECT * FROM "Students"."Student" WHERE id = %s' , (id,))
    found = cur.fetchone()
    if(found):
        cur.execute('UPDATE "Students"."Student" SET id = %s, name = %s , gender = %s,age = %s,major = %s', (id,name,gender,age,major) )
        cur.close()
        conn.commit()
        return jsonify({'msg' : 'The student was updated'})
    else:
        return jsonify({'msg':'The student is not availble'})
if(__name__ == '__main__'):
    app.run(debug=True)