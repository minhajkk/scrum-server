package com.scrum

import grails.rest.RestfulController

class CommentController extends RestfulController<Comment>{

    static responseFormats = ['json', 'xml', 'html']

    def index() {}
}
