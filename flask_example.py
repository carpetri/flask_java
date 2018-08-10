import os
from flask import Flask, jsonify, request, url_for
import ast

# Create Flask application
app = Flask(__name__)

# Get bindings from the environment
DEBUG = (os.getenv('DEBUG', 'False') == 'True')
PORT = os.getenv('PORT', '5000')
HOSTNAME = os.getenv('HOSTNAME', '127.0.0.1')

# Application Routes

@app.route('/')
def index():
    """ Returns a message about the service """
    return jsonify(name='POC as MICROSERVICE ',
                   version='1.0',
                   url=url_for('avg', _external=True)
                  ), 200

@app.route('/avg', methods =['POST'])
def avg():
    """ Gets the average of the list in the input"""
    data = request.get_json()
    try:
        list1 = ast.literal_eval(data['list'])
        out = sum(list1) / float(len(list1) )
    except :
        message = {'error': 'Need a List'}
        return jsonify(message), 400

    return jsonify( out ), 201  


###########################################################
#   M A I N
###########################################################
if __name__ == "__main__":
    print "============================================"
    print "POC AS MICROSERVICE EXAMPLE "
    print "============================================"
    app.run(host='0.0.0.0', port=int(PORT), debug=DEBUG)
