import unittest
import os
import json
import flask_example 

class TestFlask_Example(unittest.TestCase):
    """Server Tests"""

    @classmethod
    def setUpClass(cls):
        flask_example.app.debug = False
        
    def setUp(self):
        # Setup test data
        self.app = flask_example.app.test_client()

    def test_index(self):
        """Test the Home Page """
        resp = self.app.get('/')
        self.assertEqual(resp.status_code, 200)

    def test_avg(self):
        """Test Average of a list"""
        list1 = {"list":"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"}
        
        resp = self.app.post('/avg', data=json.dumps(list1), content_type='application/json')
        self.assertEqual(resp.status_code, 201)
        
        data = json.loads(resp.data.decode('utf8'))
        self.assertEqual(data,5.0)
        
if __name__ == '__main__':
    unittest.main()
